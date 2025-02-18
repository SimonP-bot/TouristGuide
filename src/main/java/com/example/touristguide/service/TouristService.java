package com.example.touristguide.service;

import com.example.touristguide.model.Tags;
import com.example.touristguide.model.TouristAttraction;
import com.example.touristguide.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
    private final TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public List<TouristAttraction> getAllAttractions() {
        return touristRepository.getAllAttrations();
    }

    public TouristAttraction getAttractionByName(String name) {
        return touristRepository.getAttractionByName(name);
    }

    public TouristAttraction addAttraction(TouristAttraction touristAttraction) {
        return touristRepository.addAttraction(touristAttraction);
    }

    public TouristAttraction updateAttraction(String name, TouristAttraction updatedAttraction) {
        return touristRepository.updateAttraction(name, updatedAttraction);
    }

    public boolean deleteAttraction(String name) {
        return touristRepository.deleteAttraction(name);
    }

    public List<Tags> getTags(String attractionName) {
        return touristRepository.getTags(attractionName);
    }

    public List<String> getCities() {
        return touristRepository.myCities();
    }
}
