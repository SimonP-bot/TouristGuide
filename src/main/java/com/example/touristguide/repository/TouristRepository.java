package com.example.touristguide.repository;

import com.example.touristguide.model.Tags;
import com.example.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> touristAttractions;

    public TouristRepository() {
        this.touristAttractions = new ArrayList<>();
        myTouristAttractions();
    }

    public void addTouristAttractions(String name, String description, String city, List<Tags> tags) {
        TouristAttraction attraction = new TouristAttraction(name, description, city);
        attraction.setTags(tags);
        this.touristAttractions.add(attraction);
    }

    public void myTouristAttractions() {
        addTouristAttractions("Eiffeltaarnet","Tårn midt i Paris. Blev lavet til verdensudstilling i Paris", "Pari", List.of(Tags.ART, Tags.CHILD_FRIENDLY, Tags.DISABILITY_FRIENDLY));
        addTouristAttractions("Den lille havfrue","Skabt af billedhuggeren Edvard Eriksen i 1913", "København", List.of(Tags.FOR_FREE, Tags.ART, Tags.CHILD_FRIENDLY, Tags.DISABILITY_FRIENDLY));
        addTouristAttractions("Rosenborg Slot","Kom og se de kongelige smykker", "København", List.of(Tags.ART, Tags.MUSEUM, Tags.CHILD_FRIENDLY));
        addTouristAttractions("Rundetaarn","Udsigtstårn over København", "København", List.of(Tags.CHILD_FRIENDLY));
    }

    public List<TouristAttraction> getAllAttractions() {
        return touristAttractions;
    }

    public TouristAttraction getAttractionByName(String name) {
        Iterator<TouristAttraction> iterator = touristAttractions.iterator();
        while(iterator.hasNext()) {
            TouristAttraction item = iterator.next();
            if(item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public TouristAttraction addAttraction(TouristAttraction touristAttraction) {
        touristAttractions.add(touristAttraction);
        return touristAttraction;
    }

    public TouristAttraction updateAttraction(TouristAttraction updatedAttraction) {
        for (TouristAttraction attraction : touristAttractions) {
            if(attraction.getName().equalsIgnoreCase(updatedAttraction.getName())) {
                attraction.setDescription(updatedAttraction.getDescription());
                attraction.setCity(updatedAttraction.getCity());
                attraction.setTags(updatedAttraction.getTags());
                return updatedAttraction;
            }
        }
        return null;
    }

    public boolean deleteAttraction(String name) {
        Iterator<TouristAttraction> iterator = touristAttractions.iterator();
        while (iterator.hasNext()) {
            TouristAttraction attraction = iterator.next();
            if (attraction.getName().equalsIgnoreCase(name)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<Tags> getTags(String attractionName) {
        for(TouristAttraction i : touristAttractions) {
            if (attractionName.equalsIgnoreCase(i.getName())) {
                return i.getTags();
            }
        }
        return null;
    }

    public List<String> myCities() {
        List<String> cities = new ArrayList<>();
        cities.add("København");
        cities.add("Aarhus");
        cities.add("Odense");
        cities.add("Aalborg");
        cities.add("Esbjerg");
        cities.add("Randers");
        cities.add("Kolding");
        cities.add("Horsens");
        cities.add("Vejle");
        cities.add("Roskilde");
        cities.add("Herning");
        cities.add("Hørsholm");
        cities.add("Helsingør");
        cities.add("Silkeborg");
        cities.add("Næstved");
        cities.add("Fredericia");
        cities.add("Ballerup");
        cities.add("Viborg");
        cities.add("Køge");
        cities.add("Holstebro");

        return cities;


    }


}
