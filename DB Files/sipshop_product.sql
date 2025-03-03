create database shipshop_product;

use shipshop_product;

CREATE TABLE Product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Auto-incrementing primary key
    name VARCHAR(255) NOT NULL,             -- Product name (not nullable)
    category VARCHAR(100),                  -- Product category (nullable)
    manufacturer VARCHAR(255),              -- Manufacturer name (nullable)
    use_case VARCHAR(255),                  -- Product use case (nullable)
    price DECIMAL(10, 2) NOT NULL,          -- Price (required, with 2 decimal points)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Timestamp for creation (defaults to current timestamp)
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- Timestamp for last update
);


CREATE TABLE Inventory (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,             -- Auto-incrementing primary key
    product_id BIGINT NOT NULL,                       -- Foreign key referencing the product table
    quantity_in_stock INT NOT NULL,                   -- Quantity of the product available in stock
    quantity_sold INT DEFAULT 0,                      -- Quantity of the product sold (defaults to 0)
    last_replenishment_date TIMESTAMP,                -- Last date when the stock was replenished
    FOREIGN KEY (product_id) REFERENCES Product(id)  -- Foreign key constraint to ensure valid product_id
);
