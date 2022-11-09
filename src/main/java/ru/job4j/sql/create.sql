create table roles (
	id serial primary key,
	name varchar(30)
);

create table rights (
	id serial primary key,
	name varchar(30)
);

create table rights_roles (
	id serial primary key,
	role integer references roles(id),
	c_right integer references rights(id)
);

create table users (
	id serial primary key,
	name varchar(30),
	role integer references roles(id)
);

create table categories (
	id serial primary key,
	name varchar(30)
);

create table states (
	id serial primary key,
	name varchar(30)
);

create table items (
    id serial primary key,
	name varchar(30),
	c_user integer references users(id),
	category integer references categories(id),
	state integer references states(id)
);

create table attachments (
	id serial primary key,
	content varchar(10000),
	item integer references items(id)
);

create table comments (
	id serial primary key,
	text varchar(1000),
	item integer references items(id)
);