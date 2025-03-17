package com.example.touristguide.model;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private int id;
    private String name;
    private String description;
    private City city;
    private List<Tag> tags;

    public TouristAttraction() {
    }

    public TouristAttraction(int id, String name, String description, City city) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.city = city;
    }

    // getters
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public City getCity() {
        return this.city;
    }
    public List<Tag> getTags() {
        return this.tags;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCity(City city) {
        this.city = city;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return this.name + ": " + this.description;
    }
}
