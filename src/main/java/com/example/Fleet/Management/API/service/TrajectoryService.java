package com.example.Fleet.Management.API.service;

import com.example.Fleet.Management.API.model.Trajectory;
import com.example.Fleet.Management.API.response.LatestTrajectoryResponse;
import com.example.Fleet.Management.API.response.TrajectoryResponse;
import com.example.Fleet.Management.API.repository.TrajectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrajectoryService {

    @Autowired
    private TrajectoryRepository trajectoryRepository;

    public List<TrajectoryResponse> getTrajectoriesByTaxiIdAndDate(Integer taxiId, LocalDate date) {
        // Definir el rango del día (00:00:00 - 23:59:59)
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        // Obtener una lista de Trajectory desde el repositorio usando el rango de tiempo
        List<Trajectory> trajectoryList = trajectoryRepository.findByTaxiIdAndDateBetween(taxiId, startOfDay, endOfDay);

        // Mapear la lista de Trajectory a una lista de TrajectoryResponse
        return trajectoryList.stream().map(traj ->

                new TrajectoryResponse(
                        traj.getId(),
                        traj.getTaxi().getPlate(),
                        traj.getTaxi().getId(),
                        traj.getDate(),
                        traj.getLatitude(),
                        traj.getLongitude()
                )
        ).collect(Collectors.toList());
    }
    public List<LatestTrajectoryResponse> getLatestTrajectories() {
        // Llamar al repositorio para obtener las trayectorias más recientes
        List<Trajectory> latestTrajectories = trajectoryRepository.findLatestTrajectoriesForAllTaxis();

        // Mapear los resultados a TrajectoryResponse
        return latestTrajectories.stream().map(traj -> new LatestTrajectoryResponse(

                traj.getTaxi().getId(),
                traj.getTaxi().getPlate(),
                traj.getDate(),
                traj.getLatitude(),
                traj.getLongitude()
        )).collect(Collectors.toList());
    }
}





