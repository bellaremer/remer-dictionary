package remer.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class TouroDictionary
{
    private final HashMap<String, String> dictionary;

    public TouroDictionary()
    {
        dictionary = new HashMap<>();
        loadDictionary();
    }

    // New constructor that loads from an InputStream (S3)
    public TouroDictionary(InputStream inputStream)
    {
        dictionary = new HashMap<>();
        loadDictionaryFromStream(inputStream);
    }

    private void loadDictionary()
    {
        InputStream dictionaryFile = TouroDictionary.class.getResourceAsStream("/dictionary.txt");

        if (dictionaryFile == null)
        {
            System.err.println("file not found");
            throw new RuntimeException("Dictionary file not found in resources");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(dictionaryFile)))
        {
            String line;

            while ((line = reader.readLine()) != null)
            {
                int spaceIndex = line.indexOf(' ');
                if (spaceIndex > 0)
                {
                    String word = line.substring(0, spaceIndex).trim().toUpperCase();
                    String definition = line.substring(spaceIndex + 1).trim();
                    dictionary.put(word, definition);
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void loadDictionaryFromStream(InputStream inputStream)
    {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)))
        {
            String line;

            while ((line = reader.readLine()) != null)
            {
                int spaceIndex = line.indexOf(' ');
                if (spaceIndex > 0)
                {
                    String word = line.substring(0, spaceIndex).trim().toUpperCase();
                    String definition = line.substring(spaceIndex + 1).trim();
                    dictionary.put(word, definition);
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String lookup(String word)
    {
        if (word == null || word.trim().isEmpty())
        {
            return null;
        }

        String searchWord = word.trim().toUpperCase();
        return dictionary.get(searchWord);
    }
}
