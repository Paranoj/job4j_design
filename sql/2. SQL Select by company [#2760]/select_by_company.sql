CREATE TABLE company(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(id, name) values (1, 'Компания А'); 
insert into company(id, name) values (2, 'Компания Б'); 
insert into company(id, name) values (3, 'Компания В'); 
insert into company(id, name) values (4, 'Компания Г'); 
insert into company(id, name) values (5, 'Компания Д'); 

insert into person(id, name, company_id) values (1, 'Иван', 1);
insert into person(id, name, company_id) values (2, 'Алексей', 2);
insert into person(id, name, company_id) values (3, 'Владимир', 3);
insert into person(id, name, company_id) values (4, 'Мария', 4);
insert into person(id, name, company_id) values (5, 'Ксения', 5);
insert into person(id, name, company_id) values (6, 'Наталья', 5);
insert into person(id, name, company_id) values (7, 'Екатерина', 4);

select p.name Имена, c.name as "Название компании"
from person p
join company c
on p.company_id = c.id
where company_id != 5;

select c.name as "Название компании", count(*) as "Количество человек"
from person p
join company c
on p.company_id = c.id
group by c.name
having count(*) = (
	select max(m.m_id) from (
		select company_id, count(*) as m_id
		from person
		group by company_id) as m);
		