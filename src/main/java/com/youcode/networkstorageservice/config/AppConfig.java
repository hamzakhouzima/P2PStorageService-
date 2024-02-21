package com.youcode.networkstorageservice.config;

//import com.youcode.interplanetary.GlobalConverters.FormatConverter;
import com.youcode.networkstorageservice.GlobalConverters.FormatConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public FormatConverter formatConverter() {
        return new FormatConverter();
    }
}
