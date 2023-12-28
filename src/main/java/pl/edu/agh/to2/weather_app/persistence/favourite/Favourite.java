package pl.edu.agh.to2.weather_app.persistence.favourite;

public class Favourite {
    private String name;
    private float lon;
    private float lat;


    public Favourite(String name, float lon, float lat){
        this.name = name;
        this.lon = lon;
        this.lat = lat;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}
