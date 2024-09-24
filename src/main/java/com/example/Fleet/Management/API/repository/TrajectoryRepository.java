package com.example.Fleet.Management.API.repository;

import com.example.Fleet.Management.API.model.Trajectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TrajectoryRepository extends JpaRepository<Trajectory, Integer> {

    @Query("SELECT t FROM Trajectory t WHERE t.taxi.id = :taxiId AND DATE(t.date) = DATE(:date)")
    List<Trajectory> findByTaxiIdAndDate(Integer taxiId, LocalDate date);

}
