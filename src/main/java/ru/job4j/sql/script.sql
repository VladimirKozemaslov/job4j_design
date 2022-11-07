create table cars (
	id serial primary key,
	mark varchar(30),
	num integer,
	production_date date
);

insert into cars(mark, num, production_date) values 
('Skoda Octavia A7', 321, to_date('2014-14-09', 'YYYY-DD-MM'));

select * from cars;

update cars set production_date = to_date('2014-25-09', 'YYYY-DD-MM');

select * from cars;

delete from cars;

commit;

