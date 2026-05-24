package com.wipro.service;

import com.wipro.dto.EmployeeRequestDTO;
import com.wipro.dto.EmployeeResponseDTO;
import com.wipro.dto.EmployeeSummaryDTO;
import com.wipro.entity.Employee;
import com.wipro.exception.EmployeeNotFoundException;
import com.wipro.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
/**
 * Service implementation for managing Employee entities without using ModelMapper.
 * This implementation manually maps between entities and DTOs.
 */
//@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {
        Employee employee = Employee.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .department(dto.getDepartment())
                .salary(dto.getSalary())
                .password((dto.getPassword()))
                .dateOfJoining(LocalDate.now())
                .build();
        Employee saved = employeeRepository.save(employee);
        return mapToResponseDTO(saved);
    }

    @Override
    public EmployeeResponseDTO getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee with id " + id + " not found"));
        return mapToResponseDTO(employee);
    }

    @Override
    public List<EmployeeSummaryDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(emp -> EmployeeSummaryDTO.builder()
                        .id(emp.getId())
                        .name(emp.getName())
                        .email(emp.getEmail())
                        .build())
                .toList();
    }

    private EmployeeResponseDTO mapToResponseDTO(Employee emp) {
        return EmployeeResponseDTO.builder()
                .id(emp.getId())
                .name(emp.getName())
                .email(emp.getEmail())
                .department(emp.getDepartment())
                .dateOfJoining(emp.getDateOfJoining())
                .build();
    }

}
