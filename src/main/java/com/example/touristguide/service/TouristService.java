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
            return touristRepository.getAllAttractions();
        }

        public TouristAttraction getAttractionByName(String name) {
            return touristRepository.getAttractionByName(name);
        }

        public TouristAttraction addAttraction(TouristAttraction touristAttraction) {
            return touristRepository.addAttraction(touristAttraction);
        }

        public TouristAttraction updateAttraction(TouristAttraction updatedAttraction) {
            return touristRepository.updateAttraction(updatedAttraction);
        }

        public boolean deleteAttraction(String name) {
            return touristRepository.deleteAttraction(name);
        }

        public List<Tags> getTags(String name) {
            return touristRepository.getTags(name);
        }

        public List<String> getCities() {
            return touristRepository.myCities();
        }
}
