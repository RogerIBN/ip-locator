package com.accenture.modern_cloud_engineering.ip_locator.models;

public class IpAddress {
    private String ip;

    public IpAddress(String ip) {
        this.ip = ip;
    }

    /**
     * Important to have a default constructor for IpInformation. Otherwise it will
     * not be able to deserialize the JSON response from the API.
     */
    public IpAddress() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
