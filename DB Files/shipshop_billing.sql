create database shipshop_billing;

use shipshop_billing;

CREATE TABLE Billing (
    billing_id INT AUTO_INCREMENT PRIMARY KEY,            -- Auto-incrementing primary key for the billing record
    order_id INT NOT NULL,                                 -- Foreign key linking to the Orders table
    discount DECIMAL(10, 2) DEFAULT 0.00,                  -- Discount applied to the order (default is 0)
    final_amount DECIMAL(10, 2) NOT NULL,                  -- Final amount after applying the discount
    payment_method VARCHAR(50) NOT NULL,                   -- Method of payment (e.g., credit card, PayPal)
    payment_status VARCHAR(50) NOT NULL,                   -- Payment status (e.g., paid, pending)
    coupon_id BIGINT DEFAULT NULL,                            -- Foreign key linking to the Coupon table (nullable if no coupon applied)
    FOREIGN KEY (order_id) REFERENCES shipshop_order.CustomerOrders(order_id),  -- Foreign key constraint to the CustomerOrders table
    FOREIGN KEY (coupon_id) REFERENCES shipshop_order.Coupon(id)         -- Foreign key constraint to the Coupon table (nullable)
);
