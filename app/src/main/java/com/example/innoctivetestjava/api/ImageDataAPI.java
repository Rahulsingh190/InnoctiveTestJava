package com.example.innoctivetestjava.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageDataAPI {

    @GET("v1/images/search")
    Call<List<ImageData>> fetchImageData(@Query("limit") int limit, @Query("page") int page);
}
