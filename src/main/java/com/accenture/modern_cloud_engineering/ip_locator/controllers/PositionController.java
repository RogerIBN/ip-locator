package com.accenture.modern_cloud_engineering.ip_locator.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.modern_cloud_engineering.ip_locator.models.GeoData;
import com.accenture.modern_cloud_engineering.ip_locator.models.OpenStreetMap;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class PositionController {
    /**
     * Get the user's position using the ipify and ipinfo APIs
     * 
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @ResponseBody
    @GetMapping("api/v1/position")
    public OpenStreetMap getUserPosition() throws IOException, InterruptedException {

        String ipifyEndPoint = "https://api.ipify.org?format=json";
        String ipAddress = null;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ipifyEndPoint))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String ipifyResponse = response.body();

            ObjectMapper mapper = new ObjectMapper();
            ipAddress = mapper.readTree(ipifyResponse).get("ip").asText();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String ipinfoEndPoint = "https://ipinfo.io/" + ipAddress + "/geo";
        GeoData geoData = null;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ipinfoEndPoint))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String ipinfoResponse = response.body();

            ObjectMapper mapper = new ObjectMapper();
            geoData = mapper.readValue(ipinfoResponse, GeoData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Split the position string into latitude and longitude
        String[] position = geoData.getLoc().split(",");
        String latitude = position[0];
        String longitude = position[1];

        // Fill the string with 0's on the left
        latitude = String.format("%.15f", Double.parseDouble(latitude));
        longitude = String.format("%.14f", Double.parseDouble(longitude));

        // Link examples:
        // "https://www.openstreetmap.org/export/embed.html?bbox=-89.67229843139648%2C20.938113990727835%2C-89.57651138305665%2C20.989409533505412&amp;layer=mapnik&amp;marker=20.963763961508004%2C-89.62440490722656"
        // "https://www.openstreetmap.org/export/embed.html?bbox=-89.62066590785982%2C20.973797313831863%2C-89.61467921733858%2C20.977003035818043&amp;layer=mapnik&amp;marker=20.97540018342022%2C-89.61767256259918"
        // BUG: The map doesn't show the marker
        String mapURL = "https://www.openstreetmap.org/export/embed.html?bbox=" +
                longitude + "%2C" + latitude + "%2C"
                + longitude + "%2C" + latitude + "&amp;layer=mapnik&amp;marker=" + latitude +
                "%2C" + longitude;

        return new OpenStreetMap(mapURL);
    }
}
