package com.lesson.dbproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    interface OnCountryClickListener{
        void onCountryClick(Country country,int position);
    }
    private final OnCountryClickListener onCountryClickListener;
    private final LayoutInflater inflater;
    private final List<Country> countries;

    public CountryAdapter(Context context, List<Country> countries,OnCountryClickListener onCountryClickListener) {
        this.inflater = LayoutInflater.from(context);
        this.countries = countries;
        this.onCountryClickListener=onCountryClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.country_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Country country=countries.get(position);
        holder.nameView.setText(country.getName());
        holder.capitalView.setText(country.getCapital());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onCountryClickListener.onCountryClick(country,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView nameView;
        final TextView capitalView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView=itemView.findViewById(R.id.name);
            capitalView=itemView.findViewById(R.id.capital);
        }
    }
}