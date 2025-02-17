package com.example.touristguide.controller;

import com.example.touristguide.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TouristController {
    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping("/attractions")

    public String getAllAttractions(Model model){

        return
    }

    @GetMapping("/attractions/{name}")

    @GetMapping("/attractions/{name}/tags")

    @GetMapping("/attractions/add")

    @PostMapping("/attractions/save")

    @GetMapping("/attractions/{name}/edit")

    @PostMapping("/attractions/update")

    @PostMapping("attractions/delete/{name}")
}
