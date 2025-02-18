package com.example.touristguide.model;

public enum Tags {
    BØRNEVENLIG("Børnevenlig"),
    NATUR("Natur"),
    GRATIS("Gratis"),
    MUSEUM("Museum"),
    KUNST("Kunst"),
    HANDIKAPVENLIGT("Handikapvenligt");

    private String displayName;

    Tags(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
