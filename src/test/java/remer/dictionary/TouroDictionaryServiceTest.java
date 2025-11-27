package remer.dictionary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TouroDictionaryServiceTest
{
    private TouroDictionaryService service;

    @BeforeEach
    public void setUp() throws IOException
    {
        TouroDictionaryServiceFactory factory = new TouroDictionaryServiceFactory();
        service = factory.createService();
    }

    @Test
    public void lookupWord_Success() throws IOException
    {
        // Given
        String word = "AA";
        DictionaryRequest request = new DictionaryRequest(word);

        // When
        Call<DictionaryResponse> call = service.lookupWord(request);
        Response<DictionaryResponse> response = call.execute();

        // Then
        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
        assertEquals(word, response.body().getWord());
        assertEquals("rough, cindery lava [n -S]", response.body().getDefinition());
    }

    @Test
    public void lookupWord_WordNotFound() throws IOException
    {
        // Given
        String word = "NONEXISTENTWORD";
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
    public void lookupWord_ServerError() throws IOException
    {
        // Given
        String word = "aa";
        DictionaryRequest request = new DictionaryRequest(word);

        // When
        Call<DictionaryResponse> call = service.lookupWord(request);
        Response<DictionaryResponse> response = call.execute();

        // Then
        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
        assertEquals(word, response.body().getWord());
        assertEquals("rough, cindery lava [n -S]", response.body().getDefinition());
    }
}