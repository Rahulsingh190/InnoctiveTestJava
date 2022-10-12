package com.example.innoctivetestjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.innoctivetestjava.api.ImageData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fetchImages(page);

        Button prevButton = (Button) findViewById(R.id.prevButton);
        Button nextButton = (Button) findViewById(R.id.nextButton);

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page>=1) {
                    fetchImages(page--);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchImages(page++);
            }
        });


    }

    private void fetchImages(int page){
        ImageDataViewModel holidayViewModel = new ImageDataViewModel();

        holidayViewModel.getImages(100, page).observe((LifecycleOwner) this, new Observer<List<ImageData>>() {
            @Override
            public void onChanged(List<ImageData> imageList) {
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

                // Setting the layout as Staggered Grid for vertical orientation
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);

                // Sending reference and data to Adapter
                Adapter adapter = new Adapter(MainActivity.this, (ArrayList) imageList);

                // Setting Adapter to RecyclerView
                recyclerView.setAdapter(adapter);
            }
        });
    }
}