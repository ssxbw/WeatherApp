package com.example.weather.Domains;

public class Hourly {

    private String hour;

    private String temperature;

    private String picCode;

    public Hourly() {
        this.hour = "- -";
        this.picCode = "100";
        this.temperature = "-";
    }

    public Hourly(String hour, String temperature, String picCode) {
        this.hour = hour;
        this.temperature = temperature;
        this.picCode = picCode;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setPicCode(String picCode) {
        this.picCode = picCode;
    }

    public String getHour() {
        return hour;
    }

    public String getPicCode() {
        return picCode;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperature() {
        return temperature;
    }

    public void refresh() {
        this.hour = "- -";
        this.picCode = "100";
        this.temperature = "-";
    }
}
