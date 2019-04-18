package Model;

import javax.persistence.*;
import io.ebean.*;

@Entity
public class Earthquake extends Model{
    @Id
    private long id;

    public String startTime;
    public String endTime;

    public double latitude;
    public double longitude;

    public double minMagnitude;
    public double radius;

    public Earthquake(){

    }

    public Earthquake(long id, String startTime, String endTime, double latitude, double longitude, double minMagnitude, double radius){
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.minMagnitude = minMagnitude;
        this.radius = radius;

    }

    public double getLatitude(){
        return latitude;
    }

    public long getId(){ return id; }

    public double getLongitude(){
        return longitude;
    }

    public double getMinMagnitude(){
        return minMagnitude;
    }

    public String getStartTime(){
        return startTime;
    }

    public String getEndTime(){
        return endTime;
    }


    public double getRadius(){
        return radius;
    }

    public static final Finder<Long, Earthquake> find = new Finder<>(Earthquake.class);
}