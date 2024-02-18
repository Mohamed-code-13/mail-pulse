DROP DATABASE mailpulsedb;
DROP USER mailpulse;

CREATE USER mailpulse with password 'password';
CREATE DATABASE mailpulsedb WITH template=template0 owner=mailpulse;
\CONNECT mailpulsedb;

ALTER DEFAULT PRIVILEGES GRANT ALL ON tables TO mailpulse;

CREATE TABLE users(
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);