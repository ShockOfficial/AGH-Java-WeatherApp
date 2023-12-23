package pl.edu.agh.to2.weather_app.model.weather_data.json;

import java.util.ArrayList;
import java.util.List;

public class WeatherDTO {
    private int id;
    private String main;
    private String description;
    private String icon;
    private List<String> iconList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getIconList() {
        return iconList;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public void addIconToList(String icon) {
        if (this.iconList == null) {
            this.iconList = new ArrayList<>();
        }
        this.iconList.add(icon);
    }

    public void setIconList(List<String> iconList) {
        this.iconList = iconList;
    }
}
