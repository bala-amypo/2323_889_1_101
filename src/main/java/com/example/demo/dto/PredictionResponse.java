package com.example.demo.dto;

import java.time.LocalDate;

public class PredictionResponse {
    private LocalDate date;

    public PredictionResponse(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}
