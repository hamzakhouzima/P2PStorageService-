package com.youcode.networkstorageservice.Dto;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ContactInformation {
    @NotNull
    private String primaryPhone;
    private String secondaryPhone;
    @Email
    private String email;
    private EmergencyContact emergencyContact;

    @AllArgsConstructor
    public static class EmergencyContact {
        private String name;
        private String phone;
        private String relationship;
    }
}
