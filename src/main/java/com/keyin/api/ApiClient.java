package com.keyin.api;

import org.apache.hc.client5.http.fluent.Request;
import java.io.IOException;

public class ApiClient {
    private static final String BASE_URL = "https://sdat-s4-sprint-backend.onrender.com/";

    public static String getAirportsByCity(int cityId) throws IOException {
        String endpoint = BASE_URL + "cities/" + cityId + "/airports";
        return Request.get(endpoint).execute().returnContent().asString();
    }

    public static String getAircraftByPassenger(int passengerId) throws IOException {
        String endpoint = BASE_URL + "passengers/" + passengerId + "/aircraft";
        return Request.get(endpoint).execute().returnContent().asString();
    }

    public static String getAirportsForAircraft(int aircraftId) throws IOException {
        String endpoint = BASE_URL + "aircraft/" + aircraftId + "/airports";
        return Request.get(endpoint).execute().returnContent().asString();
    }

    public static String getAirportsUsedByPassenger(int passengerId) throws IOException {
        String endpoint = BASE_URL + "passengers/" + passengerId + "/airports";
        return Request.get(endpoint).execute().returnContent().asString();
    }
}