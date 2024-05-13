package com.example.weather.Response;

import java.util.List;

public class CitySearchResponse {
    private String code;
    private List<Location> location;
    private Refer refer;

    public CitySearchResponse(String code, List<Location> location, Refer refer) {
        this.code = code;
        this.location = location;
        this.refer = refer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }

    public Refer getRefer() {
        return refer;
    }

    public void setRefer(Refer refer) {
        this.refer = refer;
    }

    @Override
    public String toString() {
        return "CitySearchResponse{" +
                "code='" + code + '\'' +
                ", location=" + location +
                ", refer=" + refer +
                '}';
    }

    public static class Location {
        private String name;
        private String id;
        private String lat;
        private String lon;
        private String adm2;
        private String adm1;
        private String country;
        private String tz;
        private String utcOffset;
        private String isDst;
        private String type;
        private String rank;
        private String fxLink;

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

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
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

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getTz() {
            return tz;
        }

        public void setTz(String tz) {
            this.tz = tz;
        }

        public String getUtcOffset() {
            return utcOffset;
        }

        public void setUtcOffset(String utcOffset) {
            this.utcOffset = utcOffset;
        }

        public String getIsDst() {
            return isDst;
        }

        public void setIsDst(String isDst) {
            this.isDst = isDst;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getFxLink() {
            return fxLink;
        }

        public void setFxLink(String fxLink) {
            this.fxLink = fxLink;
        }

        public Location(String name, String id, String lat, String lon, String adm2, String adm1, String country, String tz, String utcOffset, String isDst, String type, String rank, String fxLink) {
            this.name = name;
            this.id = id;
            this.lat = lat;
            this.lon = lon;
            this.adm2 = adm2;
            this.adm1 = adm1;
            this.country = country;
            this.tz = tz;
            this.utcOffset = utcOffset;
            this.isDst = isDst;
            this.type = type;
            this.rank = rank;
            this.fxLink = fxLink;
        }
    }

    public static class Refer {
        private List<String> sources;
        private List<String> license;

        public List<String> getSources() {
            return sources;
        }

        public void setSources(List<String> sources) {
            this.sources = sources;
        }

        public List<String> getLicense() {
            return license;
        }

        public void setLicense(List<String> license) {
            this.license = license;
        }

        public Refer(List<String> sources, List<String> license) {
            this.sources = sources;
            this.license = license;
        }
    }
}
