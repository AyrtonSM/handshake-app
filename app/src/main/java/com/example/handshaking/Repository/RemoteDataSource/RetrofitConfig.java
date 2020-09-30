package com.example.handshaking.Repository.RemoteDataSource;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private static final String PROTOCOL = "http";
    private static final String HOST = "10.0.0.156";
    private static final String PORT = "8080";
    private static final String BASE_URL = PROTOCOL + "://" + HOST + ":" + PORT;

    private static Retrofit retrofit;


    public static Retrofit getConfiguration(){
        retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;
    }
}
