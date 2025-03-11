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

insert into Product(id,name,category,manufacturer,use_case,price)
values(1001,'product1','cat1','manu1','usc1',1025.21),
(1002,'product2','cat2','manu1','usc2',2999.10);


CREATE TABLE Inventory (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,             -- Auto-incrementing primary key
    product_id BIGINT NOT NULL,                       -- Foreign key referencing the product table
    quantity_in_stock INT NOT NULL,                   -- Quantity of the product available in stock
    quantity_sold INT DEFAULT 0,                      -- Quantity of the product sold (defaults to 0)
    last_replenishment_date TIMESTAMP,                -- Last date when the stock was replenished
    FOREIGN KEY (product_id) REFERENCES Product(id)  -- Foreign key constraint to ensure valid product_id
);

INSERT INTO Inventory (id, product_id, quantity_in_stock, quantity_sold, last_replenishment_date)
VALUES
(1,1001, 100, 0, '2025-03-01 10:00:00'),  
(2, 1002, 50, 10, '2025-03-02 12:30:00'); 

select * from inventory;
