package model;

public class Coordinates {

    double latitude;
    double longitude;

    public Coordinates(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongitude(){
        return this.longitude;
    }
}

//package models;
//
//import io.ebean.*;
//import javax.persistence.*;
//
//
//@Entity
//public class Person extends Model {
//    @Id
//    public Long id;
//    public String name;
//
//    public Person(Long id, String name){
//        this.id = id;
//        this.name = name;
//    }
//
//    public static final Finder<Long, Person> find = new Finder<>(Person.class);
//
//}