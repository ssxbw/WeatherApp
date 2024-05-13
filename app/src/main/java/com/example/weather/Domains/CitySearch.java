package com.example.weather.Domains;

public class CitySearch {

    private String name;

    private String id;

    private String adm2;

    private String adm1;

    public CitySearch(String name, String id, String adm2, String adm1) {
        this.name = name;
        this.id = id;
        this.adm2 = adm2;
        this.adm1 = adm1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdm2() {
        return adm2;
    }

    public void setAdm2(String adm2) {
        this.adm2 = adm2;
    }

    public String getAdm1() {
        return adm1;
    }

    public void setAdm1(String adm1) {
        this.adm1 = adm1;
    }
}
