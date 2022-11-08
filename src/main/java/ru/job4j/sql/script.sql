create table cars (
	id serial primary key,
	mark varchar(30),
	num integer,
	production_date date
);

insert into cars(mark, num, production_date) values 
('Skoda Octavia A7', 321, '2014-09-14');

select * from cars;

update cars set production_date = '2014-09-14';

select * from cars;

delete from cars;

commit;

