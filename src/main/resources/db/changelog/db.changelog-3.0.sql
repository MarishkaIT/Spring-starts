--liquibase formatted sql

--changeset litvinskaya:1

    ALTER TABLE users
    ADD COLUMN image VARCHAR(64);

--changeset litvinskaya:2
ALTER TABLE users_aud
ADD COLUMN image VARCHAR(64);


