create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

start transaction;
insert into products (name, producer, count, price) VALUES  ('product_4', 'producer_4', 11, 64);
commit;
select * from products;

start transaction;
insert into products (name, producer, count, price) VALUES  ('product_5', 'producer_5', 17, 64);
savepoint first_savepoint;
delete from products where price = 115;
update products set price = 75 where name = 'product_1';
select * from products;

rollback to first_savepoint;
select * from products;
commit;

start transaction;
savepoint first_savepoint;
update products set price = 77 where count > 15;
insert into products (name, producer, count, price) values ('product_6', 'producer_6', 1, 999);
savepoint second_savepoint;
insert into products (name, producer, count, price) values ('product_7', 'producer_7', 2, 555);
select * from products;
release savepoint second_savepoint;
select * from products;
rollback to second_savepoint;
rollback to first_savepoint;
select * from products;