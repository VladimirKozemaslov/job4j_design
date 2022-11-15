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
        set price = price + price * 0.2
        where id in (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

create or replace function taxBefore()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger taxBefore_trigger
    before insert
    on products
    for each row
    execute procedure taxBefore();

insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 5, 140);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 11, 130);

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function addToHistory()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date)
        values
        ((select name from inserted), (select price from inserted), CURRENT_TIMESTAMP);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger addToHistory_trigger
    after insert on products
    referencing new table
    for each statement
    execute procedure addToHistory();

insert into products (name, producer, count, price) VALUES ('product_6', 'producer_2', 11, 200);
insert into products (name, producer, count, price) VALUES ('product_7', 'producer_2', 19, 300);

select * from products;

select * from history_of_price;




