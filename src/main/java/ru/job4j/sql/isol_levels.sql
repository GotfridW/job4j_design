create table fuel (
id serial primary key,
name varchar(20),
volume integer,
price float
);

insert into fuel (name, volume, price) values 
('regular', 55, 3.8), ('mid-grade', 45, 4.2),
('premium', 15, 4.55), ('diesel', 80, 5.4);

--1st transaction:
begin transaction isolation level serializable;
select * from fuel;
select * from fuel where volume = (select max(volume) from fuel);
update fuel set volume = 50 where name = 'diesel';
commit;

--2nd transaction 

begin transaction isolation level serializable;
select * from fuel;
select * from fuel where volume = (select max(volume) from fuel);
update fuel set volume = 35 where name = 'regular';
commit;