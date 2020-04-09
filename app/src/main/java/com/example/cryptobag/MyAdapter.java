package com.example.cryptobag;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.getColor;
import static androidx.core.content.ContextCompat.startActivity;
import com.example.cryptobag.Entities.Coin;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private static final String TAG = "MyAdapter";
    private List<Coin> coins;
    RecyclerViewClickListener listener;


//    public interface RecyclerViewClickListener {
//
//        void onClick(View view, int position);
//    }



    public MyAdapter(List<Coin> myDataSet, RecyclerViewClickListener listener) {

        coins = myDataSet;
        this.listener = listener;
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView cryptoName, change1h, value;
        ImageView image;
        RecyclerViewClickListener listener;

        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.listener = listener;
            cryptoName = itemView.findViewById(R.id.cryptoName);
            value = itemView.findViewById(R.id.value);
            change1h = itemView.findViewById(R.id.change1h);
            image = itemView.findViewById(R.id.image);


        }


        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: inside myadapter on click");
            listener.onClick(v,getAdapterPosition());
        }
    }


    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new MyViewHolder(v, listener);
     }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Coin coin = coins.get(position);
        String value= coin.getPriceUsd();
        String change= coin.getPercentChange24h();
        final String name = coin.getSymbol();
        holder.cryptoName.setText(coin.getName());
        holder.value.setText(value);
        holder.change1h.setText(change+"%");
        holder.image.setImageResource(R.mipmap.ic_launcher_round);
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    public void setCoins(List<Coin> newCoin) {
        coins.clear();
        coins.addAll(newCoin) ;
        notifyDataSetChanged();
    }
}
