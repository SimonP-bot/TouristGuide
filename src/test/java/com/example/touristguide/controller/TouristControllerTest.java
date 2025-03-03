package com.example.touristguide.controller;
import com.example.touristguide.model.Tags;
import com.example.touristguide.model.TouristAttraction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.touristguide.service.TouristService;

import java.util.Arrays;
import java.util.List;


@WebMvcTest(TouristController.class) //WebMvcTest-annotation = mocker service-laget
class TouristGuideApplicationTests {



    @Autowired
    private MockMvc mockMvc; //Simulerer requests vha objekt

    @MockitoBean
    private TouristService touristService; //Mock'er @Service, så den hardcodet arrayList ikke er nødvendig

    @BeforeEach
    void setUp() {
        Mockito.reset(touristService); //Nulstiller mock mellem hver test



    }

    @AfterEach()
    void tearDown() {
    }

    @Test //Viser alle attraktioner
    void testShowTouristAttractions() throws Exception {
        when(touristService.getAllAttractions())
                .thenReturn(List.of(new TouristAttraction("Eremitageslottet", "Jagtslot", "Klampenborg"),
                        new TouristAttraction("Fredensborg Slot", "Slot", "Fredensborg")));

        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractionList"))
                .andExpect(model().attributeExists("attractions"));
    }

    @Test //Finder attraktion ud fra navn
    void testGetAttractionByName() throws Exception {
        TouristAttraction touristAttraction = new TouristAttraction("Eremitageslottet", "Jagtslot", "Klampenborg");

        when(touristService.getAttractionByName("Eremitageslottet")).thenReturn(touristAttraction);

        mockMvc.perform(get("/attractions/Eremitageslottet"))
                .andExpect(status().isOk())
                .andExpect(view().name("index")) //Returnerer 'index' da dette ikke er løst i koden endnu
                .andExpect(model().attributeExists("attraction"));
    }

    @Test //Viser tags for en attraktion
    void testGetTags() throws Exception {
        List<Tags> tags = Arrays.asList(Tags.CHILD_FRIENDLY, Tags.FOR_FREE);

        when(touristService.getTags("Eremitageslottet")).thenReturn(tags);

        mockMvc.perform(get("/attractions/Eremitageslottet/tags"))
                .andExpect(status().isOk())
                .andExpect(view().name("tags"))
                .andExpect(model().attributeExists("tags"));

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