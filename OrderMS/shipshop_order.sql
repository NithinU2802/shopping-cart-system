create database shipshop_order;

USE shipshop_order;

-- Create a Cart table to hold customer cart information
CREATE TABLE Cart (
    cart_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create a CartItem table to store individual items added to a cart
CREATE TABLE cart_item (
    cart_item_id INT AUTO_INCREMENT PRIMARY KEY,
    cart_id INT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cart_id) REFERENCES Cart(cart_id),
    FOREIGN KEY (product_id) REFERENCES shipshop_product.Product(id)
);


CREATE TABLE Coupon (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,             -- Auto-incrementing primary key for the coupon
    code VARCHAR(100) NOT NULL,                        -- The coupon code (required)
    discount_percentage DECIMAL(5, 2) NOT NULL,        -- Discount percentage applied by the coupon
    valid_until TIMESTAMP,                             -- The expiration date of the coupon
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,    -- Timestamp for when the coupon was created
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- Timestamp for last update
);

insert into Coupon(id, code, discount_percentage,valid_until) values
(1,'XVMKL231KZI',10.0,'2025-03-12 12:00:00'),
(2,'WDJFOHW42DH',12.0,'2025-03-14 12:00:00'),
(3,'FHWEI234FHE',8.0,'2025-03-16 12:00:00');



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


select * from customerOrders;

select * from orderitems;

select * from coupon;

SELECT SUM(total_amount) AS total_sales
FROM CustomerOrders
WHERE order_date >= NOW() - INTERVAL 300 MINUTE;
