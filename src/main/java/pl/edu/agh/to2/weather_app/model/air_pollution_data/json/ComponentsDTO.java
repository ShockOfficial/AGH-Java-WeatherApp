package pl.edu.agh.to2.weather_app.model.air_pollution_data.json;

import com.google.gson.annotations.SerializedName;

public class ComponentsDTO {
    @SerializedName("co")
    private String concentrationOfCarbonMonoxide;
    @SerializedName("no")
    private String concentrationOfNitrogenMonoxide;
    @SerializedName("no2")
    private String concentrationOfNitrogenDioxide;
    @SerializedName("o3")
    private String concentrationOfOzone;
    @SerializedName("so2")
    private String concentrationOfSulphurDioxide;
    @SerializedName("pm2_5")
    private String concentrationOfFineParticlesMatter;
    @SerializedName("pm10")
    private String concentrationOfCoarseParticulateMatter;
    @SerializedName("nh3")
    private String concentrationOfAmmonia;

    public String getConcentrationOfCarbonMonoxide() {
        return concentrationOfCarbonMonoxide;
    }

    public void setConcentrationOfCarbonMonoxide(String concentrationOfCarbonMonoxide) {
        this.concentrationOfCarbonMonoxide = concentrationOfCarbonMonoxide;
    }

    public String getConcentrationOfNitrogenMonoxide() {
        return concentrationOfNitrogenMonoxide;
    }

    public void setNo(String concentrationOfNitrogenMonoxide) {
        this.concentrationOfNitrogenMonoxide = concentrationOfNitrogenMonoxide;
    }

    public String getConcentrationOfNitrogenDioxide() {
        return concentrationOfNitrogenDioxide;
    }

    public void setConcentrationOfNitrogenDioxide(String concentrationOfNitrogenDioxide) {
        this.concentrationOfNitrogenDioxide = concentrationOfNitrogenDioxide;
    }

    public String getConcentrationOfOzone() {
        return concentrationOfOzone;
    }

    public void setConcentrationOfOzone(String concentrationOfOzone) {
        this.concentrationOfOzone = concentrationOfOzone;
    }

    public String getConcentrationOfSulphurDioxide() {
        return concentrationOfSulphurDioxide;
    }

    public void setConcentrationOfSulphurDioxide(String concentrationOfSulphurDioxide) {
        this.concentrationOfSulphurDioxide = concentrationOfSulphurDioxide;
    }

    public String getConcentrationOfFineParticlesMatter() {
        return concentrationOfFineParticlesMatter;
    }

    public void setConcentrationOfFineParticlesMatter(String concentrationOfFineParticlesMatter) {
        this.concentrationOfFineParticlesMatter = concentrationOfFineParticlesMatter;
    }

    public String getConcentrationOfCoarseParticulateMatter() {
        return concentrationOfCoarseParticulateMatter;
    }

    public void setConcentrationOfCoarseParticulateMatter(String concentrationOfCoarseParticulateMatter) {
        this.concentrationOfCoarseParticulateMatter = concentrationOfCoarseParticulateMatter;
    }

    public String getConcentrationOfAmmonia() {
        return concentrationOfAmmonia;
    }

    public void setConcentrationOfAmmonia(String concentrationOfAmmonia) {
        this.concentrationOfAmmonia = concentrationOfAmmonia;
    }
}
