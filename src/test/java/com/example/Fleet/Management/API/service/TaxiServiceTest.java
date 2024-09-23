package com.example.Fleet.Management.API.service;

import com.example.Fleet.Management.API.model.Taxi;
import com.example.Fleet.Management.API.repository.TaxiRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaxiServiceTest {

    @Mock
    private TaxiRepository taxiRepository;

    @InjectMocks
    private TaxiService taxiService;

    private List<Taxi> taxiList;

    @BeforeEach
    void setUp() {
        // Prepara una lista de taxis simulados
        Taxi taxi1 = new Taxi();
        taxi1.setId(1);
        taxi1.setPlate("ABC-123");

        Taxi taxi2 = new Taxi();
        taxi2.setId(2);
        taxi2.setPlate("XYZ-456");

        taxiList = Arrays.asList(taxi1, taxi2);
    }

    @Test
    void testGetTaxisWithoutFilter() {

        Page<Taxi> taxiPage = new PageImpl<>(taxiList);
        when(taxiRepository.findAll(any(Pageable.class))).thenReturn(taxiPage);

        Page<Taxi> result = taxiService.getTaxis(null, Pageable.unpaged());

        assertEquals(2, result.getTotalElements());
        assertEquals("ABC-123", result.getContent().get(0).getPlate());
        assertEquals("XYZ-456", result.getContent().get(1).getPlate());
    }

    @Test
    void testGetTaxisWithFilter() {

        Taxi filteredTaxi = new Taxi();
        filteredTaxi.setId(3);
        filteredTaxi.setPlate("ABC-789");

        Page<Taxi> taxiPage = new PageImpl<>(Arrays.asList(filteredTaxi));
        when(taxiRepository.findByPlateContaining(Mockito.eq("ABC"), any(Pageable.class))).thenReturn(taxiPage);

        Page<Taxi> result = taxiService.getTaxis("ABC", Pageable.unpaged());

        assertEquals(1, result.getTotalElements());
        assertEquals("ABC-789", result.getContent().get(0).getPlate());
    }
}