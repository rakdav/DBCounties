package com.lesson.dbproject;

import java.io.Serializable;

public class Country implements Serializable
{
    private long ID;
    private String name;
    private String capital;
    private int population;

    public Country(String name, String capital, int population) {
        this.name = name;
        this.capital = capital;
        this.population = population;
    }

    public Country(long ID, String name, String capital, int population) {
        this.ID = ID;
        this.name = name;
        this.capital = capital;
        this.population = population;
    }

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}