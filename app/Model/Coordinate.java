package Model;

import javax.persistence.*;
import io.ebean.*;

@Entity
public class Coordinate extends Model{
    @Id
    private long id;
    private double latitude;
    private double longitude;

    public Coordinate(){

    }

    public Coordinate(long id, double latitude, double longitude){
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude(){
        return latitude;
    }
    public long getId(){ return id; }
    public double getLongitude(){
        return longitude;
    }

    public static final Finder<Long, Coordinate> find = new Finder<>(Coordinate.class);
}
