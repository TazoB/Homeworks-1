CREATE TABLE item (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    description TEXT,
    submission_time TIMESTAMP NOT NULL DEFAULT NOW(),
    photo_url TEXT
);