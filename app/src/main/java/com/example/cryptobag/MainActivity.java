package com.example.cryptobag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.cryptobag.Entities.CoinLoreResponse;
import com.example.cryptobag.Entities.Coin;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "MainActivity";

    private RecyclerView.Adapter mAdapter;
    List<Coin> coins;
    Boolean inWide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"Reached");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rview = findViewById(R.id.rview);
        rview.setHasFixedSize(true);
        rview.setLayoutManager(new LinearLayoutManager(this));


        inWide = findViewById(R.id.detailContainer)!=null;
        Log.d(TAG, "boolean " + String.valueOf(inWide));

        MyAdapter.RecyclerViewClickListener listener = new MyAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                clickResponse(position);
            }
        };


        mAdapter = new MyAdapter( new ArrayList<Coin>(), listener);
        rview.setAdapter(mAdapter);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.coinlore.net")
                .addConverterFactory(GsonConverterFactory.create()).build();

        CoinService service = retrofit.create(CoinService.class);

        Call<CoinLoreResponse> coinsCall = service.getAllCoins();
        Log.d(TAG, "finished response");


        coinsCall.enqueue(new Callback<CoinLoreResponse>() {

            @Override
            public void onResponse(Call<CoinLoreResponse> call, Response<CoinLoreResponse> response) {

                Log.d(TAG, "reached response");
                if(response.isSuccessful()){

                    coins = response.body().getData();
                    ((MyAdapter) mAdapter).setCoins(coins);


                } else {
                    Log.d(TAG, "onResponse: Error is "+ response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<CoinLoreResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ON FAILURE IS "+ t.getLocalizedMessage());
            }
        });





    }

    private void clickResponse(int position) {
        Log.d(TAG, "clickResponse: pressed " + position);

        Coin coin = coins.get(position);


        Log.d(TAG, "reachedInWide" );
        if(!inWide){
            Log.d(TAG, "notInWide");
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("crypto", new Gson().toJson(coin));
            startActivity(intent);
        } else {
            Log.d(TAG, "InWide");
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            Fragment fragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("crypto", coin);
            fragment.setArguments(bundle);
            transaction.replace(R.id.detailContainer, fragment);
            transaction.commit();
        }

    }



}

