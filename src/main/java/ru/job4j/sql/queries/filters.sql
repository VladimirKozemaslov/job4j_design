create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id integer references type(id),
    expired_date date,
    price integer
);

insert into type(name) values
('СЫР'), ('МАСЛО'), ('МОЛОКО'), ('ХЛЕБ'), ('МОЛОЧНЫЕ ПРОДУКТЫ');

insert into product(name, type_id, expired_date, price) values
('Масло сливочное 65%', (select id from type where name = 'МАСЛО'), '2022-11-20', 115),
('Сыр Lamber', (select id from type where name = 'СЫР'), '2022-12-05', 780),
('Сыр Пошехонский', (select id from type where name = 'СЫР'), '2022-10-15', 89),
('Хлеб Бородинский', (select id from type where name = 'ХЛЕБ'), '2022-11-08', 51),
('Хлеб пшеничный', (select id from type where name = 'ХЛЕБ'), '2022-11-15', 55),
('Молоко Веселый Молочник', (select id from type where name = 'МОЛОКО'), '2022-11-09', 81),
('Молоко ульрапастеризованное', (select id from type where name = 'МОЛОКО'), '2022-11-13', 95),
('Мороженое рожок', (select id from type where name = 'МОЛОЧНЫЕ ПРОДУКТЫ'), '2022-11-14', 120),
('Мороженое стакан', (select id from type where name = 'МОЛОЧНЫЕ ПРОДУКТЫ'), '2022-11-14', 80);

select * from product p inner join type t on p.type_id = t.id
where t.name = 'СЫР';

select * from product p inner join type t on p.type_id = t.id
where p.name like '%Мороженое%';

select * from product p inner join type t on p.type_id = t.id
where p.expired_date < current_date;

select * from product where price = (select max(price) from product);

select t.name, count(1) from product p inner join type t on p.type_id = t.id
group by t.name;

select p.* from product p inner join type t on p.type_id = t.id
where t.name in ('СЫР', 'МОЛОКО');

select t.name, count(1) from product p inner join type t on p.type_id = t.id
group by t.name
having count(1) < 10;

select p.*, t.name as type from product p inner join type t on p.type_id = t.id;


