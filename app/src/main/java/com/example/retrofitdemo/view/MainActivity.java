package com.example.retrofitdemo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.retrofitdemo.R;
import com.example.retrofitdemo.RecylerViewAdapter;
import com.example.retrofitdemo.model.CyrptoModel;
import com.example.retrofitdemo.service.CyrptoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    ArrayList<CyrptoModel> cyrptoModels;
    private String BASE_URL = "https://raw.githubusercontent.com/";
    Retrofit retrofit;
    RecyclerView recyclerView;

    RecylerViewAdapter recylerViewAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json


        recyclerView = findViewById(R.id.recyclerView);


        // Retrofit oluşturma
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

        loadData();
    }



    // Json Veriyi çekme
    private void loadData(){
        CyrptoAPI cyrptoAPI = retrofit.create(CyrptoAPI.class);
        Call<List<CyrptoModel>> call = cyrptoAPI.getData();
        call.enqueue(new Callback<List<CyrptoModel>>() {
            @Override
            public void onResponse(Call<List<CyrptoModel>> call, Response<List<CyrptoModel>> response) {
                List<CyrptoModel> responseList = response.body();
                // Listeyi ArrayList'e çevirme
                cyrptoModels = new ArrayList<>(responseList);


                //Adapter bağlama
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recylerViewAdapter = new RecylerViewAdapter(cyrptoModels);
                recyclerView.setAdapter(recylerViewAdapter);



            for(CyrptoModel cyrptoModel: cyrptoModels)
                {
                    System.out.println(cyrptoModel.currency);
                }

            }

            @Override
            public void onFailure(Call<List<CyrptoModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}