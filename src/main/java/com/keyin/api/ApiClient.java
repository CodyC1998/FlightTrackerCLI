// ApiClient.java
package com.keyin.api;

import org.apache.hc.client5.http.fluent.Request;
import java.io.IOException;
import java.util.Objects;

/**
 * Client for calling SDAT S4 Sprint backend endpoints.
 */
public class ApiClient {
    private static final String BASE_URL = "https://sdat-s4-sprint-backend.onrender.com/";
    private final HttpExecutor httpExecutor;

    /**
     * Functional interface for executing HTTP GET requests.
     */
    public interface HttpExecutor {
        String get(String uri) throws IOException;
    }

    /**
     * Default executor using Apache HTTPClient fluent API.
     */
    private static class DefaultHttpExecutor implements HttpExecutor {
        @Override
        public String get(String uri) throws IOException {
            return Request.get(uri)
                    .execute()
                    .returnContent()
                    .asString();
        }
    }

    /**
     * Creates an ApiClient with the default HTTP executor.
     */
    public ApiClient() {
        this(new DefaultHttpExecutor());
    }

    /**
     * Creates an ApiClient with a custom HTTP executor (for testing).
     */
    public ApiClient(HttpExecutor httpExecutor) {
        this.httpExecutor = Objects.requireNonNull(httpExecutor, "httpExecutor must not be null");
    }

    public String getAirportsByCity(int cityId) throws IOException {
        String endpoint = BASE_URL + "cities/" + cityId + "/airports";
        return httpExecutor.get(endpoint);
    }

    public String getAircraftByPassenger(int passengerId) throws IOException {
        String endpoint = BASE_URL + "passengers/" + passengerId + "/aircraft";
        return httpExecutor.get(endpoint);
    }

    public String getAirportsForAircraft(int aircraftId) throws IOException {
        String endpoint = BASE_URL + "aircraft/" + aircraftId + "/airports";
        return httpExecutor.get(endpoint);
    }

    public String getAirportsUsedByPassenger(int passengerId) throws IOException {
        String endpoint = BASE_URL + "passengers/" + passengerId + "/airports";
        return httpExecutor.get(endpoint);
    }
}