package com.wipro.service;

import com.wipro.dto.EmployeeRequestDTO;
import com.wipro.dto.EmployeeResponseDTO;
import com.wipro.dto.EmployeeSummaryDTO;
import com.wipro.entity.Employee;
import com.wipro.exception.EmployeeNotFoundException;
import com.wipro.mapper.EmployeeMapper;
import com.wipro.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service implementation for managing Employee entities using ModelMapper for DTO conversions.
 */
@Service
public class EmployeeServiceImpl_ModelMapper implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper mapper;

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {
        Employee saved = employeeRepository.save(mapper.mapToEntity(dto));
        return mapper.mapToResponseDTO(saved);
    }

    @Override
    public EmployeeResponseDTO getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee with id " + id + " not found"));
        return mapper.mapToResponseDTO(employee);
    }

    @Override
    public List<EmployeeSummaryDTO> getAllEmployees() {
        List<Employee> employees=employeeRepository.findAll();
        return mapper.mapToSummaryDTO(employees);
    }

}
