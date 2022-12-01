create table students (
    id serial primary key,
    name varchar(50)
);

insert into students (name) values ('Иван Иванов');
insert into students (name) values ('Петр Петров');

create table authors (
    id serial primary key,
    name varchar(50)
);

insert into authors (name) values ('Александр Пушкин');
insert into authors (name) values ('Николай Гоголь');
insert into authors (name) values ('Александр Грибоедов');

create table books (
    id serial primary key,
    name varchar(200),
    author_id integer references authors(id)
);

insert into books (name, author_id) values ('Евгений Онегин', 1);
insert into books (name, author_id) values ('Капитанская дочка', 1);
insert into books (name, author_id) values ('Дубровский', 1);
insert into books (name, author_id) values ('Мертвые души', 2);
insert into books (name, author_id) values ('Вий', 2);
insert into books (name, author_id) values ('Тарас Бульба', 2);

create table orders (
    id serial primary key,
    active boolean default true,
    book_id integer references books(id),
    student_id integer references students(id)
);

insert into orders (book_id, student_id) values (1, 1);
insert into orders (book_id, student_id) values (3, 1);
insert into orders (book_id, student_id) values (5, 2);
insert into orders (book_id, student_id) values (4, 1);
insert into orders (book_id, student_id) values (2, 2);

select s.name student, b.name book, count(s.name), a.name author
	from students as s
    join orders o on s.id = o.student_id
    right join books b on o.book_id = b.id
    right join authors a on b.author_id = a.id
	where o.book_id is null
	or b.name like '%души%'
	or b.author_id is null
	or a.name like 'А%'
    group by (s.name, a.name, b.name)
	having count(s.name) < 2;
	
create view poor_imagination
	as select s.name student, b.name book, count(s.name), a.name author
		from students as s
    	join orders o on s.id = o.student_id
    	right join books b on o.book_id = b.id
    	right join authors a on b.author_id = a.id
		where o.book_id is null
		or b.name like '%души%'
		or b.author_id is null
		or a.name like 'А%'
    	group by (s.name, a.name, b.name)
		having count(s.name) < 2;
		
select * from poor_imagination;
