package com.example.weather.Domains;

public class CityMain {

    String name;

    String id;

    String updateTime;

    String weather;

    String temperature;

    String highTemperature;

    String lowTemperature;

    String wind;

    String humidity;

    String rain;

    String picCode;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHighTemperature() {
        return highTemperature;
    }

    public void setHighTemperature(String highTemperature) {
        this.highTemperature = highTemperature;
    }

    public String getLowTemperature() {
        return lowTemperature;
    }

    public void setLowTemperature(String lowTemperature) {
        this.lowTemperature = lowTemperature;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getPicCode() {
        return picCode;
    }

    public void setPicCode(String picCode) {
        this.picCode = picCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CityMain(String name, String id, String updateTime, String weather, String temperature, String highTemperature, String lowTemperature, String wind, String humidity, String rain, String picCode) {
        this.name = name;
        this.id = id;
        this.updateTime = updateTime;
        this.weather = weather;
        this.temperature = temperature;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.wind = wind;
        this.humidity = humidity;
        this.rain = rain;
        this.picCode = picCode;
    }

    public CityMain() {
        this.name = "- -";
        this.id = "";
        this.updateTime = "- -";
        this.weather = "- -";
        this.temperature = "- -";
        this.highTemperature = "-";
        this.lowTemperature = "-";
        this.wind = "- -";
        this.humidity = "- -";
        this.rain = "- -";
        this.picCode = "100";
    }

    public void refresh() {
        this.name = "- -";
        this.id = "";
        this.updateTime = "- -";
        this.weather = "- -";
        this.temperature = "- -";
        this.highTemperature = "-";
        this.lowTemperature = "-";
        this.wind = "- -";
        this.humidity = "- -";
        this.rain = "- -";
        this.picCode = "100";
    }
}
