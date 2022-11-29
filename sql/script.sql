create table processors(
	id serial primary key,
	name varchar(255),
	cores int,
	socket text
);
insert into processors(name, cores, socket) values ('AMD Ryzen 5 5600X OEM',
'6', 'AM4');
select * from processors;
update processors set name = 'Intel Core i5-11400F';
update processors set socket = 'LGA 1200';
select * from processors;
delete from processors;
select * from processors;
