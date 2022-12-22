CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers values (1, 'Bill', 'Clinton', 52, 'USA');
insert into customers values (2, 'Olaf', 'Sholtz', 55, 'Germany');
insert into customers values (3, 'Emmanuel', 'Macron', 51, 'France');
insert into customers values (4, 'Alexander', 'Lukashenko', 65, 'Belarus');
insert into customers values (5, 'Bill', 'Harrington', 52, 'USA');

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders values (1, 5, 1);
insert into orders values (2, 2, 3);
insert into orders values (3, 8, 4);
insert into orders values (5, 20, 5);

select * from customers
where age = (select MIN(age) from customers);

select * from customers
where id not in (select customer_id from orders);