create database ecommerce;
use ecommerce;
create table admins(
id int primary key auto_increment,
name varchar(100),
email varchar(100),
password varchar(100)
);
select * from admins;
drop table User;
create table users(
id int primary key auto_increment,
name varchar(100),
email varchar(100),
password varchar(100));

create table products(
id int primary key auto_increment,
name varchar(100),
price int,
stocks int,
type varchar(50),
adminId int,
img_path varchar(100),
foreign key (adminId) references users(id)
);

create table carts(
id int primary key auto_increment,
user_id int,
product_id int,
quantity int,
price int,
foreign key (user_id) references users(id),
foreign key (product_id) references products(id)
);
select * from products;
select * from carts;
create table orders(
id int primary key auto_increment,
user_id int,
total_price int,
status varchar(20),
address varchar(100),
phone varchar(100),
created_at  timestamp default current_timestamp,
foreign key (user_id) references users(id)
);


create table order_items(
id int primary key auto_increment,
order_id int,
product_id int,
quantity int,
foreign key (order_id) references orders(id),
foreign key (product_id) references products(id)
);
select sum(p.price * c.quantity) as total from
 carts c join products p on p.id=c.product_id where c.user_id=1;
 
select * from order_items;
select * from orders;

select * from products;
show databases;
select database();
show tables;
drop table orders;
