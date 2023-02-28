use test;

create table product(
	product_code int primary key auto_increment,
    product_name varchar(100) not null,
    description varchar(5000),
    price int default 0,
    fileName varchar(500)
);

insert into product (product_name, description, price) values ('사과', '맛있는 사과입니다.', 500);

select * from product;