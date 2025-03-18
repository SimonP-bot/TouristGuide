package com.example.touristguide.dto;

import java.util.List;

public class TouristAttractionDTO {
    private int id;
    private String name;
    private String description;
    private int cityId;
    private List<Integer> tagIds;

    public TouristAttractionDTO() {
    }

    public TouristAttractionDTO(int id, String name, String description, int cityId, List<Integer> tagIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cityId = cityId;
        this.tagIds = tagIds;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCityId() {
        return cityId;
    }

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds;
    }
}
