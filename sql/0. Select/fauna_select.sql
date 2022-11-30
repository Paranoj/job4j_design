create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) 
values ('Australian box jellyfish', 3, '1956-01-01');
insert into fauna(name, avg_age, discovery_date) 
values ('Arctic wolf', 15, '1935-01-01');
insert into fauna(name, avg_age, discovery_date) 
values ('Polar bear', 28, '1774-01-01');
insert into fauna(name, avg_age, discovery_date) 
values ('Clownfish', 6, null);
insert into fauna(name, avg_age, discovery_date) 
values ('Venus flower basket', 10000, '1841-01-01');

select * from fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';
