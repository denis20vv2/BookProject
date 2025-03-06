
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


   CREATE TABLE cell
   (
      cell_id bigint NOT NULL,
      cell_positionx BIGINT NOT NULL,
      cell_positiony BIGINT NOT NULL,
      app_id BIGINT NOT NULL,
      data jsonb NOT NULL,
      PRIMARY KEY (cell_id)
   );

   CREATE SEQUENCE cell_seq
          START WITH 1
          INCREMENT BY 1;

   CREATE TABLE app
      (
         app_id bigint NOT NULL,
         name VARCHAR(255) NOT NULL,
         cell_positiony BIGINT NOT NULL,

         PRIMARY KEY (app_id)
      );

      CREATE SEQUENCE app_seq
             START WITH 1
             INCREMENT BY 1;


      ALTER TABLE cell
      ADD CONSTRAINT fk_app_id FOREIGN KEY (app_id) REFERENCES app (app_id) ON DELETE CASCADE;
