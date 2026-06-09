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
 * Service implementation for managing Employee entities  using MapStruct.
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper mapper;

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {
        Employee employee = mapper.toEntity(dto);
        employee.setDateOfJoining(LocalDate.now()); // Set date of joining to current date
        Employee saved = employeeRepository.save(employee);
        return mapper.toResponseDTO(saved);
    }

    @Override
    public EmployeeResponseDTO getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee with id " + id + " not found"));
        return mapper.toResponseDTO(employee);
    }

    @Override
    public List<EmployeeSummaryDTO> getAllEmployees() {
        return mapper.toSummaryDTOs(employeeRepository.findAll());
    }

}
