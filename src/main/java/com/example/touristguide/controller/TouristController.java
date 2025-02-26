package com.example.touristguide.controller;

import com.example.touristguide.model.Tags;
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

    @GetMapping("/attractions/add") //Bruges til at vise html-side - ikke sende data
    public String addAttraction(Model model) {
        model.addAttribute("attraction", new TouristAttraction()); //Opretter tom attraktion
        model.addAttribute("cities", touristService.getCities()); //Henter liste over byer
        model.addAttribute("tags", Tags.values()); //Henter tags-værdier
        return "newAttraction"; //Viser html <form>
    }

    @PostMapping("/attractions/save")
    public String saveAttraction(@ModelAttribute TouristAttraction attraction, @RequestParam(required = false) List<Tags> tags) {
        if (tags != null) {
            attraction.setTags(tags);
        } else {
            attraction.setTags(new ArrayList<>()); //Ingen tags = tom liste (undgår NullPointerException)
        }
        touristService.addAttraction(attraction);
        return "redirect:/attractions"; // Omdirigerer til listen over attraktioner
    }

    @GetMapping("/attractions/{name}/edit")
    public String editAttraction(Model model, @PathVariable String name) {
        TouristAttraction touristAttraction = touristService.getAttractionByName(name);
        if(touristAttraction == null) {
            throw new IllegalArgumentException("Ugyldigt attraktion");
        }
        model.addAttribute("attraction",touristAttraction);
        model.addAttribute("attractionName", touristAttraction.getName());
        model.addAttribute("attractionDescription",touristAttraction.getDescription());
        model.addAttribute("tags", Tags.values());
        model.addAttribute("cities",touristService.getCities());
        return "updateAttraction";
    }

    @PostMapping("/attractions/update")
    public String updateAttraction(@ModelAttribute TouristAttraction attraction) {
        touristService.updateAttraction(attraction);
        return "redirect:/attractions";
    }

    @PostMapping("/attractions/delete/{name}")
    public String deleteAttraction(Model model, @PathVariable String name) {
        boolean deletedAttraction = touristService.deleteAttraction(name);
        model.addAttribute("attraction", deletedAttraction);
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
