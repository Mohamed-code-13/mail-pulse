DROP DATABASE mailpulsedb;
DROP USER mailpulse;

CREATE USER mailpulse with password '123456';
CREATE DATABASE mailpulsedb WITH template=template0 owner=mailpulse;
\CONNECT mailpulsedb;

ALTER DEFAULT PRIVILEGES GRANT ALL ON tables TO mailpulse;

CREATE TABLE users(
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE emails (
	email_id SERIAL PRIMARY KEY,
	sender_id INTEGER NOT NULL,
	receiver_id INTEGER NOT NULL,
	subject VARCHAR(255) NOT NULL,
	description TEXT,
	priority INTEGER NOT NULL,
	FOREIGN KEY (sender_id) REFERENCES users(user_id),
	FOREIGN KEY (receiver_id) REFERENCES users(user_id)
);
