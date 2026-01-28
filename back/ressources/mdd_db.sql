DROP DATABASE mdd_db;

CREATE DATABASE mdd_db;

USE mdd_db;


-- ============================================================
-- CREATION DES TABLES
-- ============================================================

DROP TABLE IF EXISTS user_theme;
DROP TABLE IF EXISTS article_theme;
DROP TABLE IF EXISTS commentaries;
DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS theme;
DROP TABLE IF EXISTS user;

-- ---------------------------
-- TABLE user
-- ---------------------------

CREATE TABLE user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at DATE,
    updated_at DATE
);

-- ---------------------------
-- TABLE theme
-- ---------------------------

CREATE TABLE theme (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

-- ---------------------------
-- TABLE articles
-- ---------------------------

CREATE TABLE articles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    author_id BIGINT NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    CONSTRAINT fk_article_user FOREIGN KEY (author_id) REFERENCES user(id)
);

-- ---------------------------
-- TABLE commentaries
-- ---------------------------

CREATE TABLE commentaries (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    author_id BIGINT,
    message TEXT,
    created_at DATETIME,
    updated_at DATETIME,
    article_id BIGINT,
    CONSTRAINT fk_comment_author FOREIGN KEY (author_id) REFERENCES user(id),
    CONSTRAINT fk_comment_article FOREIGN KEY (article_id) REFERENCES articles(id)
);

-- ---------------------------
-- TABLE many-to-many : user -> theme
-- ---------------------------

CREATE TABLE user_theme (
    user_id BIGINT,
    theme_id BIGINT,
    PRIMARY KEY (user_id, theme_id),
    CONSTRAINT fk_user_theme_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_user_theme_theme FOREIGN KEY (theme_id) REFERENCES theme(id)
);

-- ---------------------------
-- TABLE many-to-many : article -> theme
-- ---------------------------

CREATE TABLE article_theme (
    article_id BIGINT,
    theme_id BIGINT,
    PRIMARY KEY (article_id, theme_id),
    CONSTRAINT fk_article_theme_article FOREIGN KEY (article_id) REFERENCES articles(id),
    CONSTRAINT fk_article_theme_theme FOREIGN KEY (theme_id) REFERENCES theme(id)
);

-- ============================================================
-- INSERTION DES DONNÉES
-- ============================================================

-- ---------------------------
-- USERS (avec mots de passe BCrypt)
-- ---------------------------

INSERT INTO user (name, email, password, created_at, updated_at) VALUES
('Alice', 'alice@example.com', '$2a$10$kt99SqEz20Qv7Gs4VrnK9eHQFNZVPdK5reS5A1PYfPZrC74UHWIbC', '2024-01-01', '2024-01-01'),
('Bob', 'bob@example.com', '$2a$10$eS8Vh36xsgntnnRkzZXE9eEJrSlflZ4OjFttM6YBUwWSWBFfo6F7m', '2024-02-01', '2024-02-01'),
('Charlie', 'charlie@example.com', '$2a$10$0K/gJ7YdnrbgyZy9WX3VuOxoQhpoAPOioRX2DsRk7sT1GqS/OGHe6', '2024-03-01', '2024-03-01');

-- ---------------------------
-- THEMES
-- ---------------------------

INSERT INTO theme (name) VALUES
('Java'),
('Spring Boot'),
('Angular'),
('DevOps'),
('Cybersecurity');

-- ---------------------------
-- USER_THEME
-- ---------------------------

INSERT INTO user_theme (user_id, theme_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4),
(3, 5);

-- ---------------------------
-- ARTICLES
-- ---------------------------

INSERT INTO articles (title, content, author_id, created_at, updated_at) VALUES
('Introduction à Spring Boot',
 'Spring Boot facilite énormément le développement Java moderne...',
 1, '2024-03-10 10:00:00', '2024-03-10 10:00:00'),

('Comprendre Angular en 2024',
 'Angular est un framework puissant pour créer des applications web...',
 2, '2024-03-15 14:00:00', '2024-03-15 14:00:00'),

('Les bases du DevOps',
 'DevOps améliore la collaboration entre dev et ops...',
 3, '2024-03-20 09:30:00', '2024-03-20 09:30:00');

-- ---------------------------
-- ARTICLE_THEME
-- ---------------------------

INSERT INTO article_theme (article_id, theme_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4);

-- ---------------------------
-- COMMENTARIES
-- ---------------------------

INSERT INTO commentaries (author_id, message, created_at, updated_at, article_id) VALUES
(2, 'Super article sur Spring Boot !', '2024-03-11 11:00:00', '2024-03-11 11:00:00', 1),
(3, 'Très clair, merci !', '2024-03-11 12:00:00', '2024-03-11 12:00:00', 1),
(1, 'Angular est parfois complexe, mais puissant.', '2024-03-16 15:00:00', '2024-03-16 15:00:00', 2),
(2, 'DevOps change vraiment la donne.', '2024-03-21 10:00:00', '2024-03-21 10:00:00', 3);
