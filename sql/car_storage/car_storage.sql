create table car_bodies(
	id serial primary key,
	name text
);

create table car_engines(
	id serial primary key,
	name text
);

create table car_transmissions(
	id serial primary key,
	name text
);

create table cars(
	id serial primary key,
	name text,
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('sedan'), ('hatchback'), ('pickup'), ('open-top');
insert into car_engines(name) values ('engine1'), ('engine2'), ('engine3'), ('engine4');
insert into car_transmissions(name) values ('auto'), ('manual'), ('robot'), ('variator');
insert into cars(name, body_id, engine_id, transmission_id)
values ('volvo', 2, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id)
values ('bmw', 1, 2, 2);
insert into cars(name, body_id, engine_id, transmission_id)
values ('audi', 1, 3, 3);
insert into cars(name, body_id, engine_id, transmission_id)
values ('toyota', 3, null, null);
insert into cars(name, body_id, engine_id, transmission_id)
values ('nissan', null, 1, 2);

select c.id id, c.name car_name, b.name body_name,
e.name engine_name, t.name transmission_name
from cars c left join car_bodies b on c.body_id = b.id
left join car_engines e on c.engine_id = e.id
left join car_transmissions t on c.transmission_id = t.id;

select b.name body_name
from cars c right join car_bodies b on c.body_id = b.id
where c.id is null;

select e.name engine_name
from cars c right join car_engines e on c.engine_id = e.id
where c.id is null;

select e.name transmission_name
from cars c right join car_transmissions e on c.transmission_id = e.id
where c.id is null;
