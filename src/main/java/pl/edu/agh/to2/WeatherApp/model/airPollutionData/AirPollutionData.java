package pl.edu.agh.to2.WeatherApp.model.airPollutionData;

import com.google.gson.annotations.SerializedName;
import pl.edu.agh.to2.WeatherApp.model.airPollutionData.json.AirListElementDTO;

import java.util.ArrayList;
import java.util.List;

public class AirPollutionData {
    @SerializedName("list")
    private List<AirListElementDTO> PollutionElementList = new ArrayList<>();


    public AirListElementDTO getPollutionList() {
        return PollutionElementList.get(0);
    }

    public void setPollutionList(List<AirListElementDTO> list) {
        this.PollutionElementList = list;
    }
}
