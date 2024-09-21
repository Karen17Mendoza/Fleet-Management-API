package com.example.Fleet.Management.API.controller;

import com.example.Fleet.Management.API.model.Taxi;
import com.example.Fleet.Management.API.service.TaxiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(TaxiController.class)

class TaxiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaxiService taxiService;
    private List<Taxi> taxiList;

    @BeforeEach
    public void setUp() {

        Taxi taxi1 = new Taxi();
        taxi1.setId(1);
        taxi1.setPlate("ABC-123");

        Taxi taxi2 = new Taxi();
        taxi2.setId(2);
        taxi2.setPlate("XYZ-456");

        taxiList = Arrays.asList(taxi1, taxi2);
    }

    @DisplayName("Testing - should return a list of taxis filtered by plate")
    @Test
    public void testListTaxisWithPlate() throws Exception {

        Taxi filteredTaxi = new Taxi();
        filteredTaxi.setId(3);
        filteredTaxi.setPlate("ABC-789");

        Page<Taxi> taxiPage = new PageImpl<>(Arrays.asList(filteredTaxi));
        Mockito.when(taxiService.getTaxis(Mockito.eq("ABC"), any(Pageable.class)))
                .thenReturn(taxiPage);

        mockMvc.perform(get("/taxis")
                        .param("plate", "ABC")
                        .param("page", "0")
                        .param("size", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':3,'plate':'ABC-789'}]"));
    }

    @DisplayName("Testing - should filter that does not find matches")
    @Test
    public void testListTaxisWithNoResults() throws Exception {
        Page<Taxi> emptyTaxiPage = new PageImpl<>(Arrays.asList());
        Mockito.when(taxiService.getTaxis(Mockito.eq("ZZZ"), any(Pageable.class)))
                .thenReturn(emptyTaxiPage);

        mockMvc.perform(get("/taxis")
                        .param("plate", "ZZZ")
                        .param("page", "0")
                        .param("size", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}