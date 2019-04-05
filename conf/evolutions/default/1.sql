# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table coordinate (
  id                            bigserial not null,
  latitude                      float not null,
  longitude                     float not null,
  constraint pk_coordinate primary key (id)
);


# --- !Downs

drop table if exists coordinate cascade;

