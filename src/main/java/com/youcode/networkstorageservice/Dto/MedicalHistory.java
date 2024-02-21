package com.youcode.networkstorageservice.Dto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicalHistory {
    private boolean familyHistoryHeartDisease;
    private List<String> existingConditions;
    private List<String> previousSurgeries;
    private List<String> medications;
}