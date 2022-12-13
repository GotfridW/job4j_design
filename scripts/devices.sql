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

insert into devices(name, price) values('iPhone', 15000);
insert into devices(name, price) values('Laptop', 20000);
insert into devices(name, price) values('Airpods', 3400);
insert into devices(name, price) values('Galaxy Tab', 11000);
insert into devices(name, price) values('Smart watch', 4900);
insert into devices(name, price) values('iMac', 26000);

insert into people(name) values ('Alex'), ('Peter'), ('Tom'), ('William'), ('Frank');

insert into devices_people(device_id, people_id) values (3, 1), (1, 1);
insert into devices_people(device_id, people_id) values (1, 2), (2, 2), (4, 2), (3, 2);
insert into devices_people(device_id, people_id) values (4, 3);
insert into devices_people(device_id, people_id) values (1, 4), (3, 4), (6, 4);
insert into devices_people(device_id, people_id) values (2, 5), (5, 5);

select avg(price) from devices;

select p.name, avg(d.price) from people p
join devices_people dp on dp.people_id = p.id
join devices d on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price) from people p
join devices_people dp on dp.people_id = p.id
join devices d on dp.device_id = d.id
group by p.name 
having avg(d.price) > 5000;
