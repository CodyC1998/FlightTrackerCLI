package com.keyin.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ApiClientTest {
    private ApiClient.HttpExecutor mockExecutor;
    private ApiClient apiClient;

    @BeforeEach
    void setUp() {
        mockExecutor = mock(ApiClient.HttpExecutor.class);
        apiClient = new ApiClient(mockExecutor);
    }

    @Test
    void getAirportsByCity_shouldCallExecutorWithCorrectUrl() throws IOException {
        int cityId = 7;
        String expected = "[{'id':1}]";
        String url = "https://sdat-s4-sprint-backend.onrender.com/cities/7/airports";
        when(mockExecutor.get(eq(url))).thenReturn(expected);

        String actual = apiClient.getAirportsByCity(cityId);

        assertEquals(expected, actual);
        verify(mockExecutor, times(1)).get(url);
    }

    @Test
    void getAircraftByPassenger_shouldCallExecutorWithCorrectUrl() throws IOException {
        int passengerId = 3;
        String expected = "[{'type':'Boeing 737'}]";
        String url = "https://sdat-s4-sprint-backend.onrender.com/passengers/3/aircraft";
        when(mockExecutor.get(eq(url))).thenReturn(expected);

        String actual = apiClient.getAircraftByPassenger(passengerId);

        assertEquals(expected, actual);
        verify(mockExecutor).get(url);
    }

    @Test
    void getAirportsForAircraft_shouldCallExecutorWithCorrectUrl() throws IOException {
        int aircraftId = 5;
        String expected = "[{'code':'YYC'}]";
        String url = "https://sdat-s4-sprint-backend.onrender.com/aircraft/5/airports";
        when(mockExecutor.get(eq(url))).thenReturn(expected);

        String actual = apiClient.getAirportsForAircraft(aircraftId);

        assertEquals(expected, actual);
        verify(mockExecutor).get(url);
    }

    @Test
    void getAirportsUsedByPassenger_shouldCallExecutorWithCorrectUrl() throws IOException {
        int passengerId = 12;
        String expected = "[{'name':'John Doe Airport'}]";
        String url = "https://sdat-s4-sprint-backend.onrender.com/passengers/12/airports";
        when(mockExecutor.get(eq(url))).thenReturn(expected);

        String actual = apiClient.getAirportsUsedByPassenger(passengerId);

        assertEquals(expected, actual);
        verify(mockExecutor).get(url);
    }
}
