# Blogging Platform API

A Spring Boot REST API for managing blog posts and categories. The implementation follows the "Blogging Platform API" idea published on [roadmap.sh](https://roadmap.sh/projects/blogging-platform-api).

## Features
- CRUD endpoints for blog posts with optional search by title or content
- CRUD endpoints for categories to keep posts organized
- Request validation and centralized error handling for consistent API responses
- PostgreSQL persistence managed through Flyway database migrations and seed data
- Dockerized local environment for one-command startup

## Tech Stack
- Java 21
- Spring Boot 3 (Web, Validation, Data JPA)
- PostgreSQL 16
- Flyway
- Docker & Docker Compose

## Prerequisites
- Java 21 runtime (use the embedded `mvnw` wrapper)
- Docker and Docker Compose (optional but recommended for local development)

## Getting Started

### 1. Configure environment variables
Environment defaults live in the `.env` file. Adjust them as needed—especially database credentials—before starting the services.

### 2. Run the full stack with Docker Compose
```bash
docker compose up --build
```
- API available at `http://localhost:9090`
- PostgreSQL exposed on `localhost:5433`
- Containers restart automatically unless stopped

Stop the stack with `docker compose down`.

### 3. Run the API with Maven (optional)
You can run the database via Docker Compose and start the API from your IDE or terminal.

```bash
# Start only PostgreSQL in the background
docker compose up -d postgres

# Run the application with Maven
./mvnw spring-boot:run
```

The API listens on the port defined by the `PORT` environment variable (defaults to `9090`). Flyway runs automatically on startup to apply the migrations under `src/main/resources/db/migration`.

## Useful Commands
- `./mvnw clean package` – build the application without running tests
- `./mvnw test` – execute the test suite
- `docker compose logs -f` – follow container logs

## API Overview
### Posts `/v1/api/posts`
- `GET /v1/api/posts?term=` – list posts, optionally filtered by search term
- `GET /v1/api/posts/{id}` – fetch a single post by its identifier
- `POST /v1/api/posts` – create a post linked to a category
- `PUT /v1/api/posts/{id}` – update an existing post
- `DELETE /v1/api/posts/{id}` – remove a post

### Categories `/v1/api/categories`
- `GET /v1/api/categories` – list categories
- `GET /v1/api/categories/{id}` – fetch a single category by its identifier
- `POST /v1/api/categories` – create a category
- `PUT /v1/api/categories/{id}` – update a category
- `DELETE /v1/api/categories/{id}` – remove a category

### Request Payloads
Payloads must be sent as JSON using snake_case keys.

**Create or update a post** (`POST`/`PUT /v1/api/posts...`)
```json
{
  "title": "Introducing Spring Boot 3",
  "content": "Body text for the article...",
  "author": "Jane Doe",
  "category_id": 1
}
```
- `title`: required, 3-255 characters
- `content`: required
- `author`: optional string
- `category_id`: required, must reference an existing category

**Create or update a category** (`POST`/`PUT /v1/api/categories...`)
```json
{
  "name": "Backend"
}
```
- `name`: required, 3-100 characters

Input payloads use snake_case as configured by Jackson. Validation errors and missing resources return structured JSON thanks to the global exception handler.

## Acknowledgements
Project idea adapted from [roadmap.sh](https://roadmap.sh/projects/blogging-platform-api).
