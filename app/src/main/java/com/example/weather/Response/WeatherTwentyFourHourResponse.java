package com.example.weather.Response;

import java.util.List;

public class WeatherTwentyFourHourResponse {
    private String code;
    private String updateTime;
    private String fxLink;
    private List<HourlyForecast> hourly;
    private Refer refer;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFxLink() {
        return fxLink;
    }

    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }

    public List<HourlyForecast> getHourly() {
        return hourly;
    }

    public void setHourly(List<HourlyForecast> hourly) {
        this.hourly = hourly;
    }

    public Refer getRefer() {
        return refer;
    }

    public void setRefer(Refer refer) {
        this.refer = refer;
    }

    public WeatherTwentyFourHourResponse(String code, String updateTime, String fxLink, List<HourlyForecast> hourly, Refer refer) {
        this.code = code;
        this.updateTime = updateTime;
        this.fxLink = fxLink;
        this.hourly = hourly;
        this.refer = refer;
    }

    public static class HourlyForecast {
        private String fxTime;
        private String temp;
        private String icon;
        private String text;
        private String wind360;
        private String windDir;
        private String windScale;
        private String windSpeed;
        private String humidity;
        private String pop;
        private String precip;
        private String pressure;
        private String cloud;
        private String dew;

        public HourlyForecast(String fxTime, String temp, String icon, String text, String wind360, String windDir, String windScale, String windSpeed, String humidity, String pop, String precip, String pressure, String cloud, String dew) {
            this.fxTime = fxTime;
            this.temp = temp;
            this.icon = icon;
            this.text = text;
            this.wind360 = wind360;
            this.windDir = windDir;
            this.windScale = windScale;
            this.windSpeed = windSpeed;
            this.humidity = humidity;
            this.pop = pop;
            this.precip = precip;
            this.pressure = pressure;
            this.cloud = cloud;
            this.dew = dew;
        }

        public String getFxTime() {
            return fxTime;
        }

        public void setFxTime(String fxTime) {
            this.fxTime = fxTime;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getWind360() {
            return wind360;
        }

        public void setWind360(String wind360) {
            this.wind360 = wind360;
        }

        public String getWindDir() {
            return windDir;
        }

        public void setWindDir(String windDir) {
            this.windDir = windDir;
        }

        public String getWindScale() {
            return windScale;
        }

        public void setWindScale(String windScale) {
            this.windScale = windScale;
        }

        public String getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(String windSpeed) {
            this.windSpeed = windSpeed;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public String getPrecip() {
            return precip;
        }

        public void setPrecip(String precip) {
            this.precip = precip;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getCloud() {
            return cloud;
        }

        public void setCloud(String cloud) {
            this.cloud = cloud;
        }

        public String getDew() {
            return dew;
        }

        public void setDew(String dew) {
            this.dew = dew;
        }

        // getters and setters
    }

    public static class Refer {
        private List<String> sources;
        private List<String> license;

        public Refer(List<String> sources, List<String> license) {
            this.sources = sources;
            this.license = license;
        }

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
    }
}
