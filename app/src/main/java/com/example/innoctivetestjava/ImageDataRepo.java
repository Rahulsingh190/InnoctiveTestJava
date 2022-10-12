package com.example.innoctivetestjava;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.innoctivetestjava.api.ApiController;
import com.example.innoctivetestjava.api.ImageData;
import com.example.innoctivetestjava.api.ImageDataAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageDataRepo {
    private final String TAG = getClass().getSimpleName();

    public MutableLiveData<List<ImageData>> requestImages(int limit, int page) {
        final MutableLiveData<List<ImageData>> mutableLiveData = new MutableLiveData<>();

        ImageDataAPI apiService =
                ApiController.getInstance().getMyApi();

        apiService.fetchImageData(limit, page).enqueue(new Callback<List<ImageData>>() {
            @Override
            public void onResponse(Call<List<ImageData>> call, Response<List<ImageData>> response) {
                Log.d(TAG, "getCurrencyList response="+response );

                if (response.isSuccessful() && response.body()!=null ) {
                    Log.e(TAG, "requestHolidays response.size="+response.body().size() );
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ImageData>> call, Throwable t) {
                Log.e(TAG, "getProdList onFailure" + call.toString());
            }
        });

        return mutableLiveData;
    }
}
