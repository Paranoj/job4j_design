create table roles(
	id serial primary key,
	name text
);

create table users(
	id serial primary key,
    name text,
	roles_id int references roles(id)
);

create table rules(
	id serial primary key,
	name text
);

create table roles_rules(
	id serial primary key,
	roles_id int references roles(id),
	rules_id int references rules(id)
);

create table category(
	id serial primary key,
	name text
);

create table states(
	id serial primary key,
	name text
);

create table item(
	id serial primary key,
	name text,
	users_id int references users(id),
	category_id int references category(id),
	states_id int references states(id)
);

create table discuss(
	id serial primary key,
	name text,
	item_id int references item(id)
);

create table attachs(
	id serial primary key,
	name text,
	item_id int references item(id)
);
