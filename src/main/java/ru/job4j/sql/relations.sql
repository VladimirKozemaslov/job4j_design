-- many_to_one
create table manufacturers (
	id serial primary key,
	name varchar(30)
);

insert into manufacturers(name) values ('Volkswagen');
insert into manufacturers(name) values ('Skoda');
insert into manufacturers(name) values ('Toyota');
insert into manufacturers(name) values ('Renault');

select * from manufacturers;

create table marks (
	id serial primary key,
	name varchar(50),
	manufacturer integer references manufacturers(id)
);

insert into marks(name, manufacturer) values ('Polo', 1);
insert into marks(name, manufacturer) values ('Jetta', 1);
insert into marks(name, manufacturer) values ('Rapid', 2);
insert into marks(name, manufacturer) values ('Octavia', 2);
insert into marks(name, manufacturer) values ('Corolla', 3);
insert into marks(name, manufacturer) values ('Camry', 3);
insert into marks(name, manufacturer) values ('Logan', 4);
insert into marks(name, manufacturer) values ('Duster', 4);

select * from marks;

-- one_to_one
create table state_nums (
	id serial primary key,
	num varchar(11) unique
);

insert into state_nums(num) values ('А 001 АА102');
insert into state_nums(num) values ('А 002 АА102');
insert into state_nums(num) values ('А 003 АА102');
insert into state_nums(num) values ('Б 111 ББ102');
insert into state_nums(num) values ('Б 112 ББ102');
insert into state_nums(num) values ('Б 113 ББ102');
insert into state_nums(num) values ('В 333 ВВ102');
insert into state_nums(num) values ('В 334 ВВ102');
insert into state_nums(num) values ('В 335 ВВ102');
insert into state_nums(num) values ('В 336 ВВ102');
insert into state_nums(num) values ('В 337 ВВ102');
insert into state_nums(num) values ('В 338 ВВ102');
insert into state_nums(num) values ('В 339 ВВ102');
insert into state_nums(num) values ('В 340 ВВ102');
insert into state_nums(num) values ('В 341 ВВ102');
insert into state_nums(num) values ('В 342 ВВ102');

select * from state_nums;

create table cars (
	id serial primary key,
	mark integer references marks(id),
	state_num integer references state_nums(id) unique
);

insert into cars(mark, state_num) values (4, 1);
insert into cars(mark, state_num) values (2, 2);
insert into cars(mark, state_num) values (7, 3);
insert into cars(mark, state_num) values (7, 4);
insert into cars(mark, state_num) values (1, 5);
insert into cars(mark, state_num) values (3, 6);
insert into cars(mark, state_num) values (3, 7);
insert into cars(mark, state_num) values (5, 8);
insert into cars(mark, state_num) values (6, 9);
insert into cars(mark, state_num) values (6, 10);
insert into cars(mark, state_num) values (6, 11);
insert into cars(mark, state_num) values (8, 12);
insert into cars(mark, state_num) values (8, 13);
insert into cars(mark, state_num) values (7, 14);
insert into cars(mark, state_num) values (7, 15);
insert into cars(mark, state_num) values (1, 16);

select * from cars;

-- many_to_many
create table dealers (
	id serial primary key,
	name varchar(50)
);

insert into dealers(name) values ('Барс авто');
insert into dealers(name) values ('МС моторс');
insert into dealers(name) values ('Автодом');
insert into dealers(name) values ('Кристалл моторс');

select * from dealers;

create table manufacturers_dealers (
	id serial primary key,
	manufacturer integer references manufacturers(id),
	dealer integer references dealers(id)
);

insert into manufacturers_dealers(manufacturer, dealer) values (1, 1);
insert into manufacturers_dealers(manufacturer, dealer) values (1, 2);
insert into manufacturers_dealers(manufacturer, dealer) values (2, 1);
insert into manufacturers_dealers(manufacturer, dealer) values (2, 2);
insert into manufacturers_dealers(manufacturer, dealer) values (3, 3);
insert into manufacturers_dealers(manufacturer, dealer) values (3, 4);
insert into manufacturers_dealers(manufacturer, dealer) values (4, 2);
insert into manufacturers_dealers(manufacturer, dealer) values (4, 4);

select * from manufacturers_dealers;