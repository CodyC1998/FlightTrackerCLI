package com.keyin.api;

import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApiClientTest {

    @Test
    void getAirportsByCity_returnsRawJson() throws IOException {
        // given
        int cityId = 42;
        String expectedJson = "[{\"id\":1,\"name\":\"YYT Airport\",\"code\":\"YYT\",\"city\":42}]";
        String endpoint = "https://sdat-s4-sprint-backend.onrender.com/cities/42/airports";

        // mock static Request.get(...) and its execute/returnContent/asString chain
        try (MockedStatic<Request> mockRequest = mockStatic(Request.class)) {
            Request fakeReq = mock(Request.class);
            Response fakeResp = mock(Response.class);
            Content fakeContent = mock(Content.class);

            // when Request.get(endpoint) is called, return our fakeReq
            mockRequest.when(() -> Request.get(endpoint)).thenReturn(fakeReq);

            // chain the fluent calls
            when(fakeReq.execute()).thenReturn(fakeResp);
            when(fakeResp.returnContent()).thenReturn(fakeContent);
            when(fakeContent.asString()).thenReturn(expectedJson);

            // exercise
            String actual = ApiClient.getAirportsByCity(cityId);

            // verify & assert
            assertEquals(expectedJson, actual);
            mockRequest.verify(() -> Request.get(endpoint), times(1));
            verify(fakeReq, times(1)).execute();
            verify(fakeResp, times(1)).returnContent();
            verify(fakeContent, times(1)).asString();
        }
    }
}
