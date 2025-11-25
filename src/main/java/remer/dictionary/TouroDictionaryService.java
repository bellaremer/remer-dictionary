package remer.dictionary;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TouroDictionaryService
{
    // Looks up a word in the dictionary
    @POST("/")
    Call<DictionaryResponse> lookupWord(@Body DictionaryRequest request);
}