create table users (
  id          bigint    not null,
  created_on  timestamp not null,
  modified_on timestamp not null,
  userName    varchar(255),
  primary key (id)
);

create table posts (
  id          bigint       not null,
  postName    varchar(255) not null,
  created_on  timestamp    not null,
  modified_on timestamp    not null,
  price       double not null,
  description      varchar(255) not null,
  quantityAvailable double not null,
  user_id    bigint       not null,
  primary key (id)
);

create table rel_user_user(
    user_id bigint not null,
    following_user_id bigint not null,
    primary key (user_id, following_user_id)
);

create table transactions (
  id          bigint       not null,
  created_on  timestamp    not null,
  modified_on timestamp    not null,
  quantity    integer      not null,
  scoreTransaction integer not null,
  post_id bigint not null,
  user_id bigint not null,
  primary key (id)
);

alter table transactions
  add constraint FK_transaction_user foreign key (user_id) references users;

alter table transactions
  add constraint FK_transaction_post foreign key (post_id) references posts;

alter table posts
  add constraint FK_user foreign key (user_id) references users;

alter table rel_user_user
  add constraint FK_user_follower foreign key (user_id) references users;

alter table rel_user_user
  add constraint FK_user_following foreign key (following_user_id) references users;

create sequence hibernate_sequence
  start with 10
  increment by 1;
