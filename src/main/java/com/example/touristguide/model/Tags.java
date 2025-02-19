package com.example.touristguide.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Tags {
    CHILD_FRIENDLY("Børnevenlig"),
    NATURE("Natur"),
    FOR_FREE("Gratis"),
    MUSEUM("Museum"),
    ART("Kunst"),
    DISABILITY_FRIENDLY("Handikapvenligt");

    private final String displayName;

    Tags(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

}
