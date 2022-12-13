create table car(
id serial primary key,
brand text,
prod_year varchar(4),
cc decimal(2, 1),
four_wheel_drive boolean
);
insert into car
(brand, prod_year, cc, four_wheel_drive) 
values
('Toyota Mark 2', '2003', 2.5, FALSE);
select * from car;
update car set brand = 'Toyota Mark II', cc = 3.0;
select * from car;
delete from car;
select * from car;