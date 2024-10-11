 drop table if exists author cascade;
 drop table if exists book cascade;
 drop sequence if exists author_seq;
 drop sequence if exists book_seq;

 create sequence author_seq
 start with 1
 increment by 1;


 create sequence book_seq
 start with 1
 increment by 1;


 create table author (
 author_id bigint not null DEFAULT nextval('author_seq'),
 author_name varchar(255) not null,
 primary key (author_id));

 create table book (
 author_id bigint not null,
 book_id bigint not null DEFAULT nextval('book_seq'),
 book_name varchar(255) not null,
 FOREIGN KEY (author_id) REFERENCES author(author_id),
 primary key (book_id));

  create table author_book (
  author_id bigint not null,
  book_id bigint not null,
  PRIMARY KEY (author_id, book_id),
  FOREIGN KEY (author_id) REFERENCES author(author_id) ON DELETE CASCADE,
  FOREIGN KEY (book_id) REFERENCES book(book_id) ON DELETE CASCADE);