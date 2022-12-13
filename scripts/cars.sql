create table car_bodies(
id serial primary key,
name varchar(255)
);

create table car_engines(
id serial primary key,
name varchar(255)
);

create table car_transmissions(
id serial primary key,
name varchar(255)
);

create table cars(
id serial primary key,
name varchar(255),
body_id int references car_bodies(id),
engine_id int references car_engines(id),
transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('Sedan'), ('Hatchback'), ('Truck'), ('SUV'), 
('Minivan'), ('Convertible'), ('Coupe');

insert into car_engines(name) values ('L4 2.0'), ('V6 1,5'),
('V6 2.0'), ('V6 2.5'), ('V8 3.5'), ('V12 4.0');

insert into car_transmissions(name) values ('MT 120'), ('MT 130'),
('AT 110'), ('AT 135'), ('AT 140'), ('AT 150'), ('CVT 130');

insert into cars(name, body_id, engine_id, transmission_id) values ('Toyota Crown', 1, 1, 4);
insert into cars(name, body_id, engine_id, transmission_id) values ('Toyota RAV4', 4, 3, 2);
insert into cars(name, body_id, engine_id, transmission_id) values ('Toyota Alphard', 5, 4, 5);
insert into cars(name, body_id, engine_id, transmission_id) values ('Toyota Prius C', 2, 2, 7);
insert into cars(name, body_id, engine_id, transmission_id) values ('Toyota Hilux', 4, 4, 5);
insert into cars(name, body_id, engine_id, transmission_id) values ('Honda Odyssey', 5, 4, 3);
insert into cars(name, body_id, engine_id, transmission_id) values ('Toyota Tundra', 3, 5, 5);
insert into cars(name, body_id, engine_id, transmission_id) values ('Audi A4', 1, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values ('Honda Jazz', 2, 3, 2);
insert into cars(name, body_id, engine_id, transmission_id) values ('BMW X5', 4, 4, 5);

select c.id id, c.name car, cb.name as "body style", ce.name engine, ct.name transmission
from cars c
left join car_bodies cb on c.body_id = cb.id
left join car_engines ce on c.engine_id = ce.id
left join car_transmissions ct on c.transmission_id = ct.id; 

select cb.id id, cb.name as "body style"
from cars c
right join car_bodies cb on c.body_id = cb.id
where c.body_id is null; 

select ce.id id, ce.name engine
from car_engines ce
left join cars c on ce.id = c.engine_id
where c.engine_id is null; 

select ct.id id, ct.name transmission
from cars c
right join car_transmissions ct on c.transmission_id = ct.id
where c.transmission_id is null; 