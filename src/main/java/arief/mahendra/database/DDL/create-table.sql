create table  customers
(
    id varchar(100) not null,
    name varchar(255) not null,
    email varchar(255) not null,
    constraint email_unique unique (email),
    primary key (id)
);

select * from customers;

create table admin
(
    username varchar(255) not null,
    password varchar(255) not null,
    primary key (username)
);

insert into admin(username, password) values ('arief','makananku');

create table comments
(
    id serial,
    email varchar(255) not null,
    comment varchar(500),
    primary key (id)
);

select * from comments;

delete from comments;
