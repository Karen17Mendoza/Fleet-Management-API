package com.example.Fleet.Management.API.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Fleet.Management.API.service.TaxiService;
import com.example.Fleet.Management.API.model.Taxi;

import java.util.List;

@RestController
public class TaxiController {

    @Autowired
    private TaxiService taxiService;

    @GetMapping("/taxis")
    public ResponseEntity<List<Taxi>> listTaxis(
            @RequestParam(required = false) String plate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<Taxi> taxiPage = taxiService.getTaxis(plate, pageable);

        if (taxiPage == null) {
            // Manejar el caso donde taxiPage es null
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<Taxi> taxiList = taxiPage.getContent();
        return new ResponseEntity<>(taxiList, HttpStatus.OK);
    }
}
