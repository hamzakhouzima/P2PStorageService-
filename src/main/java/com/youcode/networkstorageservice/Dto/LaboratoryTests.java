package com.youcode.networkstorageservice.Dto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryTests {
    private LipidProfile lipidProfile;
    private BloodSugar bloodSugar;
    private Electrolytes electrolytes;
    private double crp;

    @AllArgsConstructor
    public static class LipidProfile {
        private int totalCholesterol;
        private int ldl;
        private int hdl;
        private int triglycerides;
    }

    @AllArgsConstructor
    public static class BloodSugar {
        private int fasting;
        private int postprandial;
    }

    @AllArgsConstructor
    public static class Electrolytes {
        private double potassium;
        private double sodium;
        private double chloride;
    }
}
