package com.example.retrofitdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitdemo.model.CyrptoModel;
import com.example.retrofitdemo.service.CyrptoAPI;

import java.util.ArrayList;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.RowHolder> {
    @NonNull

    private ArrayList<CyrptoModel> cyrptoList;

    public RecylerViewAdapter(@NonNull ArrayList<CyrptoModel> cyrptoList) {
        this.cyrptoList = cyrptoList;
    }

    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        // row Layout tanımlama
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(cyrptoList.get(position));
    }

    @Override
    public int getItemCount() {
        return cyrptoList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {

        TextView currency;
        TextView price;
        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(CyrptoModel cyrptoModel){
            currency = itemView.findViewById(R.id.currency);
            price = itemView.findViewById(R.id.price);
            currency.setText(cyrptoModel.currency);
            price.setText(cyrptoModel.price);
        }
    }
}
