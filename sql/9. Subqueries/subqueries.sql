CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

INSERT INTO customers (first_name, last_name, age, country) VALUES
('Ivan', 'Ivanov', 44, 'Russia'), ('Jane', 'Doe', 22, 'USA'), ('Christopher', 'Robin', 6, 'USA');

SELECT * FROM customers WHERE age = (SELECT MIN(age) FROM customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

INSERT INTO orders (amount, customer_id) VALUES (2, 1), (1, 2);	

SELECT * FROM customers WHERE customers.id NOT IN (SELECT orders.id FROM orders);
