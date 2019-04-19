package Model;

import javax.persistence.*;
import io.ebean.*;

@Entity
public class Coordinate extends Model{
    @Id
    public double latitude;
    public double longitude;

    public Coordinate(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getlatitude(){
        return latitude;
    }
    public double getlongitude(){
        return longitude;
    }

    public static final Finder<Long, Coordinate> find = new Finder<>(Coordinate.class);
}