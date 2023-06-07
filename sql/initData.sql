DROP DATABASE IF EXISTS tpjava2;
CREATE DATABASE IF NOT EXISTS tpjava2;

create table tpjava2.category
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);

create table tpjava2.former
(
    id         bigint auto_increment
        primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null
);

create table tpjava2.level
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);

create table tpjava2.students
(
    id         bigint auto_increment
        primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null
);

create table tpjava2.training
(
    id          bigint auto_increment
        primary key,
    duration    int          null,
    name        varchar(255) null,
    online      bit          not null,
    price       int          null,
    start_at    varchar(255) null,
    id_category bigint       null,
    id_level    bigint       null,
    constraint FK3sk7wqjffuwkw7mdt1o23pnd0
        foreign key (id_category) references tpjava2.category (id),
    constraint FK9i0xsvu2lmvcj3xwolqkyq2wq
        foreign key (id_level) references tpjava2.level (id)
);

create table tpjava2.training_to_former
(
    id_training bigint not null,
    id_former   bigint not null,
    constraint FK27dep26xqvff0fix2ne9lkxsw
        foreign key (id_former) references tpjava2.former (id),
    constraint FKryd49bj08tfukmeveo9gsnx7r
        foreign key (id_training) references tpjava2.training (id)
);

create table tpjava2.training_to_students
(
    id_training bigint not null,
    id_students bigint not null,
    constraint FK2luhwexwklhrtlxxdxtgdxhjk
        foreign key (id_students) references tpjava2.students (id),
    constraint FKg2y3gg352wj8a9ve2c8mewp7s
        foreign key (id_training) references tpjava2.training (id)
);

USE tpjava2;
INSERT INTO category (name) VALUES ('Bureautique'),('Développement'),('Design');
INSERT INTO level (name) VALUES ('Facile'),('Moyen'),('Difficile');

INSERT INTO training (duration, name, online, price, start_at, id_category, id_level)
VALUES
    (3, 'Docker', 0, 2000, '2020-05-05', 2, 3),
    (5, 'Spring', 1, 5000, '2020-06-10', 2, 3),
    (15, 'Photoshop', 0, 15000, '2020-05-02', 3, 1);

INSERT INTO students (first_name, last_name)
VALUES
    ('Olivier', 'Carglass'),
    ('Michel', 'Lambert'),
    ('Justine', 'Trion'),
    ('Marc', 'Aubert');

INSERT INTO former (first_name, last_name)
VALUES
    ('Clémence', 'Breton'),
    ('Christian', 'Demers');

INSERT INTO training_to_former (id_training, id_former)
VALUES
    (1,1),
    (2,2);

INSERT INTO training_to_students (id_training, id_students)
VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (1, 2),
    (2, 2);
