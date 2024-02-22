package com.youcode.networkstorageservice.Dto.nested;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Data
@Component
public  class BloodPressure {
    private int systolic;
    private int diastolic;
}