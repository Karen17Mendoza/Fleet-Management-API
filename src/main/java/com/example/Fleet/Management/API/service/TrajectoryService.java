package com.example.Fleet.Management.API.service;

import com.example.Fleet.Management.API.model.Trajectory;
import com.example.Fleet.Management.API.repository.TrajectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TrajectoryService {

    @Autowired
    private TrajectoryRepository trajectoryRepository;

    public List<Trajectory> getTrajectoriesByTaxiIdAndDate(Integer taxiId, LocalDate date) {
        return trajectoryRepository.findByTaxiIdAndDate(taxiId,date);
    }
}
