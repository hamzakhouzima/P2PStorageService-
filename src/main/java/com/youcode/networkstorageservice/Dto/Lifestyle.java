package com.youcode.networkstorageservice.Dto;

import com.youcode.networkstorageservice.Dto.nested.AlcoholConsumption;
import com.youcode.networkstorageservice.Dto.nested.PhysicalActivity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Data
public class Lifestyle {
    private String smokingStatus;
    private AlcoholConsumption alcoholConsumption;
    private PhysicalActivity physicalActivity;
    private String diet;




}
