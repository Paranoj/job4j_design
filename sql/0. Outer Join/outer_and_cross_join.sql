create table departments(
	id serial primary key,
	name text
);

create table employees(
	id serial primary key,
	name text,
	department_id int references departments(id)
);

insert into departments(name) values ('hr'), ('management'),
('application development'), ('security'), ('user support & service');
insert into employees(name, department_id) values ('Tom', 3), ('Helena', 1),
('Emily', 4);

select * from departments d full join employees e on e.department_id = d.id;

select * from departments d left join employees e 
on e.department_id = d.id where e.id is null;

select e.name, d.name from departments d left join employees e on e.department_id = d.id
group by e.name, d.name;
select e.name, d.name from employees e right join departments d on e.department_id = d.id
group by e.name, d.name;

/* gender = true - man;
gender = false  - woman */

create table teens(
	id serial primary key,
	name text,
	gender bool
);

insert into teens(name,  gender) values ('Joseph', true), ('Anna', false),
('Max', true), ('Jolene', false), ('Steven', true), ('Maria', false);

select g1.name Name1, g2.name Name2, (g1.gender or g2.gender) as "couple"
from teens g1 cross join teens g2
where g1.name != g2.name and (g1.gender = false or g2.gender = false)
and (g1.gender or g2.gender) != false;
