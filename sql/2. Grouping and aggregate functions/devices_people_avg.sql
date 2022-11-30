create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price)
values ('PC', 100000), ('Mobile phone', 50000), ('Video game console', 40000);

insert into people(name) values ('Tom'), ('Michael'), ('John');

insert into devices_people(people_id, device_id)
values (1, 1), (1, 2), (1, 3), (2, 2), (3, 2), (3, 3);

select avg(d.price)
from devices d;

select p.name, avg(d.price)
from devices_people as dp
join devices d
on dp.device_id = d.id
join people p
on dp.people_id = p.id
group by p.name
having avg(d.price) > 50000;
