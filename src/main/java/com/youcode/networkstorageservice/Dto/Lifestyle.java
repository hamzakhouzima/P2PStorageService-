package com.youcode.networkstorageservice.Dto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class Lifestyle {
    private String smokingStatus;
    private AlcoholConsumption alcoholConsumption;
    private PhysicalActivity physicalActivity;
    private String diet;

    @AllArgsConstructor
    public static class AlcoholConsumption {
        private String frequency;
        private int amount;
    }

    @AllArgsConstructor
    public static class PhysicalActivity {
        private String frequency;
        private String duration;
    }
}
