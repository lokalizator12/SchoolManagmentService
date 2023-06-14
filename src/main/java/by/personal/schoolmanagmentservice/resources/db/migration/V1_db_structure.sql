CREATE TABLE news
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    header        VARCHAR(255)          NULL,
    date          VARCHAR(255)          NULL,
    `description` TEXT                  NULL,
    preview_image BLOB                  NULL,
    image_name    VARCHAR(255)          NULL,
    image         BLOB                  NULL,
    user_id       BIGINT                NOT NULL,
    CONSTRAINT pk_news PRIMARY KEY (id)
);

ALTER TABLE news
    ADD CONSTRAINT FK_NEWS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

CREATE TABLE roles
(
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_roles PRIMARY KEY (name)
);

CREATE TABLE user_roles
(
    role_name VARCHAR(255) NOT NULL,
    user_id   BIGINT       NOT NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (role_name, user_id)
);

CREATE TABLE users
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    login      VARCHAR(24)           NULL,
    first_name VARCHAR(50)           NULL,
    last_name  VARCHAR(50)           NULL,
    email      VARCHAR(255)          NULL,
    password   VARCHAR(255)          NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_login UNIQUE (login);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (role_name) REFERENCES roles (name);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (id);
