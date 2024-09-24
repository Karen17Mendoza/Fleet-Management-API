package com.example.Fleet.Management.API.response;

import java.time.LocalDateTime;

public class TrajectoryResponse {
    private Integer id;
    private String plate;
    private Integer taxiId;
    private LocalDateTime date;
    private Double latitude;
    private Double longitude;

    public TrajectoryResponse(Integer id, String plate, Integer taxiId, LocalDateTime date, Double latitude, Double longitude) {

        this.id = id;
        this.taxiId = taxiId;
        this.plate = plate;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Integer getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(Integer taxiId) {
        this.taxiId = taxiId;
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
