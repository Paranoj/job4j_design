create table details (
    id serial primary key,
    name varchar(50),
    count integer default 0,
    price integer,
	manufacturing_date date
);

insert into details (name, count, price, manufacturing_date)
VALUES ('steering rack', 3, 2999, '02-04-2022'), ('brake pad', 2, 4999, '10-08-2021'),
('transmission clutch', 1, 11999, '01-12-2019');
