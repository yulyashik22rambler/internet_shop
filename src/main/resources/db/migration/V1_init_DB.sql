create sequence basket_seq start with 1 increment by 1;
create sequence category_seq start with 1 increment by 1;
create sequence order_details_seq start with 1 increment by 1;
create sequence order_seq start with 1 increment by 1;
create sequence product_seq start with 1 increment by 1;
create sequence user_seq start with 1 increment by 1;

create table baskets (id bigint not null, user_id bigint unique, primary key (id));
create table baskets_products (basket_id bigint not null, product_id bigint not null);
create table category (id bigint not null, title varchar(255), primary key (id));
create table orders (sum numeric(38,2), created timestamp(6), id bigint not null, updated timestamp(6), user_id bigint, address varchar(255), status varchar(255) check (status in ('NEW','APPROVED','CANCELED','PAID','CLOSED')), primary key (id));
create table orders_details (amount numeric(38,2), price numeric(38,2), details_id bigint not null unique, id bigint not null, order_id bigint, product_id bigint, primary key (id));
create table products (price numeric(38,2), id bigint not null, title varchar(255), primary key (id));
create table products_categories (category_id bigint not null, product_id bigint not null);
create table users (basket_id bigint unique, id bigint not null, archive varchar(255), email varchar(255), name varchar(255), password varchar(255), role varchar(255) check (role in ('CLIENT','MANAGER','ADMIN')), primary key (id));

alter table if exists baskets add constraint FK87s17cinc4wkx0taas5nh0s8h foreign key (user_id) references users;
alter table if exists baskets_products add constraint FK81mtboy4cahxvtv86apcin43c foreign key (product_id) references products;
alter table if exists baskets_products add constraint FKribumfhgwuqeoqhq8gs88smee foreign key (basket_id) references baskets;
alter table if exists orders add constraint FK32ql8ubntj5uh44ph9659tiih foreign key (user_id) references users;
alter table if exists orders_details add constraint FK5o977kj2vptwo70fu7w7so9fe foreign key (order_id) references orders;
alter table if exists orders_details add constraint FKs0r9x49croribb4j6tah648gt foreign key (product_id) references products;
alter table if exists orders_details add constraint FKgvp1k7a3ubdboj3yhnawd5m1p foreign key (details_id) references orders_details;
alter table if exists products_categories add constraint FKfdcalyppphnbbrsktuqd46c9 foreign key (category_id) references category;
alter table if exists products_categories add constraint FKtj1vdea8qwerbjqie4xldl1el foreign key (product_id) references products;
alter table if exists users add constraint FKommxbvym8k63qbwomj0ikj8us foreign key (basket_id) references baskets;