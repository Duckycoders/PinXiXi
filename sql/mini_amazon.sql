create table cart_items
(
    id         int auto_increment
        primary key,
    cart_id    int null,
    product_id int null,
    seller_id  int null,
    quantity   int null
)
    collate = utf8mb4_unicode_ci;

create table carts
(
    id         int auto_increment
        primary key,
    user_id    int                                 null,
    created_at timestamp default CURRENT_TIMESTAMP null
)
    collate = utf8mb4_unicode_ci;

create table categories
(
    id   int auto_increment
        primary key,
    name varchar(255) not null
)
    collate = utf8mb4_unicode_ci;

create table messages
(
    id         int auto_increment
        primary key,
    user_id    int                                 null,
    seller_id  int                                 null,
    message    text                                null,
    created_at timestamp default CURRENT_TIMESTAMP null
)
    collate = utf8mb4_unicode_ci;

create table product_reviews
(
    id         int auto_increment
        primary key,
    product_id int  null,
    user_id    int  null,
    rating     int  null,
    comment    text null,
    check ((`rating` >= 1) and (`rating` <= 5))
)
    collate = utf8mb4_unicode_ci;

create table products
(
    id          int auto_increment
        primary key,
    name        varchar(255)   not null,
    description text           not null,
    image_url   varchar(255)   null,
    price       decimal(10, 2) not null,
    category_id int            null,
    seller_id   int            null,
    constraint products_ibfk_1
        foreign key (category_id) references categories (id)
)
    collate = utf8mb4_unicode_ci;

create index category_id
    on products (category_id);

create table seller_reviews
(
    id         int auto_increment
        primary key,
    seller_id  int                                 null,
    user_id    int                                 null,
    rating     int                                 null,
    comment    text                                null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    updated_at timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint unique_seller_user
        unique (seller_id, user_id),
    check ((`rating` >= 1) and (`rating` <= 5))
)
    collate = utf8mb4_unicode_ci;

create table sellers
(
    id      int auto_increment
        primary key,
    name    varchar(255) not null,
    email   varchar(255) not null,
    address varchar(255) not null,
    user_id int          null,
    constraint email
        unique (email)
)
    collate = utf8mb4_unicode_ci;

create table product_inventory
(
    id             int auto_increment
        primary key,
    product_id     int null,
    seller_id      int null,
    stock_quantity int null,
    constraint product_inventory_ibfk_2
        foreign key (seller_id) references sellers (id)
)
    collate = utf8mb4_unicode_ci;

create index seller_id
    on product_inventory (seller_id);

create table seller_inventory
(
    id             int auto_increment
        primary key,
    product_id     int null,
    seller_id      int null,
    stock_quantity int null,
    constraint seller_inventory_ibfk_2
        foreign key (seller_id) references sellers (id)
)
    collate = utf8mb4_unicode_ci;

create index product_id
    on seller_inventory (product_id);

create index seller_id
    on seller_inventory (seller_id);

create table users
(
    id         bigint auto_increment
        primary key,
    email      varchar(255)                             not null,
    name       varchar(255)                             not null,
    address    varchar(255)                             null,
    password   varchar(255)                             not null,
    balance    decimal(10, 2) default 0.00              null,
    created_at timestamp      default CURRENT_TIMESTAMP null,
    constraint email
        unique (email)
)
    collate = utf8mb4_unicode_ci;

create table orders
(
    id           bigint auto_increment
        primary key,
    user_id      bigint                                not null,
    total_amount decimal(10, 2)                        not null,
    item_count   int                                   not null,
    status       varchar(50) default 'pending'         not null,
    created_at   timestamp   default CURRENT_TIMESTAMP null,
    constraint orders_ibfk_1
        foreign key (user_id) references users (id)
            on delete cascade
)
    collate = utf8mb4_unicode_ci;

create table order_items
(
    id         bigint auto_increment
        primary key,
    order_id   bigint         not null,
    product_id bigint         not null,
    quantity   int            not null,
    price      decimal(10, 2) not null,
    constraint order_items_ibfk_1
        foreign key (order_id) references orders (id)
            on delete cascade
)
    collate = utf8mb4_unicode_ci;

create index order_id
    on order_items (order_id);

create index user_id
    on orders (user_id);

