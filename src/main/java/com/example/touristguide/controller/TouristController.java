package com.example.touristguide.controller;

import com.example.touristguide.model.TouristAttraction;
import com.example.touristguide.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "index";
    }

    @GetMapping("/attractions/{name}")
    public String getAttractionByName(Model model, @PathVariable String attractionName) {
        TouristAttraction attraction = touristService.getAttractionByName(attractionName);
        model.addAttribute("attraction",attraction);
        return "index";
    }

    @GetMapping("/attractions/{name}/tags")

    @GetMapping("/attractions/add")

    @PostMapping("/attractions/save")

    @GetMapping("/attractions/{name}/edit")

    @PostMapping("/attractions/update")

    @PostMapping("attractions/delete/{name}")
}
