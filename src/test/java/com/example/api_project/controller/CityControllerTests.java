package com.example.api_project.controller;

import com.example.api_project.dto.CityDTO;
import com.example.api_project.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CityControllerTests {

    @Mock
    private CityService cityService;

    @InjectMocks
    private CityController cityController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(cityController).build();
    }

    @Test
    public void testGetAllCities() throws Exception {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(1L);
        cityDTO.setItalianForeignName("Test City");

        when(cityService.getAllCities()).thenReturn(Collections.singletonList(cityDTO));

        mockMvc.perform(get("/city/cities"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(cityDTO.getId()))
                .andExpect(jsonPath("$[0].italianForeignName").value(cityDTO.getItalianForeignName()));
    }

    @Test
    public void testGetCityById() throws Exception {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(1L);
        cityDTO.setItalianForeignName("Test City");

        when(cityService.getCityById(1L)).thenReturn(Optional.of(cityDTO));

        mockMvc.perform(get("/city/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(cityDTO.getId()))
                .andExpect(jsonPath("$.italianForeignName").value(cityDTO.getItalianForeignName()));

        when(cityService.getCityById(2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/city/{id}", 2L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetCityByIdBadRequest() throws Exception {
        mockMvc.perform(get("/city/{id}", 0L))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetCityByIdInternalServerError() throws Exception {
        doThrow(new RuntimeException()).when(cityService).getCityById(anyLong());

        mockMvc.perform(get("/city/{id}", 1L))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGetAllCitiesNoContent() throws Exception {
        when(cityService.getAllCities()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/city/cities"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllCitiesInternalServerError() throws Exception {
        when(cityService.getAllCities()).thenThrow(new RuntimeException());

        mockMvc.perform(get("/city/cities"))
                .andExpect(status().isInternalServerError());
    }
}