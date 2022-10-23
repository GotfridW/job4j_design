create table type(
    id serial primary key,
    name varchar(255)
);

create table animals(
    id serial primary key,
    name varchar(255),
    type_id int references type(id)
);

insert into type(name) values ('birds');
insert into type(name) values ('reptiles');
insert into animals(name, type_id) VALUES ('Eagle', 1);
insert into animals(name, type_id) VALUES ('Python', 2);