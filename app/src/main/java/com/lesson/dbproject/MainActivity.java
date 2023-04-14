package com.lesson.dbproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Country> list = new ArrayList<>();
    private DBCountry db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.rv);
        db=new DBCountry(this);
        db.Insert(1,"Germany","Berlin",3000000);

        CountryAdapter.OnCountryClickListener onCountryClickListener = new CountryAdapter.OnCountryClickListener() {
            @Override
            public void onCountryClick(Country country, int position) {
                Intent intent = new Intent(MainActivity.this, CountryActivity.class);
                intent.putExtra(Country.class.getSimpleName(), country);
                startActivity(intent);
            }
        };
        CountryAdapter adapter = new CountryAdapter(this, db.selectAll(), onCountryClickListener);
        recyclerView.setAdapter(adapter);
    }
}
