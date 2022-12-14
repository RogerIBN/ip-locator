package com.accenture.modern_cloud_engineering.ip_locator.models;

public class OpenStreetMap {
    private String url;

    public OpenStreetMap(IpInfo ipInfo) {
        String[] location = ipInfo.getLoc().split(",");
        String url = createUrl(location);
        this.url = url;
    }

    public OpenStreetMap(String[] location) {
        String url = createUrl(location);
        this.url = url;
    }

    public OpenStreetMap(String url) {
        this.url = url;
    }

    public OpenStreetMap() {
    }

    /**
     * Create the URL for the OpenStreetMap object
     *
     * @param location The latitude and longitude
     * @return The URL
     */
    private String createUrl(String[] location) {
        // Split the position string into latitude and longitude
        String latitude = location[0];
        String longitude = location[1];

        // Link examples:
        // "https://www.openstreetmap.org/export/embed.html?bbox=-89.67229843139648%2C20.938113990727835%2C-89.57651138305665%2C20.989409533505412&amp;layer=mapnik&amp;marker=20.963763961508004%2C-89.62440490722656"
        // "https://www.openstreetmap.org/export/embed.html?bbox=-89.62066590785982%2C20.973797313831863%2C-89.61467921733858%2C20.977003035818043&amp;layer=mapnik&amp;marker=20.97540018342022%2C-89.61767256259918"
        // BUG: The map doesn't show the marker

        // Create the URL
        return "https://www.openstreetmap.org/export/embed.html?bbox=" +
                longitude + "%2C" + latitude + "%2C"
                + longitude + "%2C" + latitude + "&amp;layer=mapnik&amp;marker=" + latitude +
                "%2C" + longitude;
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
