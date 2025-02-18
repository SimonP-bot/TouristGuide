package com.example.touristguide.model;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private List<Tags> tags;

    public TouristAttraction() {
    }

    public TouristAttraction(String name, String description) {
        this.name = name;
        this.description = description;
        tags = new ArrayList<>();
    }

    // getters
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.name + ": " + this.description;
    }
}
