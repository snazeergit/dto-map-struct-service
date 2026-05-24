package com.wipro.controller;

import com.wipro.dto.EmployeeRequestDTO;
import com.wipro.dto.EmployeeResponseDTO;
import com.wipro.dto.EmployeeSummaryDTO;
import com.wipro.service.IEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Operation(summary = "Register a new employee",
            description = "Registers a new employee with the provided details.")
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(
            @Valid @RequestBody EmployeeRequestDTO dto) {
        return new ResponseEntity<>(employeeService.createEmployee(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get employee by ID",
            description = "Retrieves the details of an employee by their ID.")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(
            @PathVariable Long id) {
        return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
    }

    @Operation(summary = "Get all employees",
            description = "Retrieves a list of all employees with their summary details.")
    @GetMapping
    public ResponseEntity<List<EmployeeSummaryDTO>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

}
