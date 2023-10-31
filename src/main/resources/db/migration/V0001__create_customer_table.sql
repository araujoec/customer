CREATE TABLE IF NOT EXISTS customer (
    id SERIAL NOT NULL,
    name VARCHAR(255),
    document VARCHAR(11),
    email VARCHAR(255),
    PRIMARY KEY (id)
);