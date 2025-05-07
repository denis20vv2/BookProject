
CREATE TABLE "user" (
    user_id BIGINT NOT NULL,
    role VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id)
);

 COMMENT ON TABLE "user" IS 'Таблица данных пользователя';

    COMMENT ON COLUMN "user".user_id IS 'Уникальный идентификатор пользователя';
    COMMENT ON COLUMN "user".role IS 'Роль пользователя';
    COMMENT ON COLUMN "user".password IS 'пароль пользователя';
    COMMENT ON COLUMN "user".username IS 'Имя пользователя';

   CREATE SEQUENCE user_seq
       START WITH 1
       INCREMENT BY 1;


   CREATE TABLE page
   (
      page_id bigint NOT NULL,
      data jsonb NOT NULL,
      PRIMARY KEY (page_id)
   );

   CREATE SEQUENCE page_seq
          START WITH 1
          INCREMENT BY 1;

CREATE TABLE "table"
   (
      id bigint NOT NULL,
      name VARCHAR(255) NOT NULL,
      data jsonb NOT NULL,
      columns jsonb NOT NULL,
      available_filters jsonb,
      applied_filters jsonb,
      PRIMARY KEY (id)
   );

   CREATE SEQUENCE table_seq
          START WITH 1
          INCREMENT BY 1;


