package remer.dictionary;
/*
This class holds the data from the incoming JSON request (incoming)
*/

public class DictionaryResponse
{
    private String word;
    private String definition;

    public DictionaryResponse(String word, String definition)
    {
        this.word = word;
        this.definition = definition;
    }

    public String getWord()
    {
        return word;
    }

    public String getDefinition()
    {
        return definition;
    }
}
