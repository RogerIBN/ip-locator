package com.accenture.modern_cloud_engineering.ip_locator.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.modern_cloud_engineering.ip_locator.models.IpInfo;
import com.accenture.modern_cloud_engineering.ip_locator.models.IpAddress;
import com.accenture.modern_cloud_engineering.ip_locator.models.OpenStreetMap;
import com.accenture.modern_cloud_engineering.ip_locator.services.GeoLocationService;

@RestController
public class PositionController {

    @Autowired
    public GeoLocationService geoLocationService;

    @ResponseBody
    @GetMapping("api/v1/position")
    public OpenStreetMap getUserPosition() throws IOException, InterruptedException {

        // 1. Request the public IP address from ipify.org
        IpAddress ipAddress = geoLocationService.getIpAddress();

        // 2. Request the IP information from ipinfo.io
        IpInfo ipInfo = geoLocationService.getIpInfo(ipAddress);

        // 3. Return the OpenStreetMap object
        return new OpenStreetMap(ipInfo);
    }
}
