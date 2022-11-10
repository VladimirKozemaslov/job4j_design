create table orders(
    id serial primary key,
    delivery_address varchar(100)
);

create table items(
    id serial primary key,
    name varchar(30),
    description varchar(1000),
    price integer
);

create table orders_items(
    id serial primary key,
    c_order int references orders(id),
    c_item int references items(id)
);

insert into items(name, description, price) values
('Black bread', 'Borodinsky bread with cumin', 58),
('Lemonade', 'Buratino, 1,5 L', 43),
('Meat dumplings', 'Miratorg 800g', 205),
('Carrot', 'Uncleaned, 1 kg', 36),
('Twix Max', 'Delicious taste, double size', 78),
('Cabbage', 'White', 51),
('Pickles', '0.8 L', 74);

insert into orders(delivery_address) values
('Moscow, Lenin st.3 room 41'),
('Saint Petersburg, Sennaya st.9 room 125'),
('Chelyabinsk, Traktornaya st.9 room 3');

insert into orders_items(c_order, c_item) values
((select id from orders where delivery_address like 'Moscow%'), (select id from items where name like 'Twix%')),
((select id from orders where delivery_address like 'Moscow%'), (select id from items where name like 'Meat%')),
((select id from orders where delivery_address like 'Moscow%'), (select id from items where name like 'Pick%')),
((select id from orders where delivery_address like 'Saint%'), (select id from items where name like 'Black%')),
((select id from orders where delivery_address like 'Saint%'), (select id from items where name like 'Lemo%')),
((select id from orders where delivery_address like 'Saint%'), (select id from items where name like 'Carr%')),
((select id from orders where delivery_address like 'Saint%'), (select id from items where name like 'Meat%')),
((select id from orders where delivery_address like 'Chelya%'), (select id from items where name like 'Cabb%')),
((select id from orders where delivery_address like 'Chelya%'), (select id from items where name like 'Pick%')),
((select id from orders where delivery_address like 'Chelya%'), (select id from items where name like 'Meat%')),
((select id from orders where delivery_address like 'Chelya%'), (select id from items where name like 'Black%'));

select * from orders_items;

select o.delivery_address as Address, i.name as Item, i.description as Description, i.price as Price from
    items i inner join orders_items oi on i.id = oi.c_item
    inner join orders o on oi.c_order = o.id;

select o.delivery_address as Address, i.name as Item, i.description as Description, i.price as Price from
    items i inner join orders_items oi on i.id = oi.c_item
    inner join orders o on oi.c_order = o.id
where i.price < 90;

select o.delivery_address as Address, i.name as Item, i.description as Description, i.price as Price from
    items i inner join orders_items oi on i.id = oi.c_item
    inner join orders o on oi.c_order = o.id
where i.price < 90 and o.delivery_address like 'M%';