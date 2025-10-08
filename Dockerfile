# ---------- build ----------
FROM maven:3.9-eclipse-temurin-21@sha256:89086b81ff2ec9c65739b1763ffb729b59b48c569fe13e5c81a54e128b6827a7 AS build
WORKDIR /project
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -DskipTests dependency:go-offline
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -q -DskipTests clean package spring-boot:repackage

RUN java -Djarmode=layertools -jar target/*-SNAPSHOT.jar extract --destination target/layers

# ---------- runtime ----------
FROM eclipse-temurin:21-jre-alpine@sha256:df8ce8302ed2ed1690ef490c633981b07e752b373b5fdf796960fb2eb0d640ea
RUN addgroup -S app && adduser -S -G app -u 10001 app
WORKDIR /app

COPY --chown=app:app --from=build /project/target/layers/dependencies/ ./
COPY --chown=app:app --from=build /project/target/layers/spring-boot-loader/ ./
COPY --chown=app:app --from=build /project/target/layers/snapshot-dependencies/ ./
COPY --chown=app:app --from=build /project/target/layers/application/ ./

ENV JAVA_OPTS=""
USER 10001
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s --retries=3 CMD wget -qO- http://127.0.0.1:8080/actuator/health || exit 1

ENTRYPOINT ["sh","-c","exec java $JAVA_OPTS org.springframework.boot.loader.launch.JarLauncher"]
