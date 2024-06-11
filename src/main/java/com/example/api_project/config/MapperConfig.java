package com.example.api_project.config;

import com.example.api_project.mapper.CityMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public CityMapper cityMapper() {
        return Mappers.getMapper(CityMapper.class);
    }
}
