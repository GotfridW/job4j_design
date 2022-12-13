create table brands(
id serial primary key,
name varchar(255)
);

create table body_styles(
id serial primary key,
name varchar(255)
);

create table brands_body_styles(
id serial primary key,
brand_id int references brands(id),
body_style_id int references body_styles(id)
);

insert into brands(name) values ('Toyota');
insert into brands(name) values ('Nissan');
insert into brands(name) values ('Mitsubishi');

insert into body_styles(name) values ('sedan');
insert into body_styles(name) values ('minivan');
insert into body_styles(name) values ('SUV');

insert into brands_body_styles(brand_id, body_style_id) values (1, 1);
insert into brands_body_styles(brand_id, body_style_id) values (2, 3);
insert into brands_body_styles(brand_id, body_style_id) values (3, 1);
insert into brands_body_styles(brand_id, body_style_id) values (2, 2);