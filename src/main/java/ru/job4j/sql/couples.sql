create table teens(
id serial primary key,
name varchar,
gender varchar(1)
);

insert into teens(name, gender) values('Alex', 'M');
insert into teens(name, gender) values('Bob', 'M');
insert into teens(name, gender) values('Felix', 'M');
insert into teens(name, gender) values('George', 'M');
insert into teens(name, gender) values('Mark', 'M');
insert into teens(name, gender) values('Lucy', 'F');
insert into teens(name, gender) values('Sara', 'F');
insert into teens(name, gender) values('Rita', 'F');
insert into teens(name, gender) values('Julia', 'F');
insert into teens(name, gender) values('Nancy', 'F');

select t1.name, t2.name
from teens t1
cross join teens t2
where t1.gender != t2.gender and t1.gender = 'M';