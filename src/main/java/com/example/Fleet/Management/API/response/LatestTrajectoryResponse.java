package com.example.Fleet.Management.API.response;

import java.time.LocalDateTime;

public class LatestTrajectoryResponse {
    private Integer taxiId;
    private String plate;
    private LocalDateTime date;
    private Double latitude;
    private Double longitude;

    public LatestTrajectoryResponse(Integer taxiId, String plate, LocalDateTime date, Double latitude, Double longitude) {
        this.taxiId = taxiId;
        this.plate = plate;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters y Setters
    public Integer getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(Integer taxiId) {
        this.taxiId = taxiId;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}

