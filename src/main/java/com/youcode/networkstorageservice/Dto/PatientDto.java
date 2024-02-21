package com.youcode.networkstorageservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@Component
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
