CREATE DATABASE online_store;
USE online_store;

CREATE TABLE products (
    product_id INT PRIMARY KEY,
    product_name VARCHAR(100),
    category VARCHAR(50),
    price DOUBLE,
    stock INT
);


