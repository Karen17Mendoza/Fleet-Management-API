package com.example.Fleet.Management.API.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trajectories")
public class Trajectory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taxi_id", referencedColumnName = "id", nullable = false)
    private Taxi taxi;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    // Constructor vacío para JPA
    protected Trajectory() {}

    // Constructor privado para el Builder
    private Trajectory(Builder builder) {
        this.id = builder.id;
        this.taxi = builder.taxi;
        this.date = builder.date;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
    }

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private Integer id;
        private Taxi taxi;
        private LocalDateTime date;
        private Double latitude;
        private Double longitude;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder taxi(Taxi taxi) {
            this.taxi = taxi;
            return this;
        }

        public Builder date(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Builder latitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder longitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Trajectory build() {
            return new Trajectory(this);
        }
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
