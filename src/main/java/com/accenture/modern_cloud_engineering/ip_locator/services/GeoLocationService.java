package com.accenture.modern_cloud_engineering.ip_locator.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.accenture.modern_cloud_engineering.ip_locator.models.GeoData;
import com.accenture.modern_cloud_engineering.ip_locator.models.IpInformation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeoLocationService {

    /**
     * Gets the position of the user using the ipify and ipinfo APIs
     *
     * @param positionController
     * @param ipAddress
     * @return The position of the user in the format [latitude, longitude]
     * @throws IOException
     * @throws InterruptedException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */
    public GeoData getGeoData(String ipAddress)
            throws IOException, InterruptedException, JsonProcessingException, JsonMappingException {
        String ipinfoEndPoint = "https://ipinfo.io/" + ipAddress + "/geo";
        GeoData geoData = null;

        try {
            String ipinfoResponse = this.makeRequest(ipinfoEndPoint);
            ObjectMapper mapper = new ObjectMapper();
            geoData = mapper.readValue(ipinfoResponse, GeoData.class);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        if (geoData == null) {
            throw new IOException("The geoData object is null");
        }

        return geoData;
    }

    /**
     * Gets the public IP address from ipify.org
     *
     * @return the public IP address
     * @throws IOException
     * @throws InterruptedException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */
    public String getIpAddress()
            throws IOException, InterruptedException, JsonProcessingException, JsonMappingException {
        String ipifyEndPoint = "https://api.ipify.org?format=json";
        String ipAddress = null;

        try {
            String ipifyResponse = this.makeRequest(ipifyEndPoint);

            ObjectMapper mapper = new ObjectMapper();
            IpInformation ipInformation = mapper.readValue(ipifyResponse, IpInformation.class);

            ipAddress = ipInformation.getIp();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ipAddress;
    }

    /**
     * This method makes a request to the specified endpoint and returns the
     * response body
     *
     * @param endPoint
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public String makeRequest(String endPoint) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endPoint))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        String body = response.body();
        return body;
    }

}
