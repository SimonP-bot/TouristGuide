package com.example.touristguide.controller;

import com.example.touristguide.dto.TouristAttractionDTO;
import com.example.touristguide.model.City;
import com.example.touristguide.model.Tag;
import com.example.touristguide.model.TouristAttraction;
import com.example.touristguide.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TouristController {
    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping("/attractions")
    public String getAllAttractions(Model model){
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "attractionList";
    }

    // TO DO
    @GetMapping("/attractions/{name}")
    public String getAttractionByName(Model model, @PathVariable String name) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        model.addAttribute("attraction",attraction);
        return "index";
    }

    @GetMapping("/attractions/{id}/tags")
    public String getTags(Model model, @PathVariable int id) {
        model.addAttribute("tags",touristService.getTagsForAttraction(id));
        return "tags";
    }

    @GetMapping("/attractions/add") //Bruges til at vise html-side - ikke sende data
    public String addAttraction(Model model) {
        model.addAttribute("attraction", new TouristAttractionDTO());
        model.addAttribute("cities", touristService.getCities());
        model.addAttribute("tags", touristService.getAllTags());
        return "newAttraction";
    }

    @PostMapping("/attractions/save")
    public String saveAttraction(@ModelAttribute TouristAttractionDTO attractionDTO) {
        TouristAttraction touristAttraction = new TouristAttraction();
        touristAttraction.setName(attractionDTO.getName());
        touristAttraction.setDescription(attractionDTO.getDescription());
        touristAttraction.setId(attractionDTO.getId());
        City city = touristService.getCityById(attractionDTO.getCityId());
        touristAttraction.setCity(city);

        List<Tag> tagList = new ArrayList<>();
if (attractionDTO.getTagIds()!=null){
    for (int tag : attractionDTO.getTagIds()){
        tagList.add(touristService.findTagById(tag));
    }
}


        touristAttraction.setTags(tagList);


        touristService.addAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @GetMapping("/attractions/{id}/edit")
    public String editAttraction(Model model, @PathVariable int id) {
        TouristAttraction attraction = touristService.getAttractionByName(String.valueOf(id));
        if (attraction == null) {
            throw new IllegalArgumentException("Ugyldig attraktion");
        }
        model.addAttribute("attraction", attraction);
        model.addAttribute("cities", touristService.getCities());
        model.addAttribute("tags",touristService.getAllTags());
        return "updateAttraction";
    }

    @PostMapping("/attractions/update")
    public String updateAttraction(@ModelAttribute TouristAttractionDTO attractionDTO) {
        TouristAttraction touristAttraction = new TouristAttraction();
        touristAttraction.setName(attractionDTO.getName());
        touristAttraction.setDescription(attractionDTO.getDescription());
        touristAttraction.setId(attractionDTO.getId());
        touristAttraction.setCity(touristService.getCityById(attractionDTO.getCityId()));

        List<Tag> tagList = new ArrayList<>();

        for (int tag : attractionDTO.getTagIds()){
            tagList.add(touristService.findTagById(tag));
        }

        touristAttraction.setTags(tagList);
        touristService.updateAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @PostMapping("/attractions/delete/{id}")
    public String deleteAttraction(@PathVariable int id) {
        touristService.deleteAttraction(id);
        return "redirect:/attractions";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
