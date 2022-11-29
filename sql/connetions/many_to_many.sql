create table items(
	id serial primary key,
	name varchar(255)
);

create table customers(
	id serial primary key,
	name varchar(255)
);

create table customers_items(
	id serial primary key,
	customer_id int references customers(id),
	item_id int references items(id)
);

insert into items(name) values ('fridge');
insert into items(name) values ('phone');
insert into items(name) values ('dishwasher');

insert into customers(name) values ('Ivan');
insert into customers(name) values ('Vasiliy');
insert into customers(name) values ('Boris');

insert into customers_items(customer_id, item_id) values (1, 1);
insert into customers_items(customer_id, item_id) values (2, 3);
insert into customers_items(customer_id, item_id) values (3, 1);
insert into customers_items(customer_id, item_id) values (3, 2);
insert into customers_items(customer_id, item_id) values (3, 3);
