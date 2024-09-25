package com.example.Fleet.Management.API.repository;

import com.example.Fleet.Management.API.model.Trajectory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrajectoryRepository extends JpaRepository<Trajectory, Integer> {

    List<Trajectory> findByTaxiIdAndDateBetween(Integer taxiId, LocalDateTime startOfDay, LocalDateTime endOfDay);

    // Consulta personalizada para obtener la última ubicación de cada taxi
    @Query("SELECT t FROM Trajectory t WHERE t.date = (SELECT MAX(t2.date) FROM Trajectory t2 WHERE t2.taxi.id = t.taxi.id)")
    List<Trajectory> findLatestTrajectoriesForAllTaxis();

}
