CREATE TABLE IF NOT EXISTS balance (
    customer_id VARCHAR(255) NOT NULL,
    balance DECIMAL,
    FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);