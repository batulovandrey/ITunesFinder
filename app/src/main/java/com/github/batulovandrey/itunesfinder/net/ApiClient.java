package com.github.batulovandrey.itunesfinder.net;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Andrey Batulov on 01/08/2017
 */

public class ApiClient {

    private static final String BASE_URL = "https://itunes.apple.com/";
    private static Retrofit sRetrofit = null;

    private ApiClient() { throw new IllegalStateException("Can't create object"); }

    public static Retrofit getRetrofit() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}