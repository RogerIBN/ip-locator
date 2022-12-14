package com.accenture.modern_cloud_engineering.ip_locator.models;

public class IpInfo {
    private String ip;
    private String city;
    private String region;
    private String country;
    private String loc;
    private String postal;
    private String timezone;
    private String org;
    private String hostname;
    private String readme;

    public IpInfo(
            String ip,
            String city,
            String region,
            String country,
            String loc,
            String postal,
            String timezone,
            String org,
            String hostname,
            String readme) {
        this.ip = ip;
        this.city = city;
        this.region = region;
        this.country = country;
        this.loc = loc;
        this.postal = postal;
        this.timezone = timezone;
        this.org = org;
        this.hostname = hostname;
        this.readme = readme;
    }

    /**
     * Important to have a default constructor for GeoData. Otherwise it will not be
     * able to deserialize the JSON response from the API.
     *
     */
    public IpInfo() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return The latitude is first, followed by
     *         the longitude. (e.g. {@code "19.4285,-99.1277"}).
     */
    public String getLoc() {
        return loc;
    }

    /**
     * @param loc The latitude is first, followed by
     *            the longitude. (e.g. {@code "19.4285,-99.1277"}).
     */
    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getReadme() {
        return readme;
    }

    public void setReadme(String readme) {
        this.readme = readme;
    }
}
