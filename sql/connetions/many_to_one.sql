create table film(
	id serial primary key,
	name varchar(255)
);

create table actors(
	id serial primary key,
	name varchar(255),
	film_id int references film(id)
);

insert into film(name) values ('Gladiator');
insert into actors(name, film_id) values ('Russell Crowe', 1);

select * from actors;

select * from film where id in (select film_id from actors);
