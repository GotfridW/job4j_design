create table fruit_trees(
id serial primary key,
name varchar(255)
);

create table fruits(
id serial primary key,
name varchar(255),
tree_id int references fruit_trees(id) unique
);

insert into fruit_trees(name) values ('Apple tree');
insert into fruits(name, tree_id) values ('Apple', 1);
 
