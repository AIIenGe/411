package Model;

public class Coordinate {
    public double latitude;
    public double longitude;
    public String maxmagnitude;

    public Coordinate(){

    }

    public Coordinate(double latitude, double longitude, String maxmagnitude){
        this.latitude = latitude;
        this.longitude = longitude;
        this.maxmagnitude = maxmagnitude;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public String minmagnitude(){
        return maxmagnitude;
    }


}
