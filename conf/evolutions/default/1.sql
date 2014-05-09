# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table area (
  tipoArea                  varchar(31) not null,
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_area primary key (id))
;

create table document (
  id                        bigint not null,
  user_username             varchar(255),
  extension                 varchar(255),
  name                      varchar(255),
  constraint pk_document primary key (id))
;

create table indicador (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_indicador primary key (id))
;

create table observacion (
  id                        bigint not null,
  provider_id               bigint,
  indicador_id              bigint,
  area_id                   bigint,
  measure                   varchar(255),
  value                     integer,
  inicio                    timestamp,
  fin                       timestamp,
  constraint pk_observacion primary key (id))
;

create table provider (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_provider primary key (id))
;

create table time (
  id                        bigint not null,
  inicio                    timestamp,
  fin                       timestamp,
  constraint pk_time primary key (id))
;

create table user (
  username                  varchar(255) not null,
  email                     varchar(255),
  password                  varchar(255),
  country                   varchar(255),
  address                   varchar(255),
  age                       integer,
  constraint pk_user primary key (username))
;

create sequence area_seq;

create sequence document_seq;

create sequence indicador_seq;

create sequence observacion_seq;

create sequence provider_seq;

create sequence time_seq;

create sequence user_seq;

alter table document add constraint fk_document_user_1 foreign key (user_username) references user (username) on delete restrict on update restrict;
create index ix_document_user_1 on document (user_username);
alter table observacion add constraint fk_observacion_provider_2 foreign key (provider_id) references provider (id) on delete restrict on update restrict;
create index ix_observacion_provider_2 on observacion (provider_id);
alter table observacion add constraint fk_observacion_indicador_3 foreign key (indicador_id) references indicador (id) on delete restrict on update restrict;
create index ix_observacion_indicador_3 on observacion (indicador_id);
alter table observacion add constraint fk_observacion_area_4 foreign key (area_id) references area (id) on delete restrict on update restrict;
create index ix_observacion_area_4 on observacion (area_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists area;

drop table if exists document;

drop table if exists indicador;

drop table if exists observacion;

drop table if exists provider;

drop table if exists time;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists area_seq;

drop sequence if exists document_seq;

drop sequence if exists indicador_seq;

drop sequence if exists observacion_seq;

drop sequence if exists provider_seq;

drop sequence if exists time_seq;

drop sequence if exists user_seq;

