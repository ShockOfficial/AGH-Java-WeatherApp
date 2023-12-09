package pl.edu.agh.to2.WeatherApp.model.airPollutionData.json;

public class AirListElementDTO {

    private AirMainInfoDTO mainInfo;
    private ComponentsDTO components;

    public AirMainInfoDTO getMainInfo() {
        return mainInfo;
    }

    public void setMainInfo(AirMainInfoDTO mainInfo) {
        this.mainInfo = mainInfo;
    }

    public ComponentsDTO getComponents() {
        return components;
    }

    public void setComponents(ComponentsDTO components) {
        this.components = components;
    }
}
