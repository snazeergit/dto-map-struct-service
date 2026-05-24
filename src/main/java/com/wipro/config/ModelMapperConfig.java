package com.wipro.config;

import com.wipro.dto.EmployeeResponseDTO;
import com.wipro.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.config.Configuration.AccessLevel;


@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);

        modelMapper.typeMap(Employee.class, EmployeeResponseDTO.class)
                .addMappings(mapper -> {
                    mapper.map(Employee::getName,
                            //EmployeeResponseDTO::setEmployeeName);
                            EmployeeResponseDTO::setName);
                });
        return modelMapper;
    }
}