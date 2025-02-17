
 create table user (
 user_id bigint not null,
 login varchar(255) not null,
 rule bigint not null,
 password varchar(255) not null,
 name varchar(255) not null,
 primary key (user_id));

 COMMENT ON TABLE user IS 'Таблица данных пользователя';

    COMMENT ON COLUMN user.user_id IS 'Уникальный идентификатор пользователя';
    COMMENT ON COLUMN user.login IS 'Имя пользователя';
    COMMENT ON COLUMN user.rule IS 'Роль пользователя';
    COMMENT ON COLUMN user.password IS 'пароль пользователя';
    COMMENT ON COLUMN user.name IS 'Имя пользователя';

   CREATE SEQUENCE user_seq
       START WITH 1
       INCREMENT BY 1;