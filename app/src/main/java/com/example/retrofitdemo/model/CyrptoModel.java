package com.example.retrofitdemo.model;

import com.google.gson.annotations.SerializedName;

public class CyrptoModel{

    @SerializedName("currency")
    public String currency;


    @SerializedName("price")
    public String price;
}
