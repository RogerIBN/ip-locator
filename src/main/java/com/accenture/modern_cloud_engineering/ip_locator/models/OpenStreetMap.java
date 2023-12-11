package com.accenture.modern_cloud_engineering.ip_locator.models;

public class OpenStreetMap {
    private String url;

    public OpenStreetMap() {
    }

    public OpenStreetMap(String url) {
        this.url = url;
    }

    public OpenStreetMap(String[] location) {
        this(createUrl(location));
    }

    public OpenStreetMap(IpInfo ipInfo) {
        this(ipInfo.getLoc().split(","));
    }

    /**
     * Create the URL for the OpenStreetMap object
     *
     * @param location The latitude and longitude
     * @return The URL
     */
    private static String createUrl(String[] location) {
        // Split the position string into latitude and longitude
        String latitude = location[0];
        String longitude = location[1];

        // Fill the latitude and longitude in the URL with 0's on the right to fill 18
        // characters
        latitude = "%-18s".formatted(latitude).replace(' ', '0');
        longitude = "%-18s".formatted(longitude).replace(' ', '0');
        // Link examples:
        // "https://www.openstreetmap.org/export/embed.html?bbox=-89.67229843139648%2C20.938113990727835%2C-89.57651138305665%2C20.989409533505412&amp;layer=mapnik&amp;marker=20.963763961508004%2C-89.62440490722656"
        // "https://www.openstreetmap.org/export/embed.html?bbox=-89.62066590785982%2C20.973797313831863%2C-89.61467921733858%2C20.977003035818043&amp;layer=mapnik&amp;marker=20.97540018342022%2C-89.61767256259918"
        // BUG: The map doesn't show the marker

        // Create the URL

        String urlFormat = "https://www.openstreetmap.org/export/embed.html?bbox=%2$s%%2C%1$s%%2C%2$s%%2C%1$s&amp;layer=mapnik&amp;marker=%1$s%%2C%2$s";
        return String.format(urlFormat, latitude, longitude);
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
