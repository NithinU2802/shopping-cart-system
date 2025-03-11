create database shipshop_customer;

use shipshop_customer;

CREATE TABLE Customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,            -- Auto-incrementing primary key for the customer
    name VARCHAR(255) NOT NULL,                       -- Customer name (required)
    email VARCHAR(255) UNIQUE NOT NULL,               -- Customer email (required, unique)
    phone_number VARCHAR(20),                         -- Customer phone number (optional)
    address VARCHAR(255),                             -- Customer address (optional)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,   -- Timestamp of when the customer was created
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- Timestamp for last update
);

 insert into customer(id,name,email,phone_number,address) values(1,"JohnTheDon","john420@mp.com","420420","FurfuriNagar");

select * from customer;