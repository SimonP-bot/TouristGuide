package com.example.touristguide.controller;
import com.example.touristguide.model.Tags;
import com.example.touristguide.model.TouristAttraction;
import com.example.touristguide.service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.FlashAttributeResultMatchers;

import java.awt.*;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(TouristController.class)
class TouristGuideApplicationTests {



    @Autowired
    private MockMvc mockMvc; //Simulerer requests vha objekt til simuleringen

    @MockitoBean
    private TouristService touristService; //Bean-objekt i stedet for objekt af @Service

    @BeforeEach
    void setUp() {



    }

    @AfterEach()
    void tearDown() {
    }

    @Test
    void showTouristAttractions() throws Exception {
        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractionList"));
    }
    /*

    @Test
    void updateAttractionTest() throws Exception {
        TouristAttraction attraction = new TouristAttraction("Pyramiderne", "sten", "Odense");
                attraction.setTags(java.util.List.of(Tags.CHILD_FRIENDLY));

                when(touristService.updateAttraction(attraction)).thenReturn(attraction);
        mockMvc.perform(post("/attractions/update")
                .queryParam("tags", "CHILD_FRIENDLY")

    }

    @Test
    void addAttractionTest() throws Exception {
        TouristAttraction attraction = new TouristAttraction("Pyramiderne", "sten", "Odense");
        attraction.setTags(java.util.List.of(Tags.CHILD_FRIENDLY, Tags.ART));
        mockMvc.perform(get("/attractions/add"))
                .andExpect(status().isOk()).andExpect(view().n)
    }
    */




}