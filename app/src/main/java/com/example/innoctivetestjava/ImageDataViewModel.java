package com.example.innoctivetestjava;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.innoctivetestjava.api.ImageData;

import java.util.List;

public class ImageDataViewModel extends ViewModel {

    private ImageDataRepo holidayRepo;
    private MutableLiveData<List<ImageData>> mutableLiveData;

    public ImageDataViewModel(){
        holidayRepo = new ImageDataRepo();
    }

    public LiveData<List<ImageData>> getImages(int limit, int page) {
        if(mutableLiveData==null){
            mutableLiveData = holidayRepo.requestImages(limit, page);
        }
        return mutableLiveData;
    }

}
