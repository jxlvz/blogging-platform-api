INSERT INTO categories (name, created_at) VALUES
  ('tech', CURRENT_TIMESTAMP),
  ('travel', CURRENT_TIMESTAMP),
  ('food', CURRENT_TIMESTAMP),
  ('lifestyle', CURRENT_TIMESTAMP),
  ('tutorials', CURRENT_TIMESTAMP)
ON CONFLICT (name) DO NOTHING;

INSERT INTO posts (title, content, author, category_id, created_at)
SELECT 'Hello Tech', 'First post about tech', 'Jose', c.id, CURRENT_TIMESTAMP
FROM categories c WHERE c.name = 'tech'
AND NOT EXISTS (SELECT 1 FROM posts p WHERE p.title = 'Hello Tech');

INSERT INTO posts (title, content, author, category_id, created_at)
SELECT 'Trip to Japan', 'Blogging my journey through Tokyo and Kyoto', 'Alejandra', c.id, CURRENT_TIMESTAMP
FROM categories c WHERE c.name = 'travel'
AND NOT EXISTS (SELECT 1 FROM posts p WHERE p.title = 'Trip to Japan');

INSERT INTO posts (title, content, author, category_id, created_at)
SELECT 'Paella Recipe', 'Traditional Valencian paella step-by-step', 'Carlos', c.id, CURRENT_TIMESTAMP
FROM categories c WHERE c.name = 'food'
AND NOT EXISTS (SELECT 1 FROM posts p WHERE p.title = 'Paella Recipe');

INSERT INTO posts (title, content, author, category_id, created_at)
SELECT 'Morning Routine', 'Small habits that compound over time', 'Ana', c.id, CURRENT_TIMESTAMP
FROM categories c WHERE c.name = 'lifestyle'
AND NOT EXISTS (SELECT 1 FROM posts p WHERE p.title = 'Morning Routine');

INSERT INTO posts (title, content, author, category_id, created_at)
SELECT 'Spring Boot Basics', 'Controllers, services, repos and DTOs', 'Jose', c.id, CURRENT_TIMESTAMP
FROM categories c WHERE c.name = 'tutorials'
AND NOT EXISTS (SELECT 1 FROM posts p WHERE p.title = 'Spring Boot Basics');

INSERT INTO posts (title, content, author, category_id, created_at)
SELECT 'Tech Trends 2025', 'AI, edge computing and dev tooling', 'Claudia', c.id, CURRENT_TIMESTAMP
FROM categories c WHERE c.name = 'tech'
AND NOT EXISTS (SELECT 1 FROM posts p WHERE p.title = 'Tech Trends 2025');

INSERT INTO posts (title, content, author, category_id, created_at)
SELECT 'Best Budget Eats', 'Street food and hidden gems', 'Diego', c.id, CURRENT_TIMESTAMP
FROM categories c WHERE c.name = 'food'
AND NOT EXISTS (SELECT 1 FROM posts p WHERE p.title = 'Best Budget Eats');
