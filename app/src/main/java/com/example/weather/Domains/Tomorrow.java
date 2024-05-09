package com.example.weather.Domains;

public class Tomorrow {
    private String date;

    private String picPath;

    private String status;

    private int highTemperature;

    private int lowTemperature;

    public Tomorrow(String date, String picPath, String status, int highTemperature, int lowTemperature) {
        this.date = date;
        this.picPath = picPath;
        this.status = status;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getHighTemperature() {
        return highTemperature;
    }

    public void setHighTemperature(int highTemperature) {
        this.highTemperature = highTemperature;
    }

    public int getLowTemperature() {
        return lowTemperature;
    }

    public void setLowTemperature(int lowTemperature) {
        this.lowTemperature = lowTemperature;
    }
}
