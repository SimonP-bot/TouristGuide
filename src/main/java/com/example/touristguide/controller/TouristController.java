package com.example.touristguide.controller;

import com.example.touristguide.model.Tags;
import com.example.touristguide.model.TouristAttraction;
import com.example.touristguide.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @GetMapping("/attractions/{name}")
    public String getAttractionByName(Model model, @PathVariable String name) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        model.addAttribute("attraction",attraction);
        return "index";
    }

    @GetMapping("/attractions/{name}/tags")
    public String getTags(Model model, @PathVariable String name) {
        List<Tags> tags = touristService.getTags(name);
        model.addAttribute("tags",tags);
        return "tags";
    }

    @GetMapping("/attractions/add")
    public String addAttraction(Model model, @RequestBody TouristAttraction newAttraction) {
        model.addAttribute("attraction", touristService.addAttraction(newAttraction));
        return "index";
    }


    //@PostMapping("/attractions/save")

    //@GetMapping("/attractions/{name}/edit")


    //@PostMapping("/attractions/update")

    @PostMapping("/attractions/delete/{name}")
    public String deleteAttraction(Model model, @PathVariable String name) {
        boolean deletedAttraction = touristService.deleteAttraction(name);
        model.addAttribute("attraction", deletedAttraction);
        return "index";
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
