package com.example.Fleet.Management.API.service;

import com.example.Fleet.Management.API.model.Trajectory;
import com.example.Fleet.Management.API.response.TrajectoryResponse;
import com.example.Fleet.Management.API.repository.TrajectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class TrajectoryService {

    @Autowired
    private TrajectoryRepository trajectoryRepository;

    public Page<TrajectoryResponse> getTrajectoriesByTaxiIdAndDate(Integer taxiId, LocalDate date, int page, int size) {
        // Definir el rango del día (00:00:00 - 23:59:59)
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        // Crear un Pageable para la paginación
        PageRequest pageable = PageRequest.of(page, size);

        // Obtener un Page de Trajectory desde el repositorio usando el rango de tiempo
        Page<Trajectory> trajectoryPage = trajectoryRepository.findByTaxiIdAndDateBetween(taxiId, startOfDay, endOfDay, pageable);

        // Mapear el Page de Trajectory a un Page de TrajectoryResponse
        return trajectoryPage.map(traj -> {
            String plate = traj.getTaxi().getPlate();
            Integer taxiIdFromEntity = traj.getTaxi().getId();

            return new TrajectoryResponse(

                    traj.getId(),
                    plate,
                    taxiIdFromEntity,
                    traj.getDate(),
                    traj.getLatitude(),
                    traj.getLongitude()

            );
        });
    }
}
