package com.example.cryptobag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cryptobag.Entities.Coin;
import com.google.gson.Gson;
public class DetailActivity extends AppCompatActivity  {

    private static final String TAG = "DetailActivity";

    String name = "hi";
    Coin coin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String jsonMyObject;
        Intent i = getIntent();
        if(i.getExtras() != null) {
            Bundle extras = i.getExtras();
            jsonMyObject = extras.getString("crypto");
            coin = new Gson().fromJson(jsonMyObject, Coin.class);

        }

        Log.d(TAG, "DetailActivity");
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("crypto", coin);
        fragment.setArguments(bundle);
        transaction.replace(R.id.detailScroll, fragment);
        transaction.commit();




    }




}
