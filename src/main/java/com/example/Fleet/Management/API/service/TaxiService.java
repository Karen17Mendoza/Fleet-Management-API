package com.example.Fleet.Management.API.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.Fleet.Management.API.repository.TaxiRepository;
import com.example.Fleet.Management.API.model.Taxi;

@Service
public class TaxiService {

    @Autowired
    private TaxiRepository taxiRepository;

    public Page<Taxi> getTaxis(String plate, Pageable pageable) {
        if (plate != null && !plate.isEmpty()) {
            //Filtramos por placa cunado lo requieraan
            return taxiRepository.findByPlateContaining(plate, pageable);
        } else {
            // Retornamos todos los taxis
            return taxiRepository.findAll(pageable);
        }
    }
}