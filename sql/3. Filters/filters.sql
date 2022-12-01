create table type(
	id serial primary key,
	name text
);

create table product(
	id serial primary key,
	name text,
	type_id int references type(id),
	expired_date date,
	price float
);

insert into type(name) values ('СЫР');
insert into type(name) values ('МОЛОКО');
insert into type(name) values ('МОРОЖЕНОЕ');

insert into product(name, type_id, expired_date, price) values
('Пармезан', 1, '01-01-2023', 299.5);
insert into product(name, type_id, expired_date, price) values
('Зеленый луг 2,5%', 2, '10-12-2022', 59.99);
insert into product(name, type_id, expired_date, price) values
('Мороженое Русский Холод', 3, '03-01-2023', 159.99);
insert into product(name, type_id, expired_date, price) values
('Мороженое Alpen Gold', 3, '21-11-2022', 49.99);
insert into product(name, type_id, expired_date, price) values
('Мороженое Maxibon', 3, '03-02-2023', 69.99);
insert into product(name, type_id, expired_date, price) values
('Мороженое Slendy черника', 3, '22-01-2023', 39.99);
insert into product(name, type_id, expired_date, price) values
('Мороженое Slendy сгущённое молоко', 3, '22-01-2023', 39.99);
insert into product(name, type_id, expired_date, price) values
('Мороженое Slendy клубника', 3, '22-01-2023', 39.99);
insert into product(name, type_id, expired_date, price) values
('Мороженое Slendy кедровый орех', 3, '22-01-2023', 39.99);
insert into product(name, type_id, expired_date, price) values
('Мороженое Slendy шоколадная крошка', 3, '22-01-2023', 39.99);
insert into product(name, type_id, expired_date, price) values
('Мороженое Slendy фисташка', 3, '22-01-2023', 39.99);
insert into product(name, type_id, expired_date, price) values
('Мороженое Slendy лесной орех', 3, '22-01-2023', 39.99);


select * from product
join type
on product.type_id = type.id
where type.name like '%СЫР%';

select * from product
where name like '%Мороженое%';

select * from product
where expired_date < current_date;

select max(price) from product;

select t.name Имя_типа, count(p.id) Количество
from product p
join type t
on p.type_id = t.id
group by t.name;

select * from product
join type
on product.type_id = type.id
where type.name like '%СЫР%' or type.name like '%МОЛОКО%';

select t.name Имя_типа, count(p.id) Количество
from product p
join type t
on p.type_id = t.id
group by t.name
having p.count < 10;

select p.name as "Наименование продукта", t.name as "Наименование типа"
from product p
join type t
on p.type_id = t.id;
