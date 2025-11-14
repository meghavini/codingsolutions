-- File: SQL/schema_and_queries.sql

-- Tables
CREATE TABLE Customers (
    customer_id INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100)
);

CREATE TABLE Orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    order_date DATE,
    total DECIMAL(10,2),
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

-- Sample inserts
INSERT INTO Customers(customer_id, name, email) VALUES (1, 'Alice', 'alice@example.com');
INSERT INTO Customers(customer_id, name, email) VALUES (2, 'Bob', 'bob@example.com');

INSERT INTO Orders(order_id, customer_id, order_date, total) VALUES (101, 1, '2025-10-01', 199.99);
INSERT INTO Orders(order_id, customer_id, order_date, total) VALUES (102, 1, '2025-10-05', 59.50);
INSERT INTO Orders(order_id, customer_id, order_date, total) VALUES (103, 2, '2025-10-07', 300.00);

-- Useful queries
-- 1) Customer orders with totals
SELECT c.customer_id, c.name, o.order_id, o.order_date, o.total
FROM Customers c JOIN Orders o ON c.customer_id = o.customer_id
ORDER BY c.customer_id, o.order_date;

-- 2) Total spend per customer
SELECT c.customer_id, c.name, SUM(o.total) AS total_spent
FROM Customers c JOIN Orders o ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.name
ORDER BY total_spent DESC;

-- 3) Index for orders by date
CREATE INDEX idx_orders_date ON Orders(order_date);

-- 4) Example stored procedure (MySQL style)
DELIMITER //
CREATE PROCEDURE GetCustomerOrders(IN cid INT)
BEGIN
    SELECT * FROM Orders WHERE customer_id = cid;
END;
//
DELIMITER ;
