package com.youcode.networkstorageservice.Dto;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class VitalSigns {
    private BloodPressure bloodPressure;
    private HeartRate heartRate;
    private int respiratoryRate;
    private double bmi;

    @AllArgsConstructor
    public static class BloodPressure {
        private int systolic;
        private int diastolic;
    }

    @AllArgsConstructor
    public static class HeartRate {
        private int resting;
        private int exercise;
    }
}
