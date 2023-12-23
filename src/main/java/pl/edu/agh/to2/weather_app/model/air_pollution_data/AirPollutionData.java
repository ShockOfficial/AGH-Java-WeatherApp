package pl.edu.agh.to2.weather_app.model.air_pollution_data;

import com.google.gson.annotations.SerializedName;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.json.AirListElementDTO;
import java.util.ArrayList;
import java.util.List;

public class AirPollutionData {
    @SerializedName("list")
    private List<AirListElementDTO> pollutionElementList = new ArrayList<>();

    public AirListElementDTO getPollutionListElement() {
        return pollutionElementList.get(0);
    }

    public void setPollutionList(List<AirListElementDTO> list) {
        this.pollutionElementList = list;
    }
}
