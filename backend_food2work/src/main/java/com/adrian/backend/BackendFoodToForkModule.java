package com.adrian.backend;

import com.adrian.backend.interactors.InteractorFood;
import com.adrian.backend.services.ServiceFood;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by adrianjez on 06.12.2017.
 */

@Module
public class BackendFoodToForkModule {

    private String mURL;
    private String mAPIKey;

    public BackendFoodToForkModule(String url, String APIKey) {
        this.mURL = url;
        this.mAPIKey = APIKey;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mURL)
                .client(httpClient)
                .build();
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);
        httpClient.followRedirects(true);

        // Uncomment to see logs
        /*
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(interceptor);
*/
        return httpClient.build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    @Named("api_key")
    String providesAPIKey(){
        return mAPIKey;
    }


    //** Backend interactors
    @Provides
    @Singleton
    InteractorFood providesInteractorFood(Retrofit retrofit, @Named("api_key") String apiKey){
        return new ServiceFood(retrofit, apiKey);
    }
}