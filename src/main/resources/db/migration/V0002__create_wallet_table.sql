CREATE TABLE IF NOT EXISTS wallet (
    customer_id SERIAL NOT NULL,
    balance DECIMAL,
    paper DECIMAL,
    FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);