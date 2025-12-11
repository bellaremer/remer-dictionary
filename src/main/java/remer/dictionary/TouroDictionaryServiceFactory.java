package remer.dictionary;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Factory class to create and configure the TouroDictionaryService
 */
public class TouroDictionaryServiceFactory
{
    // AWS Lambda function URL
    private final String baseUrl;

    public TouroDictionaryServiceFactory()
    {
        this.baseUrl = "https://hqdfh74wspl3aopph7ozjf26fy0aikig.lambda-url.us-east-2.on.aws/";
    }

    // Creates a configured TouroDictionaryService instance
    public TouroDictionaryServiceFactory(String baseUrl)
    {
        this.baseUrl = baseUrl;
    }

    // Creates a configured TouroDictionaryService instance with the base URL
    public TouroDictionaryService createService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        return retrofit.create(TouroDictionaryService.class);
    }
}