package com.accenture.modern_cloud_engineering.ip_locator.models;

public class OpenStreetMap {
    private String url;

    public OpenStreetMap() {
    }

    public OpenStreetMap(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "OpenStreetMap [url=" + url + "]";
    }

}
