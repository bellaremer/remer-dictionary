package remer.dictionary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.reactivex.rxjava3.core.Single;

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
        Single<DictionaryResponse> single = service.lookupWord(request);
        DictionaryResponse response = single.blockingGet();

        // Then
        assertNotNull(response);
        assertEquals(word, response.getWord());
        assertEquals("rough, cindery lava [n -S]", response.getDefinition());
    }

    @Test
    public void lookupWord_WordNotFound() throws IOException
    {
        // Given
        String word = "NONEXISTENTWORD";
        DictionaryRequest request = new DictionaryRequest(word);

        // When
        Single<DictionaryResponse> single = service.lookupWord(request);
        DictionaryResponse response = single.blockingGet();

        // Then
        assertNotNull(response);
        assertEquals(word, response.getWord());
        assertNull(response.getDefinition());
    }
}