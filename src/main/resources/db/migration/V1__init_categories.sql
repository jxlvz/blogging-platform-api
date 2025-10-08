CREATE TABLE categories (
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP
);

-- search by category name (wildcard)
CREATE EXTENSION IF NOT EXISTS pg_trgm;
CREATE INDEX idx_categories_name_trgm ON categories USING gin (name gin_trgm_ops);
