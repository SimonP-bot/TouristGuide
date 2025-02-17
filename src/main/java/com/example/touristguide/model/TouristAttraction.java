package com.example.touristguide.model;

public class TouristAttraction {
    private String name;
    private String description;

    public TouristAttraction() {
    }

    public TouristAttraction(String name, String description) {
        this.name = name;
        this.description = description;
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
