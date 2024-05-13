package com.example.weather.Domains;

public class Tomorrow {
    private String date;

    private String picCode;

    private String weather;

    private String highTemperature;

    private String lowTemperature;

    private String wind;

    private String rain;

    private String humidity;

    public Tomorrow() {
        this.date = "- -";
        this.picCode = "";
        this.weather = "- -";
        this.highTemperature = "-";
        this.lowTemperature = "-";
        this.wind = "- -";
        this.rain = "- -";
        this.humidity = "- -";
    }

    public Tomorrow(String date, String picCode, String weather, String highTemperature, String lowTemperature, String wind, String rain, String humidity) {
        this.date = date;
        this.picCode = picCode;
        this.weather = weather;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.wind = wind;
        this.rain = rain;
        this.humidity = humidity;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPicCode() {
        return picCode;
    }

    public void setPicCode(String picCode) {
        this.picCode = picCode;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String status) {
        this.weather = status;
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

    public void refresh() {
        this.date = "- -";
        this.picCode = "";
        this.weather = "- -";
        this.highTemperature = "-";
        this.lowTemperature = "-";
        this.wind = "- -";
        this.rain = "- -";
        this.humidity = "- -";
    }
}
