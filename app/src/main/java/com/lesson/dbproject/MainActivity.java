package com.lesson.dbproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Country> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.rv);
        list.add(new Country("Франция","Париж",65000000));
        CountryAdapter.OnCountryClickListener onCountryClickListener = new CountryAdapter.OnCountryClickListener() {
            @Override
            public void onCountryClick(Country country, int position) {
                Intent intent = new Intent(MainActivity.this, CountryActivity.class);
                intent.putExtra(Country.class.getSimpleName(), country);
                startActivity(intent);
            }
        };
        CountryAdapter adapter = new CountryAdapter(this, list, onCountryClickListener);
        recyclerView.setAdapter(adapter);
    }
}
