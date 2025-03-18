package com.example.touristguide.model;

public class City {
    private int id;
    private String name;

    public City() {}

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void String(String name) {
        this.name = name;
    }
}
