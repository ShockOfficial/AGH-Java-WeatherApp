package pl.edu.agh.to2.weather_app.model.weather_data;

import pl.edu.agh.to2.weather_app.model.weather_data.json.*;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataMerger {
    public WeatherData mergeWorseWeatherData(WeatherData dataA, WeatherData dataB) {
        if (dataA == null) return dataB;
        if (dataB == null) return dataA;

        WeatherData result = new WeatherData();
        result.setSys(new SysDTO());
        result.setMain(new MainInfoDTO());
        result.setWind(new WindDTO());
        result.setClouds(new CloudsDTO());
        result.getWeather().add(new WeatherDTO());

        mergeNamesAndCountries(dataA, dataB, result);
        mergeMainWeatherConditions(dataA, dataB, result);
        mergeWeatherDetails(dataA, dataB, result);
        mergeAirPollutionData(dataA, dataB, result);

        mergeTotalFall(dataA.getRain(), dataB.getRain(), result, true);
        mergeTotalFall(dataA.getSnow(), dataB.getSnow(), result, false);
        mergeWeatherIcons(dataA, dataB, result);

        return result;
    }

    private static void mergeNamesAndCountries(WeatherData dataA, WeatherData dataB, WeatherData result) {
        String nameA = dataA.getName() != null ? dataA.getName() : "";
        String nameB = dataB.getName() != null ? dataB.getName() : "";
        String countryA = (dataA.getSys() != null && dataA.getSys().getCountry() != null) ? dataA.getSys().getCountry() : "";
        String countryB = (dataB.getSys() != null && dataB.getSys().getCountry() != null) ? dataB.getSys().getCountry() : "";

        result.setName(nameA + (nameB.isEmpty() ? "" : " & " + nameB));
        result.getSys().setCountry(countryA + (countryB.isEmpty() ? "" : " & " + countryB));
    }

    private static void mergeMainWeatherConditions(WeatherData dataA, WeatherData dataB, WeatherData result) {
        if (!dataA.getWeather().isEmpty() && !dataB.getWeather().isEmpty()) {
            String mainA = dataA.getWeather().get(0).getMain();
            String mainB = dataB.getWeather().get(0).getMain();
            result.getWeather().get(0).setMain(mainA + (mainB.isEmpty() ? "" : " & " + mainB));
        } else if (!dataA.getWeather().isEmpty()) {
            result.getWeather().get(0).setMain(dataA.getWeather().get(0).getMain());
        } else if (!dataB.getWeather().isEmpty()) {
            result.getWeather().get(0).setMain(dataB.getWeather().get(0).getMain());
        }
    }

    private static void mergeWeatherDetails(WeatherData dataA, WeatherData dataB, WeatherData result) {
        if (dataA.getMain() != null && dataB.getMain() != null) {
            result.getMain().setTemp(Math.min(dataA.getMain().getTemp(), dataB.getMain().getTemp()));
            result.getMain().setFeelsLike(Math.min(dataA.getMain().getFeelsLike(), dataB.getMain().getFeelsLike()));
            result.getMain().setPressure(Math.max(dataA.getMain().getPressure(), dataB.getMain().getPressure()));
            result.getMain().setHumidity(Math.max(dataA.getMain().getHumidity(), dataB.getMain().getHumidity()));
        }

        if (dataA.getWind() != null && dataB.getWind() != null) {
            result.getWind().setSpeed(Math.max(dataA.getWind().getSpeed(), dataB.getWind().getSpeed()));
        }
    }

    private static void mergeTotalFall(TotalFallDTO fallA, TotalFallDTO fallB, WeatherData result, boolean isRain) {
        if (fallA != null || fallB != null) {
            float amountA = fallA != null ? fallA.getOneH() : 0;
            float amountB = fallB != null ? fallB.getOneH() : 0;
            TotalFallDTO mergedFall = new TotalFallDTO();
            mergedFall.setOneH(Math.max(amountA, amountB));
            if (isRain) {
                result.setRain(mergedFall);
            } else {
                result.setSnow(mergedFall);
            }
        }
    }

    private static void mergeWeatherIcons(WeatherData dataA, WeatherData dataB, WeatherData result) {
        if (dataA.getWeather().isEmpty() || dataB.getWeather().isEmpty()) return;

        String iconA = dataA.getWeather().get(0).getIcon();
        String iconB = dataB.getWeather().get(0).getIcon();

        if (!iconA.equals(iconB)) {
            result.getWeather().get(0).addIconToList(iconA);
            result.getWeather().get(0).addIconToList(iconB);
        } else {
            result.getWeather().get(0).addIconToList(iconA);
        }
    }

    private static void mergeAirPollutionData(WeatherData dataA, WeatherData dataB, WeatherData result) {
        if (dataA.getAirPollutionData() != null && dataB.getAirPollutionData() != null) {
            int aqiA = Integer.parseInt(dataA.getAirPollutionData().getPollutionListElement().getMainInfo().getAqi());
            int aqiB = Integer.parseInt(dataB.getAirPollutionData().getPollutionListElement().getMainInfo().getAqi());
            if (aqiA > aqiB) {
                result.setAirPollutionData(dataA.getAirPollutionData());
            } else {
                result.setAirPollutionData(dataB.getAirPollutionData());
            }
        } else if (dataA.getAirPollutionData() != null) {
            result.setAirPollutionData(dataA.getAirPollutionData());
        } else if (dataB.getAirPollutionData() != null) {
            result.setAirPollutionData(dataB.getAirPollutionData());
        }
    }

    public WeatherDataToDisplay mergeWorseWeatherData(List<WeatherDataToDisplay> dataToDisplay) {
        ArrayList<WeatherDataToDisplay> dataToDisplayCopy = new ArrayList<>(dataToDisplay);
        WeatherDataToDisplay result = dataToDisplayCopy.remove(0);
        result.setCityName(result.getCityName() != null ? result.getCityName() : "Unknown");
        result.setCountry(result.getCountry() != null ? result.getCountry() : "Unknown");
        result.setWeatherParameter(result.getWeatherParameter() != null ? result.getWeatherParameter() : "Unknown");
        result.setAirQuality(result.getAirQuality() != null ? result.getAirQuality() : "Unknown");
        if (result.getIcon() != null) {
            result.addIconToList(result.getIcon());
        }
        dataToDisplayCopy.forEach(data -> mergeWorseWeatherData(result, data));
        return result;
    }

    private static void mergeWorseWeatherData(WeatherDataToDisplay dataA, WeatherDataToDisplay dataB) {
        mergeCityName(dataA, dataB);
        mergeCountry(dataA, dataB);
        mergeWeatherParameter(dataA, dataB);
        mergeWeatherDetails(dataA, dataB);
        mergeAirQuality(dataA, dataB);
        mergeWeatherIcons(dataA, dataB);
    }

    private static void mergeCityName(WeatherDataToDisplay dataA, WeatherDataToDisplay dataB) {
        if (dataA.getCityName().equals("Unknown")) {
            if (dataB.getCityName() != null) {
                dataA.setCityName(dataB.getCityName());
            }
        } else {
            if (dataB.getCityName() != null) {
                dataA.setCityName(dataA.getCityName() + " & " + dataB.getCityName());
            }
        }
    }

    private static void mergeCountry(WeatherDataToDisplay dataA, WeatherDataToDisplay dataB) {
        if (dataA.getCountry().equals("Unknown")) {
            if (dataB.getCountry() != null) {
                dataA.setCountry(dataB.getCountry());
            }
        } else {
            if (dataB.getCountry() != null) {
                dataA.setCountry(dataA.getCountry() + " & " + dataB.getCountry());
            }
        }
    }

    private static void mergeWeatherParameter(WeatherDataToDisplay dataA, WeatherDataToDisplay dataB) {
        if (dataA.getWeatherParameter().equals("Unknown")) {
            if (dataB.getWeatherParameter() != null) {
                dataA.setWeatherParameter(dataB.getWeatherParameter());
            }
        } else {
            if (dataB.getWeatherParameter() != null) {
                dataA.setWeatherParameter(dataA.getWeatherParameter() + " & " + dataB.getWeatherParameter());
            }
        }
    }

    private static void mergeWeatherDetails(WeatherDataToDisplay dataA, WeatherDataToDisplay dataB) {
        dataA.setTemperature(Math.min(dataA.getTemperature(), dataB.getTemperature()));
        dataA.setPressure(Math.max(dataA.getPressure(), dataB.getPressure()));
        dataA.setHumidity(Math.max(dataA.getHumidity(), dataB.getHumidity()));
        dataA.setWindSpeed(Math.max(dataA.getWindSpeed(), dataB.getWindSpeed()));
        dataA.setRain(Math.max(dataA.getRain(), dataB.getRain()));
        dataA.setSnow(Math.max(dataA.getSnow(), dataB.getSnow()));
    }

    private static void mergeAirQuality(WeatherDataToDisplay dataA, WeatherDataToDisplay dataB) {
        if (dataA.getAirQuality().equals("Unknown")) {
            if (dataB.getAirQuality() != null) {
                dataA.setAirQuality(dataB.getAirQuality());
            }
        } else {
            if (dataB.getAirQuality() != null) {
                dataA.setAirQuality(Integer.parseInt(dataA.getAirQuality()) >= Integer.parseInt(dataB.getAirQuality()) ? dataA.getAirQuality() : dataB.getAirQuality());
            }
        }
    }

    private static void mergeWeatherIcons(WeatherDataToDisplay dataA, WeatherDataToDisplay dataB) {
        if (dataB.getIcon() != null) {
            dataA.addIconToList(dataB.getIcon());
        }
    }

}