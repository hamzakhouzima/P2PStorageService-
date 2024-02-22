package com.youcode.networkstorageservice.Dto.nested;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Data
@Component
public  class AlcoholConsumption {
    private String frequency;
    private int amount;
}