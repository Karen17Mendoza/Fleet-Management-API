package com.example.Fleet.Management.API.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Fleet.Management.API.model.Taxi;


@Repository
public interface TaxiRepository extends JpaRepository<Taxi, Integer> {
    Page<Taxi> findByPlateContaining(String plate, Pageable pageable);
}