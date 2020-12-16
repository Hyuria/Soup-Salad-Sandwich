

create table category(
	id serial primary key,
	category_name varchar(9) unique not null check (category_name in('soup','salad','sandwich','undecided'))
);

create table status(
	id serial primary key,
	status_name varchar(30) unique not null
);

create table roles(
	id serial primary key,
	role_name varchar(5) check (role_name in('user','admin'))
);

create table users(
	id serial primary key,
	username varchar(20) unique not null,
	passwrd varchar(20) not null,
	roles_id int references roles default 1
);

create table dish(
	id serial primary key,
	dish_name varchar(30) unique not null,
	category_id int references category,
	photo_url varchar(60)
);

create table user_comments(
	id serial primary key,
	dish_id int references dish,
	user_id int references users,
	date_posted timestamp default current_timestamp,
	like_count int check (like_count in(-1,0,1)),
	message varchar(200)
);

create table vote(
	id serial primary key,
	dish_id int references dish,
	user_id int references users,
	category_id int references category,
	unique(dish_id,user_id)
);

create table likes(
	id serial primary key,
	user_coments_id int references user_comments,
	user_id int references users,
	thoughts varchar(9) unique not null check(thoughts in('like','dislike','undecided')) default 'undecided'
);



insert into category values(default,'soup');
insert into category values(default,'salad');
insert into category values(default,'sandwich');
insert into category values(default,'undecided');

insert into status values(default,'admin pending');
insert into status values(default,'new dish');
insert into status values(default,'hot topic');
insert into status values(default,'classic');

insert into roles values(default,'user');
insert into roles values(default,'admin');

insert into users values(default,'jakeem','fisher',2);
insert into users values(default,'henry','chen',2);
insert into users values(default,'brodie','hufnagel',2);
insert into users values(default,'ben','hsieh',2);
insert into users values(default,'caillou','bald',default);
insert into users values(default,'sierra','nicholes',default);
insert into users values(default,'gordon','ramsey',default);



select * from users;

drop table category, status, roles, users, dish, user_comments, vote, likes;
