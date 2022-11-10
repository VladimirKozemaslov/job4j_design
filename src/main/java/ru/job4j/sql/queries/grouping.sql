create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values
('Xiaomi redmi 10C', 17800),
('Apple iphone 13', 86800),
('ZTE 4T', 16900),
('Huawei 10Pro', 22000),
('Nokia 3310', 2000);

insert into people(name) values
('Silvester Stallone'),
('Arnold Shwarznegger'),
('Leonardo DiCaprio'),
('Quentin Tarantino'),
('Mila Yovovich');

insert into devices_people(device_id, people_id) values
((select id from devices where name like 'Xia%'), (select id from people where name like 'Silv%')),
((select id from devices where name like 'Nok%'), (select id from people where name like 'Silv%')),
((select id from devices where name like 'Hua%'), (select id from people where name like 'Silv%')),
((select id from devices where name like 'App%'), (select id from people where name like 'Arn%')),
((select id from devices where name like 'Z%'), (select id from people where name like 'Arn%')),
((select id from devices where name like 'Hua%'), (select id from people where name like 'Arn%')),
((select id from devices where name like 'Hua%'), (select id from people where name like 'Leo%')),
((select id from devices where name like 'Nok%'), (select id from people where name like 'Leo%')),
((select id from devices where name like 'App%'), (select id from people where name like 'Que%')),
((select id from devices where name like 'Nok%'), (select id from people where name like 'Que%')),
((select id from devices where name like 'App%'), (select id from people where name like 'Mil%')),
((select id from devices where name like 'Xia%'), (select id from people where name like 'Mil%')),
((select id from devices where name like 'Hua%'), (select id from people where name like 'Mil%')),
((select id from devices where name like 'Z%'), (select id from people where name like 'Mil%'));

select avg(price) from devices;

select p.name as name, avg(d.price) from
    devices d inner join devices_people dp on d.id = dp.device_id
    inner join people p on dp.people_id = p.id
group by p.name;

select p.name as name, avg(d.price) from
    devices d inner join devices_people dp on d.id = dp.device_id
    inner join people p on dp.people_id = p.id
group by p.name
having avg(d.price) > 5000;