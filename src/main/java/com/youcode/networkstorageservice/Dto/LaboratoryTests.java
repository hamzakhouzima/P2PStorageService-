package com.youcode.networkstorageservice.Dto;

import com.youcode.networkstorageservice.Dto.nested.BloodSugar;
import com.youcode.networkstorageservice.Dto.nested.Electrolytes;
import com.youcode.networkstorageservice.Dto.nested.LipidProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Data
public class LaboratoryTests {
    private LipidProfile lipidProfile;
    private BloodSugar bloodSugar;
    private Electrolytes electrolytes;
    private double crp;






}
