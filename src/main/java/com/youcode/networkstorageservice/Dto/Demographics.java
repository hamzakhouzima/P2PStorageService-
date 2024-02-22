package com.youcode.networkstorageservice.Dto;

import com.youcode.networkstorageservice.Dto.nested.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Data
public class Demographics {
    private int age;
    private String gender;
    private String ethnicity;
    private Location location;


}