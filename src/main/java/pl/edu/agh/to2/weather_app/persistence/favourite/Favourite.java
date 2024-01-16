package pl.edu.agh.to2.weather_app.persistence.favourite;

public class Favourite {
    private String customName;
    private String city;
    private Float lon;
    private Float lat;
    private String time;


    public Favourite(String name, Float lon, Float lat, String time){
        this.customName = name;
        this.lon = lon;
        this.lat = lat;
        this.time = time;
    }

    public Favourite(String name, String city, String time){
        this.customName = name;
        this.city = city;
        this.time = time;
    }
    public String getName() {
        return customName;
    }

    public void setName(String name) {
        this.customName = name;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCity() {
        return city;
    }
    @Override
    public String toString() {
        if (this.city == null) {
            return this.customName + " (" + this.lon + ", " + this.lat + ") - " + this.time;
        }
        return this.customName + " (" + this.city + ") - " + this.time;
    }
}
