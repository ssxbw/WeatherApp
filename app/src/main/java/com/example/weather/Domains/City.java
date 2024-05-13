package com.example.weather.Domains;

public class City {

    private String name;
    
    private String id;

    private String weather;

    private String temperature;

    private String picCode;

    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPicCode() {
        return picCode;
    }

    public void setPicCode(String picCode) {
        this.picCode = picCode;
    }

    public City() {
        this.name = "- -";
        this.id = "";
        this.weather = "- -";
        this.temperature = "";
        this.picCode = "100";
        this.selected = false;
    }

    public City(String name, String id, String weather, String temperature, String picCode) {
        this.name = name;
        this.id = id;
        this.weather = weather;
        this.temperature = temperature;
        this.picCode = picCode;
        this.selected = false;
    }
}
