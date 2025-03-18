package com.example.touristguide.service;

import com.example.touristguide.model.City;
import com.example.touristguide.model.Tag;
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

    public void addAttraction(TouristAttraction attraction) {
            touristRepository.addAttraction(attraction);

    }

        public void updateAttraction(TouristAttraction attraction) {
            touristRepository.updateAttraction(attraction);
        }

        public boolean deleteAttraction(int attractionId) {
            return touristRepository.deleteAttraction(attractionId);
        }

        public List<City> getCities() {
            return touristRepository.getCities();
        }

        public Tag findTagById(int id){
            return touristRepository.findTagById(id);
        }


        public City getCityById(int id) {
            return touristRepository.getCityById(id);
        }

        public List<Tag> getAllTags() {
            return touristRepository.getAllTags();
        }
        public List<Tag> getTagsForAttraction(int attractionId) {
            return touristRepository.getTagsForAttraction(attractionId);
        }

}
