create table roles(
id serial primary key,
name varchar(255)
);

create table users(
id serial primary key,
name varchar(255),
role_id int references roles(id)
);

create table rules(
id serial primary key,
name varchar(255)
);

create table roles_rules(
id serial primary key,
role_id int references roles(id),
rule_id int references rules(id)
);

create table state(
id serial primary key,
name varchar(255)
);

create table category(
id serial primary key,
name varchar(255)
);

create table items(
id serial primary key,
item int,
user_id int references users(id),
state_id int references state(id),
category int references category(id)
);

create table comments(
id serial primary key,
comment text,
item_id int references items(id)
);

create table attachs(
id serial primary key,
attach text,
item_id int references items(id)
);