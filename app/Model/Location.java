package Model;

import javax.persistence.*;
import io.ebean.*;

@Entity(name="location")
public class Location extends Model{
    @Id
    public Long id;
    public String city;
    public String country;

    public Location(Long id, String city, String country){
        this.id = id;
        this.city = city;
        this.country = country;
    }

    public String getCity(){
        return city;
    }
    public String getCountry(){
        return country;
    }

    public static final Finder<Long, Location> find = new Finder<>(Location.class);
}