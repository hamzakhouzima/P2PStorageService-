package com.youcode.networkstorageservice.Dto;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Data
@Component
@ToString
public class PatientDto {

//    private String patientId;

    private Demographics demographics;
    private MedicalHistory medicalHistory;
    private VitalSigns vitalSigns;
    private LaboratoryTests laboratoryTests;
    private HeartData heartData;
    private Lifestyle lifestyle;
    private ContactInformation contactInformation;


//    private Long timestamp;



}



