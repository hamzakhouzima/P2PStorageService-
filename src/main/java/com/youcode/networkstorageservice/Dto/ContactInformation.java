package com.youcode.networkstorageservice.Dto;

import com.youcode.networkstorageservice.Dto.nested.EmergencyContact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Data
public class ContactInformation {
//    @NotNull
    private String primaryPhone;
    private String secondaryPhone;
    @Email
    private String email;
    private EmergencyContact emergencyContact;

}
