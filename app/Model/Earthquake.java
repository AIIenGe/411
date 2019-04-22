package Model;

import javax.persistence.*;
import io.ebean.*;

@Entity
public class Earthquake extends Model{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String startTime;
    public String endTime;

    public double minMagnitude;
    public double radius;

    public Earthquake(){

    }

    public Earthquake(Long id, String startTime, String endTime, double minMagnitude, double radius){
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.minMagnitude = minMagnitude;
        this.radius = radius;

    }

    public long getId(){ return id; }

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