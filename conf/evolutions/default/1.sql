# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table coordinate (
  latitude                      float not null,
  longitude                     float not null,
  constraint pk_coordinate primary key (latitude)
);

create table earthquake (
  id                            bigserial not null,
  start_time                    varchar(255),
  end_time                      varchar(255),
  min_magnitude                 float not null,
  radius                        float not null,
  constraint pk_earthquake primary key (id)
);

create table location (
  id                            bigserial not null,
  city                          varchar(255),
  country                       varchar(255),
  constraint pk_location primary key (id)
);


# --- !Downs

drop table if exists coordinate cascade;

drop table if exists earthquake cascade;

drop table if exists location cascade;

