create table roles(
	id serial primary key,
	name text
);

create table users(
	id serial primary key,
	name text,
	roles_id int references roles(id)
);

insert into roles(name) values('administrator');
insert into roles(name) values('moderator');
insert into roles(name) values('user');

insert into users(name, roles_id) values('Tom', 1);
insert into users(name, roles_id) values('Alex', 2);
insert into users(name, roles_id) values('Max', 3);
insert into users(name) values('Joshua');

select r.name, u.name
from users as u join roles as r on u.roles_id = r.id;

select r.name Роль, u.name Пользователь
from users as u join roles as r on u.roles_id = r.id;

select r.name as "Наименование Роли", u.name as "Имя Пользователя"
from users as u join roles as r on u.roles_id = r.id;
