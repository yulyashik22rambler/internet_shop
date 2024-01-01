INSERT into users(basket_id, id, archive, email, name, password, role)
VALUES(null, 1, false, 'j_uliet_ta@vk.com', 'admin','admin', 'ADMIN');

create table authorities
(
    username  varchar,
    authority varchar
);

ALTER TABLE users
ADD enabled       varchar;
 ALTER TABLE users
 ADD       priority      integer;


ALTER TABLE username
ADD enabled       varchar;