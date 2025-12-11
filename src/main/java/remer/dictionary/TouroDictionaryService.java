package remer.dictionary;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TouroDictionaryService
{
    // Looks up a word in the dictionary
    @POST("/")
    Single<DictionaryResponse> lookupWord(@Body DictionaryRequest request);
}