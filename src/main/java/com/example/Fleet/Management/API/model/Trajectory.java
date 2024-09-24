package com.example.Fleet.Management.API.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Table(name = "trajectories")
public class Trajectory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="taxi_id", nullable = false)
    private Taxi taxi;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Builder
    public Trajectory(Integer id, Taxi taxi, LocalDateTime date, Double latitude, Double longitude) {
        this.id = id;
        this.taxi = taxi;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }


}
