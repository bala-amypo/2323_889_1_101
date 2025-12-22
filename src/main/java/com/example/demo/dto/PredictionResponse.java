package com.example.demo.dto;

public class PredictionResponse {
    private double value;

    public PredictionResponse(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
