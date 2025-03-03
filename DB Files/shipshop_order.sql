create database shipshop_order;

use shipshop_order;

-- CREATE TABLE Cart (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,             -- Auto-incrementing primary key for the cart
--     customer_id BIGINT NOT NULL,                       -- Foreign key referencing the customer table
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,    -- Timestamp for when the cart was created
--     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Timestamp for last update
--     total_price DECIMAL(10, 2) NOT NULL,               -- Total price of the cart (calculated)
--     FOREIGN KEY (customer_id) REFERENCES shipshop_customer.Customer(id) -- Foreign key constraint referencing Customer table
-- );



-- CREATE TABLE OrderItem (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,             -- Auto-incrementing primary key for the order item
--     cart_id BIGINT NOT NULL,                           -- Foreign key referencing the cart table
--     product_id BIGINT NOT NULL,                        -- Foreign key referencing the product table
--     quantity INT NOT NULL,                             -- Quantity of the product in the cart
--     price_per_unit DECIMAL(10, 2) NOT NULL,            -- Price per unit of the product at the time of adding to the cart
--     FOREIGN KEY (cart_id) REFERENCES Cart(id),        -- Foreign key constraint referencing the Cart table
--     FOREIGN KEY (product_id) REFERENCES shipshop_product.Product(id)   -- Foreign key constraint referencing the Product table
-- );

CREATE TABLE Coupon (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,             -- Auto-incrementing primary key for the coupon
    code VARCHAR(100) NOT NULL,                        -- The coupon code (required)
    discount_percentage DECIMAL(5, 2) NOT NULL,        -- Discount percentage applied by the coupon
    valid_until TIMESTAMP,                             -- The expiration date of the coupon
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,    -- Timestamp for when the coupon was created
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- Timestamp for last update
);


CREATE TABLE CustomerOrders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,            -- Auto-incrementing primary key for the order
    customer_id BIGINT NOT NULL,                            -- Foreign key linking to the Customers table
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,      -- Date and time when the order was placed
    total_amount DECIMAL(10, 2) NOT NULL,                -- Total value of the order
    order_status VARCHAR(50) NOT NULL,                   -- Status of the order (e.g., placed, shipped, delivered)
    FOREIGN KEY (customer_id) REFERENCES shipshop_customer.Customer(id)  -- Foreign key constraint to the Customers table
);


CREATE TABLE OrderItems (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,       -- Auto-incrementing primary key for the order item
    order_id INT NOT NULL,                               -- Foreign key linking to the Orders table
    product_id BIGINT NOT NULL,                             -- Foreign key linking to the Products table
    quantity INT NOT NULL,                               -- Quantity of the product ordered
    price DECIMAL(10, 2) NOT NULL,                       -- Price of the product at the time of the order
    FOREIGN KEY (order_id) REFERENCES CustomerOrders(order_id),  -- Foreign key constraint to the CustomerOrders table
    FOREIGN KEY (product_id) REFERENCES shipshop_product.Product(id)    -- Foreign key constraint to the Products table
);
