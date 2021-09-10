create table car_body_types
(
    id   serial primary key,
    name varchar(255)
);

create table car_brands
(
    id   serial primary key,
    name varchar(255)
);


create table images
(
    id      serial primary key,
    path    varchar(255),
    post_id integer references posts
);


create table users
(
    id   serial primary key,
    name varchar(255)
);

create table posts
(
    id          serial primary key,
    description varchar(255),
    sold        boolean not null default false,
    author_id   integer references users,
    body_id     integer references car_body_types,
    brand_id    integer references car_brands,
    created     timestamp
);
