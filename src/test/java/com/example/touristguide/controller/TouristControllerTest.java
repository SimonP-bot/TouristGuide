package com.example.touristguide.controller;
import com.example.touristguide.model.TouristAttraction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.mockito.ArgumentMatchers.any;


import static org.hamcrest.MatcherAssert.assertThat;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.touristguide.service.TouristService;

import java.util.Arrays;
import java.util.List;


@WebMvcTest(TouristController.class) //WebMvcTest-annotation = mocker service-laget
class TouristGuideApplicationTests {



//    @Autowired
//    private MockMvc mockMvc; //Simulerer requests vha objekt
//
//    @MockitoBean
//    private TouristService touristService; //Mock'er @Service, så den hardcodet arrayList ikke er nødvendig
//
//    @BeforeEach
//    void setUp() {
//        Mockito.reset(touristService); //Nulstiller mock mellem hver test
//
//
//
//    }
//
//    @AfterEach()
//    void tearDown() {
//    }
//
//    @Test // Viser alle attraktioner
//    void testShowTouristAttractions() throws Exception {
//        when(touristService.getAllAttractions())
//                .thenReturn(List.of(new TouristAttraction("Eremitageslottet", "Jagtslot", "Klampenborg"),
//                        new TouristAttraction("Fredensborg Slot", "Slot", "Fredensborg")));
//
//        mockMvc.perform(get("/attractions"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("attractionList"))
//                .andExpect(model().attributeExists("attractions"));
//    }
//
//    @Test // Finder attraktion ud fra navn
//    void testGetAttractionByName() throws Exception {
//        TouristAttraction touristAttraction = new TouristAttraction("Eremitageslottet", "Jagtslot", "Klampenborg");
//
//        when(touristService.getAttractionByName("Eremitageslottet")).thenReturn(touristAttraction);
//
//        mockMvc.perform(get("/attractions/Eremitageslottet"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("index")) //Returnerer 'index' da dette ikke er løst i koden endnu
//                .andExpect(model().attributeExists("attraction"));
//    }
//
//    @Test // Viser tags for en attraktion
//    void testGetTags() throws Exception {
//        List<Tags> tags = Arrays.asList(Tags.CHILD_FRIENDLY, Tags.FOR_FREE);
//
//        when(touristService.getTags("Eremitageslottet")).thenReturn(tags);
//
//        mockMvc.perform(get("/attractions/Eremitageslottet/tags"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("tags"))
//                .andExpect(model().attributeExists("tags"));
//
//    }
//
//    @Test // Tilføjer attraktion til liste
//    void testAddAttraction() throws Exception {
//
//        //Arrange
//        List<String> cities = Arrays.asList("Klampenborg", "Lyngby", "Kalundborg");
//
//        when(touristService.getCities()).thenReturn(cities);
//
//
//        //Act & Assert
//        mockMvc.perform(get("/attractions/add")) //Forventer nedenstående
//                .andExpect(status().isOk()) // Status 200
//                .andExpect(view().name("newAttraction"))
//                .andExpect(model().attributeExists("attraction")) // Tom 'attraction'
//                .andExpect(model().attributeExists("cities")) // Cities tilføjet til model
//                .andExpect(model().attributeExists("tags")) // Tags tilføjet til model
//                .andExpect(model().attribute("cities", cities)) // Sikre at cities er mock-værdierne
//                .andExpect(model().attribute("tags", Tags.values())); // Sikre at tags er enum-værdierne
//    }
//
//    @Test
//    void testSaveAttraction() throws Exception {
//        //Arrange
//        TouristAttraction touristAttraction = new TouristAttraction("Eremitageslottet", "Jagtslot", "Klampenborg");
//        List<Tags> tags = Arrays.asList(Tags.FOR_FREE, Tags.CHILD_FRIENDLY);
//        touristAttraction.setTags(tags);
//
//        // Act & Assert
//        mockMvc.perform(post("/attractions/save")
//                        .flashAttr("attraction", touristAttraction) //@ModelAttribute binder sig til objekt vha flashAttr()
//                                                                    //Simulerer hvordan <form> sender data i Spring MVC
//                        .param("tags", "FOR_FREE", "CHILD_FRIENDLY")) //Tags sendes som requestParam
//                        .andExpect(status().is3xxRedirection()) //Forventer redirect
//                        .andExpect(redirectedUrl("/attractions")); //Selve omdirigeringen
//    }
//
//    @Test
//    void testEditAttraction() throws Exception {
//        TouristAttraction touristAttraction = new TouristAttraction("Eremitageslottet", "Jagtslot", "Klampenborg");
//        List<Tags> tags = Arrays.asList(Tags.FOR_FREE, Tags.CHILD_FRIENDLY);
//        touristAttraction.setTags(tags);
//
//        when(touristService.getAttractionByName("Eremitageslottet")).thenReturn(touristAttraction);
//        when(touristService.getCities()).thenReturn(Arrays.asList("Klampenborg", "Lyngby"));
//
//
//        mockMvc.perform(get("/attractions/{name}/edit", "Eremitageslottet"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("updateAttraction"))
//                .andExpect(model().attribute("attraction", touristAttraction))
//                .andExpect(model().attribute("attractionName", touristAttraction.getName()))
//                .andExpect(model().attribute("attractionDescription", touristAttraction.getDescription()))
//                .andExpect(model().attributeExists("tags"))
//                .andExpect(model().attribute("tags", arrayContainingInAnyOrder(Tags.values()))) //Sikrer at Tags.values()
//                                                                                    //returnerer samme værdier som modellen indeholder
//                .andExpect(model().attributeExists("cities"));
//
//        verify(touristService, times(1)).getAttractionByName("Eremitageslottet");
//        verify(touristService, times(1)).getCities();
//
//    }
//
//    @Test
//    void testUpdateAttraction() throws Exception {
//        TouristAttraction touristAttraction = new TouristAttraction("Eiffeltaarnet","Tårn midt i Paris. Blev lavet til verdensudstilling i Paris", "Paris");
//        List<Tags> tags = Arrays.asList(Tags.ART, Tags.CHILD_FRIENDLY, Tags.DISABILITY_FRIENDLY);
//        touristAttraction.setTags(tags);
//
//        when(touristService.getAttractionByName("Eiffeltaarnet")).thenReturn(touristAttraction);
//
//        String updatedDescription = "Opdateret";
//        TouristAttraction updatedTouristAttraction = new TouristAttraction("Eiffeltaarnet", updatedDescription, "Paris");
//        updatedTouristAttraction.setTags(Arrays.asList(Tags.CHILD_FRIENDLY, Tags.FOR_FREE));
//
//        //Mock'er updateAttraction() til at returnere den opdaterede attraktion
//        when(touristService.updateAttraction(any(TouristAttraction.class))).thenReturn(updatedTouristAttraction);
//
//        //Act - Simulerer post-request med opdaterede værdier
//        mockMvc.perform(post("/attractions/update")
//                        .flashAttr("attraction", touristAttraction) //@ModelAttribute binder sig til objekt vha flashAttr()
//                        //Simulerer hvordan <form> sender data i Spring MVC
//                        .param("tags", "FOR_FREE", "CHILD_FRIENDLY")) //Tags sendes som requestParam
//                .andExpect(status().is3xxRedirection()) //Forventer redirect
//                .andExpect(redirectedUrl("/attractions")); //Selve omdirigeringen
//
//        verify(touristService, times(1)).updateAttraction(any(TouristAttraction.class));
//
//    }
//
//    @Test
//    void testDeleteAttraction() throws Exception {
//        TouristAttraction touristAttraction = new TouristAttraction("Eiffeltaarnet", "Tårn midt i Paris. Blev lavet til verdensudstilling i Paris", "Paris");
//        List<Tags> tags = Arrays.asList(Tags.ART, Tags.CHILD_FRIENDLY, Tags.DISABILITY_FRIENDLY);
//        touristAttraction.setTags(tags);
//
//        when(touristService.deleteAttraction(touristAttraction.getName())).thenReturn(true);
//
//        mockMvc.perform(post("/attractions/delete/{name}", "Eiffeltaarnet"))
//                .andExpect(status().is3xxRedirection()) // Forventer en redirect
//                .andExpect(redirectedUrl("/attractions"));
//
//        verify(touristService, times(1)).deleteAttraction("Eiffeltaarnet");
//
//
//    }









}