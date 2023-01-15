create table categories
(
id          bigserial primary key,
title       varchar(255) unique,
created_at  timestamp default current_timestamp,
updated_at  timestamp default current_timestamp
);

insert into categories(title)values
('testcategory1'),
('testcategory2')
;

create table products
(
id              bigserial primary key,
title           varchar(255) not null,
category_id     bigint references categories(id),
price           int not null,
created_at      timestamp default current_timestamp,
updated_at      timestamp default current_timestamp
);

insert into products
(title, price, category_id) values
('Product_01', 80,1),('Product_02', 25,1),('Product_03', 300,2),
('Product_04', 80,1),('Product_05', 25,1),('Product_06', 300,2),
('Product_07', 80,1),('Product_08', 25,1),('Product_09', 300,2),
('Product_10', 80,1),('Product_11', 25,1),('Product_12', 300,2),
('Product_13', 80,1),('Product_14', 25,1),('Product_15', 300,2),
('Product_16', 80,1),('Product_17', 25,1),('Product_18', 300,2),
('Product_19', 80,1),('Product_20', 25,1),('Product_21', 300,2),
('Product_22', 80,1),('Product_23', 25,1),('Product_24', 300,2),
('Product_25', 80,1),('Product_26', 25,1),('Product_27', 300,2),
('Product_28', 80,1),('Product_29', 25,1),('Product_30', 300,2)
;

--insert into products
--(title, price) values
--('TEST', 999999)
--;

create table orders
(
id                  bigserial primary key,
username            varchar(255) not null,
total_price         int not null,
address             varchar(255),
phone               varchar(255),
created_at          timestamp default current_timestamp,
updated_at          timestamp default current_timestamp
);

create table order_items
(
id                  bigserial primary key,
product_id          bigint not null references products (id),
order_id            bigint not null references orders (id),
quantity            int not null,
price_per_product   int not null,
price               int not null,
created_at          timestamp default current_timestamp,
updated_at          timestamp default current_timestamp
);



