create table fuel (
id serial primary key,
name varchar(20),
volume integer,
price float
);

insert into fuel (name, volume, price) values ('regular', 55, 3.8),
('mid-grade', 45, 4.2), ('premium', 15, 4.55), ('diesel', 80, 5.4);

select * from fuel;
begin transaction;
update fuel set price = 5 where name = 'diesel';
savepoint point_1;
insert into fuel (name, volume, price) values ('e-85', 23, 3.15);
delete from fuel where volume < 20;
savepoint point_2;
select * from fuel;
delete from fuel;
drop table fuel;
select * from fuel;
rollback to point_2;
select * from fuel;
release point_2;
rollback to point_1;
select * from fuel;
commit;

