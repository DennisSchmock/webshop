DROP TABLE IF EXISTS order_details,orders,toppings,bottoms, customers;

CREATE TABLE customers (
cos_id int(10) NOT NULL UNIQUE AUTO_INCREMENT,
username varchar(30) NOT NULL,
fname varchar(30),
lname varchar(50),
street varchar(50),
zip int(10),
city varchar(40),
balance decimal(8,2) UNSIGNED NOT NULL,
email varchar(40), 
pwd varchar(40) NOT NULL,
PRIMARY KEY (cos_id)
);

CREATE TABLE orders(
order_id int(10) NOT NULL UNIQUE AUTO_INCREMENT,
cos_id integer(10) NOT NULL,
PRIMARY KEY (order_id),
FOREIGN KEY (cos_id) REFERENCES customers(cos_id)
);

CREATE TABLE toppings(
top_id int(10) NOT NULL UNIQUE AUTO_INCREMENT,
top_name varchar(30) NOT NULL,
top_price decimal(8,2) UNSIGNED,
PRIMARY KEY(top_id)
);

CREATE TABLE bottoms(
bot_id int(10) NOT NULL UNIQUE AUTO_INCREMENT,
bot_name varchar(30) NOT NULL,
bot_price decimal(8,2)UNSIGNED NOT NULL,
PRIMARY KEY (bot_id)
);


CREATE TABLE order_details(
od_id int(10) NOT NULL AUTO_INCREMENT,
order_id int(10) NOT NULL,
top_id int(10) NOT NULL,
bot_id int(10) NOT NULL,
qty int(10) UNSIGNED NOT NULL,
PRIMARY KEY (od_id),
FOREIGN KEY (order_id) references orders(order_id),
FOREIGN KEY (top_id) REFERENCES toppings(top_id),
FOREIGN KEY (bot_id) REFERENCES bottoms(bot_id)

);

INSERT INTO bottoms(bot_name,bot_price) values('Chocolate','4.99');
INSERT INTO bottoms(bot_name,bot_price) values('Vanilla','4.99');
INSERT INTO bottoms(bot_name,bot_price) values('Nutmeg','4.99');
INSERT INTO bottoms(bot_name,bot_price) values('Pistacio','5.99');
INSERT INTO bottoms(bot_name,bot_price) values('Almond','6.99');

INSERT INTO toppings(top_name,top_price) values('Chocolate','4.99');
INSERT INTO toppings(top_name,top_price) values('Blueberry','4.99');
INSERT INTO toppings(top_name,top_price) values('Rasberry','4.99');
INSERT INTO toppings(top_name,top_price) values('Crispy','5.99');
INSERT INTO toppings(top_name,top_price) values('Strawberry','5.99');
INSERT INTO toppings(top_name,top_price) values('Rum/Raisin','6.99');
INSERT INTO toppings(top_name,top_price) values('Orange','7.99');
INSERT INTO toppings(top_name,top_price) values('Lemon','7.99');
INSERT INTO toppings(top_name,top_price) values('Blue Cheese','8.99');

INSERT INTO customers(username,fname,lname,street,zip,city,balance,pwd) 
values('daeniz','Dennis','Schmock','Lange Eng 58','2620','Albertslund','100','111');
