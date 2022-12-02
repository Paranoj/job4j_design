create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.13
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';
	
create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();
	
create or replace function tax_before()
    returns trigger as
$$
    BEGIN
        NEW.price = NEW.price + NEW.price * 0.13;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';
	
create trigger tax_before_trigger
    before insert
    on products
    for each row
    execute procedure tax_before();

create table history_of_price(
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function transfer_to_history()
    returns trigger as
$$
    BEGIN
		insert into history_of_price(name, price, date)
		values (NEW.name, NEW.price, current_date);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger transfer_price_history
    after insert
    on products
    for each row
    execute procedure transfer_to_history();
	
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

select * from products;
drop trigger tax_before_trigger on products;
drop trigger transfer_price_history on products;
drop table products;
select * from history_of_price;