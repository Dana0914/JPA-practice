create table categories (
    id serial8 not null,
    name varchar (40) not null,
    primary key (id)
);

create table items (
    id serial8 not null,
    category_id int8 not null,
    item_names varchar (40) not null,
    price int8 not null,
    primary key (id),
    foreign key (category_id) references categories (id)
);

create table characteristics (
    id       serial8     not null,
    category_id int8        not null,
    name     varchar(40) not null,
    primary key (id),
    foreign key (category_id) references categories (id)
);

insert into categories (name)
values ('processors'),
       ( 'monitors');

insert into items (id, category_id, item_names, price)
values (1,'Intel Core I9 9900', 780000),
       (1,'AMD Ryzen R7 7700', 650000),
       (2,'Samsung SU556270', 450000),
       (2, 'AOC Z215S659', 535000);

insert into characteristics (category_id, name)
values (1, 'manufacturer' ),
       (1, 'amount_of_cores'),
       (1, 'socket'),
       (2, 'manufacturer'),
       (2, 'diagonal'),
       (2, 'matrix'),
       (2, 'resolution');

create table values (
    id serial8 not null,
    characteristic_id int8 not null,
    items_id int8 not null,
    values varchar (40) not null,
    primary key (id),
    foreign key (characteristic_id) references characteristics (id),
    foreign key (items_id) references items (id)
);

insert into values (characteristic_id, items_id, values)
values (1, 1,'Intel'),
       (2, 1,'8'),
       (3, 1,'1250'),
       (4, 2,'Samsung'),
       (5, 2,'27'),
       (6, 2,'TN'),
       (7,2,'2560*1440'),
       (1, 3,'AMD'),
       (2,3,'12'),
       (3, 3,'AM4'),
       (4,4,'AOC'),
       (5,4,'21.5'),
       (6,4,'AH-IPS'),
       (7,4,'1920*1080');


