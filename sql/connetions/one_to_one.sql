create table uniqueID(
	id serial primary key,
	UID BigInt
);

create table users(
	id serial primary key,
	name varchar(255),
	uniqueID_id int references UniqueID(id) unique
);

insert into uniqueID(UID) values (46272492321);
insert into users(name, uniqueID_id) values ('Genadiy', 1);

select * from uniqueID where id in (select uniqueID_id from users);
