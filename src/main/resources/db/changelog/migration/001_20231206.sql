--liquibase formatted sql
--changeset jimsprouts:001.1
create table if not exists student (
  id uuid not null default random_uuid(),

  name varchar(50) not null,
  email varchar(50) not null,

  constraint pk_customer_id primary key (id),
  constraint uq_customer_name unique (name),
  constraint uq_customer_email unique (email)
);
--rollback drop table if exists student;

