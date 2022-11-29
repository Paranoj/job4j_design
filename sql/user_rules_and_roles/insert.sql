insert into roles(name) values ('admin');
insert into roles(name) values ('user');
insert into roles(name) values ('moderator');

insert into users(name, roles_id) values ('George', 1);
insert into users(name, roles_id) values ('Michael', 2);
insert into users(name, roles_id) values ('Helena', 3);

insert into rules(name) values ('Complete access');
insert into rules(name) values ('CRUD for user himself');
insert into rules(name) values ('CRUD for other users');

insert into roles_rules(roles_id, rules_id) values (1, 1);
insert into roles_rules(roles_id, rules_id) values (2, 2);
insert into roles_rules(roles_id, rules_id) values (3, 3);

insert into category(name) values ('medicine');
insert into category(name) values ('engineering');
insert into category(name) values ('computer science');

insert into states(name) values ('sent');
insert into states(name) values ('read');
insert into states(name) values ('edited');

insert into item(users_id, category_id, states_id) values (1, 3, 1);
insert into item(users_id, category_id, states_id) values (2, 1, 3);
insert into item(users_id, category_id, states_id) values (3, 2, 2);

insert into discuss(name, item_id) values ('Java tips', 1);
insert into discuss(name, item_id) values ('Sensor adjustment', 3);
insert into discuss(name, item_id) values ('Parkinson disease', 2);

insert into attachs(name, item_id) values ('tips.jpg', 1);
insert into attachs(name, item_id) values ('manual.pdf', 3);
insert into discuss(name, item_id) values ('Parkinson.docx', 2);
