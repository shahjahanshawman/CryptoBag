package com.example.cryptobag;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.cryptobag.Entities.Coin;

public class DetailFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "DetailFragment";


    public DetailFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "inflating" );
        View v =inflater.inflate(R.layout.fragment_detail, container, false);

        Coin coin = (Coin) getArguments().getSerializable("crypto");



        TextView cryptoName = v.findViewById(R.id.nameofCrypto);
        cryptoName.setText(coin.getName());

        TextView cryptoAbbr = v.findViewById(R.id.abbr);
        cryptoAbbr.setText(coin.getSymbol());

        TextView value = v.findViewById(R.id.valueNum);
        value.setText(coin.getPriceUsd());

        TextView change1Num = v.findViewById(R.id.change1Num);
        change1Num.setText((coin.getPercentChange1h()));

        TextView change24Num = v.findViewById(R.id.change24Num);
        change24Num.setText(coin.getPercentChange24h());

        TextView change7Num = v.findViewById(R.id.change7Num);
        change7Num.setText(coin.getPercentChange7d());

        TextView marketNum = v.findViewById(R.id.marketNum);
        marketNum.setText(coin.getMarketCapUsd());

        TextView volume24Num = v.findViewById(R.id.volume24Num);
        volume24Num.setText(Double.toString(coin.getVolume24()));

        Button search = v.findViewById(R.id.search);
        search.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        String name = getArguments().getString("crypto");
        String url = "http://www.google.com/#q=" + name;
        intent.putExtra(SearchManager.QUERY, url);
        startActivity(intent);
    }
}
