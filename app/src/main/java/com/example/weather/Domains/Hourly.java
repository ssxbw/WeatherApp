package com.example.weather.Domains;

public class Hourly {

    private String hour;

    private int temperature;

    public Hourly(String hour, int temperature, String picPath) {
        this.hour = hour;
        this.temperature = temperature;
        this.picPath = picPath;
    }

    private String picPath;

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getHour() {
        return hour;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }
}
