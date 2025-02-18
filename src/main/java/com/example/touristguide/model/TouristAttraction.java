package com.example.touristguide.model;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private List<Tags> tags;
    private String city;


    public TouristAttraction() {
    }

    public TouristAttraction(String name, String description, String city) {
        setName(name);
        setDescription(description);
        setCity(city);
        tags = new ArrayList<>();
    }

    // getters
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public String getCity() {
        return this.city;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return this.name + ": " + this.description;
    }
}
