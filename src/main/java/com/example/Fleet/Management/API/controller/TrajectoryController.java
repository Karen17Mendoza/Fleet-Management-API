package com.example.Fleet.Management.API.controller;

import com.example.Fleet.Management.API.response.LatestTrajectoryResponse;
import com.example.Fleet.Management.API.response.TrajectoryResponse;
import com.example.Fleet.Management.API.service.TrajectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TrajectoryController {

    @Autowired
    private TrajectoryService trajectoryService;

    @GetMapping("/trajectories")
    public ResponseEntity<List<TrajectoryResponse>> listTrajectoriesByTaxiIdAndDate(
            @RequestParam Integer taxiId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date)
            {

        // Llamamos al servicio para obtener las trayectorias filtradas por taxiId y fecha
        List<TrajectoryResponse> trajectoryResponses = trajectoryService.getTrajectoriesByTaxiIdAndDate(taxiId, date);

        if (trajectoryResponses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(trajectoryResponses, HttpStatus.OK);
    }
    // Endpoint para obtener la última ubicación de cada taxi
    @GetMapping("/trajectories/latest")
    public ResponseEntity<List<LatestTrajectoryResponse>> getLatestTrajectories() {
        List<LatestTrajectoryResponse> latestTrajectories = trajectoryService.getLatestTrajectories();

        if (latestTrajectories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(latestTrajectories, HttpStatus.OK);
    }

}
