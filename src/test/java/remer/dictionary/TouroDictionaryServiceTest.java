package remer.dictionary;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TouroDictionaryServiceTest
{
    private MockWebServer mockWebServer;
    private TouroDictionaryService service;

    @BeforeEach
    public void setUp() throws IOException
    {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        String baseUrl = mockWebServer.url("/").toString();
        TouroDictionaryServiceFactory factory = new TouroDictionaryServiceFactory(baseUrl);
        service = factory.createService();
    }

    @AfterEach
    public void tearDown() throws IOException
    {
        mockWebServer.shutdown();
    }

    @Test
    public void testLookupWord_Success() throws IOException
    {
        // Given
        String word = "AA";
        String definition = "rough, cindery lava [n -S]";
        String mockResponseBody = String.format(
                "{\"word\":\"%s\",\"definition\":\"%s\"}",
                word,
                definition
        );

        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponseBody)
                .setResponseCode(200)
                .addHeader("Content-Type", "application/json"));

        DictionaryRequest request = new DictionaryRequest(word);

        // When
        Call<DictionaryResponse> call = service.lookupWord(request);
        Response<DictionaryResponse> response = call.execute();

        // Then
        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
        assertEquals(word, response.body().getWord());
        assertEquals(definition, response.body().getDefinition());
    }

    @Test
    public void testLookupWord_WordNotFound() throws IOException
    {
        // Given
        String word = "NONEXISTENTWORD";
        String mockResponseBody = String.format(
                "{\"word\":\"%s\",\"definition\":null}",
                word
        );

        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponseBody)
                .setResponseCode(200)
                .addHeader("Content-Type", "application/json"));

        DictionaryRequest request = new DictionaryRequest(word);

        // When
        Call<DictionaryResponse> call = service.lookupWord(request);
        Response<DictionaryResponse> response = call.execute();

        // Then
        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
        assertEquals(word, response.body().getWord());
        assertNull(response.body().getDefinition());
    }

    @Test
    public void testLookupWord_ServerError() throws IOException
    {
        // Given
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .setBody("Internal Server Error"));

        DictionaryRequest request = new DictionaryRequest("TEST");

        // When
        Call<DictionaryResponse> call = service.lookupWord(request);
        Response<DictionaryResponse> response = call.execute();

        // Then
        assertFalse(response.isSuccessful());
        assertEquals(500, response.code());
    }
}