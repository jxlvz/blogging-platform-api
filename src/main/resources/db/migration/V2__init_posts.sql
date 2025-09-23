CREATE TABLE posts (
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    content     TEXT NOT NULL,
    author      VARCHAR(100),
    category_id BIGINT REFERENCES categories(id) ON DELETE SET NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Indexes
CREATE INDEX idx_posts_category_id ON posts(category_id);
CREATE INDEX idx_posts_created_at  ON posts(created_at DESC);

-- Wildcard search in title and content
CREATE EXTENSION IF NOT EXISTS pg_trgm;
CREATE INDEX idx_posts_title_trgm   ON posts USING gin (title gin_trgm_ops);
CREATE INDEX idx_posts_content_trgm ON posts USING gin (content gin_trgm_ops);