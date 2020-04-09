package com.example.cryptobag.Entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoinLoreResponse {


    @SerializedName("data")
    @Expose
    private List<Coin> data = null;



    public List<Coin> getData() {

        return data;
    }

    public void setData(List<Coin> data) {

        this.data = data;
    }


}


