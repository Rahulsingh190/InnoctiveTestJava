package com.example.innoctivetestjava.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiController {

    private static ApiController instance = null;
    private ImageDataAPI myApi;

    private ApiController() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.thecatapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(ImageDataAPI.class);
    }

    public static synchronized ApiController getInstance() {
        if (instance == null) {
            instance = new ApiController();
        }
        return instance;
    }

    public ImageDataAPI getMyApi() {
        return myApi;
    }
}