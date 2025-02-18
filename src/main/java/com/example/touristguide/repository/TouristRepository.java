package com.example.touristguide.repository;

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
        myTouristAttrations();
    }

    public void addTouristAttractions(String name, String description, String city) {
        this.touristAttractions.add(new TouristAttraction(name,description, city));
    }

    public void myTouristAttrations() {
        addTouristAttractions("Eiffeltaarnet","Tårn midt i Paris. Blev lavet til verdensudstilling i Paris", "Paris");
        addTouristAttractions("Den lille havfrue","Skabt af billedhuggeren Edvard Eriksen i 1913", "København");
        addTouristAttractions("Rosenborg Slot","Kom og se de kongelige smykker", "København");
        addTouristAttractions("Rundetaarn","Udsigtstårn over København", "København");
    }

    public List<TouristAttraction> getAllAttrations() {
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

    public TouristAttraction updateAttraction(String name, TouristAttraction updatedAttraction) {
        for (TouristAttraction attraction : touristAttractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                if (updatedAttraction.getDescription() != null) {
                    attraction.setDescription(updatedAttraction.getDescription());
                }
                if (updatedAttraction.getName() != null) {
                    attraction.setName(updatedAttraction.getName());
                }
                return attraction;
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
