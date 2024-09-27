 alter table if exists books drop constraint if exists FKfjixh2vym2cvfj3ufxj91jem7
 drop table if exists authors cascade
 drop table if exists books cascade
 drop sequence if exists authors_seq
 drop sequence if exists books_seq
 create sequence authors_seq start with 1 increment by 1
 create sequence books_seq start with 1 increment by 1
 create table authors (author_id bigint not null, author_name varchar(255) not null, primary key (author_id))
 create table books (author_id bigint not null, book_id bigint not null, book_name varchar(255) not null, primary key (book_id))