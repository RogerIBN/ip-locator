package com.accenture.modern_cloud_engineering.ip_locator.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.accenture.modern_cloud_engineering.ip_locator.models.IpAddress;
import com.accenture.modern_cloud_engineering.ip_locator.models.IpInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeoLocationService {

    /**
     * Gets the position of the user using the ipify and ipinfo APIs
     *
     * @param ipAddress
     * @return The position of the user in the format [latitude, longitude]
     * @throws IOException
     * @throws InterruptedException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */
    public IpInfo getIpInfo(String ipAddress)
            throws IOException, InterruptedException, JsonProcessingException, JsonMappingException {
        // Instantiate a new String variable and set it to the URL of the API endpoint.
        String ipinfoEndPoint = "https://ipinfo.io/" + ipAddress + "/geo";

        // Instantiate a new IpInfo object and set it to null.
        IpInfo ipInfo = null;

        try {
            // 1. Make request to ipinfo API
            String ipinfoResponse = this.makeRequest(ipinfoEndPoint);

            // 2. Parse the response into a IpInfo object
            ObjectMapper mapper = new ObjectMapper();
            ipInfo = mapper.readValue(ipinfoResponse, IpInfo.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // If the ipInfo object is null, throw an IOException.
        if (ipInfo == null) {
            throw new IOException("The ipInfo object is null");
        }

        // Return the ipInfo object.
        return ipInfo;
    }

    /**
     * Alternative method to get the ipInfo object from IpAddress object
     *
     * @param ipAddress
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */
    public IpInfo getIpInfo(IpAddress ipAddress)
            throws IOException, InterruptedException, JsonProcessingException, JsonMappingException {
        return getIpInfo(ipAddress.getIp());
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
    public IpAddress getIpAddress()
            throws IOException, InterruptedException, JsonProcessingException, JsonMappingException {
        String ipifyEndPoint = "https://api.ipify.org?format=json";
        IpAddress ipAddress = null;

        try {
            // 1. Make request to IPIFY service
            String ipifyResponse = this.makeRequest(ipifyEndPoint);

            // 2. Parse the response into an IpInformation object
            ObjectMapper mapper = new ObjectMapper();
            ipAddress = mapper.readValue(ipifyResponse, IpAddress.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // If the ipInformation object is null, throw an IOException.
        if (ipAddress == null) {
            throw new IOException("The ipInformation object is null");
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
        // Create request to the API
        HttpRequest request = HttpRequest.newBuilder(URI.create(endPoint)).build();

        // Send request to the API and get the response
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        // Get the response body
        return response.body();
    }

}
