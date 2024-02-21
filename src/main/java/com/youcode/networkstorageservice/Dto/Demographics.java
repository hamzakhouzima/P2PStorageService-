package com.youcode.networkstorageservice.Dto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class Demographics {
    private int age;
    private String gender;
    private String ethnicity;
    private Location location;

    @AllArgsConstructor
    public static class Location {
        private String country;
        private String region;
    }
}