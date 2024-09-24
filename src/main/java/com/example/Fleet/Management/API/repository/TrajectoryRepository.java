package com.example.Fleet.Management.API.repository;

import com.example.Fleet.Management.API.model.Trajectory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

@Repository
public interface TrajectoryRepository extends JpaRepository<Trajectory, Integer> {

    Page<Trajectory> findByTaxiIdAndDateBetween(Integer taxiId, LocalDateTime startOfDay, LocalDateTime endOfDay, Pageable pageable);
}
