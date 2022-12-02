create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products(name, producer, price) values ('x', 'y', 5);
insert into products(name, producer, count, price) values ('x', 'y', 3, 5);
select * from products;
drop table products;

create or replace procedure delete_data(u_id integer)
language 'plpgsql'
as $$
    BEGIN	
		delete from products where id = u_id or count = 0;
    END;
$$;

call delete_data(2);

create or replace function f_delete_data(u_id integer)
returns void
language 'plpgsql'
as
$$
    begin
		delete from products where id = u_id or count = 0;
    end;
$$;

select f_delete_data(2);
