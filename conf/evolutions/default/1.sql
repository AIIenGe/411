# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table coordinate (
  id                            bigserial not null,
  latitude                      float not null,
  longitude                     float not null,
  constraint pk_coordinate primary key (id)
);

create table earthquake (
  id                            bigserial not null,
  start_time                    varchar(255),
  end_time                      varchar(255),
  latitude                      float not null,
  longitude                     float not null,
  min_magnitude                 float not null,
  radius                        float not null,
  constraint pk_earthquake primary key (id)
);


# --- !Downs

drop table if exists coordinate cascade;

drop table if exists earthquake cascade;

