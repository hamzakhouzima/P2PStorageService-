package com.youcode.networkstorageservice.Dto;


import com.youcode.networkstorageservice.Dto.nested.BloodPressure;
import com.youcode.networkstorageservice.Dto.nested.HeartRate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Data
public class VitalSigns {
    private BloodPressure bloodPressure;
    private HeartRate heartRate;
    private int respiratoryRate;
    private double bmi;




}
