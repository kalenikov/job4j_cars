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
    id   serial primary key,
    path varchar(255)
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
    brand_id    integer references car_brands
);

create table posts_images
(
    post_id   integer not null references posts,
    images_id integer not null unique references images,
    constraint posts_images_pkey
        primary key (post_id, images_id)
);
