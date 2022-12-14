create table users(
	id serial primary key,
	name varchar,
	age integer
);

insert into users(name, age) values ('Иван', 55), ('Владимир', 44), ('Максим', 33), ('Иван', 22);

select name, age
from users
where age = (select max(age) from users);

select name, count(*)
from users
group by name
having count(*) > 1;
