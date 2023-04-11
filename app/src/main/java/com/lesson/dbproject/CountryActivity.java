package com.lesson.dbproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class CountryActivity extends AppCompatActivity {
    private Country country;
    private TextView nameCountry,capitalCountry,populationCountry;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        nameCountry=findViewById(R.id.nameCountry);
        capitalCountry=findViewById(R.id.capitalCountry);
        populationCountry=findViewById(R.id.populationCountry);
        Bundle arguments=getIntent().getExtras();
        if(arguments!=null)
        {
            country=(Country)arguments.getSerializable(Country.class.getSimpleName());
            nameCountry.setText(country.getName());
            capitalCountry.setText(country.getCapital());
            populationCountry.setText(Integer.toString(country.getPopulation()));
        }
    }
}