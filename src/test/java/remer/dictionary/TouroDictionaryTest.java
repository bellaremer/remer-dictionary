package remer.dictionary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TouroDictionaryTest
{
    private TouroDictionary dictionary;

    @BeforeEach
    public void setUp()
    {
        dictionary = new TouroDictionary();
    }

    @Test
    public void lookUpWord()
    {
        // Given
        String word = "AA";
        String expected = "rough, cindery lava [n -S]";

        // When
        String result = dictionary.lookup(word);

        // Then
        assertEquals(expected, result);
    }

    @Test
    public void lookupWordNotFound()
    {
        // Given
        String word = "NONEXISTENTWORD";
        String expected = null;

        // When
        String result = dictionary.lookup(word);

        // Then
        assertEquals(expected, result, "Non-existent word should return null");
    }

    @Test
    public void lookupNullWord()
    {
        // Given
        String word = null;
        String expected = null;

        // When
        String result = dictionary.lookup(word);

        // Then
        assertEquals(expected, result, "Null word should return null");
    }

    @Test
    public void lookupEmptyWord()
    {
        // Given
        String word = "";
        String expected = null;

        // When
        String result = dictionary.lookup(word);

        // Then
        assertEquals(expected, result, "Empty word should return null");
    }

    @Test
    public void lookupCaseInsensitive()
    {
        // Given
        String word = "aa";
        String expected = "rough, cindery lava [n -S]";

        // When
        String result = dictionary.lookup(word);

        // Then
        assertEquals(expected, result, "Lowercase should work");
    }
}
