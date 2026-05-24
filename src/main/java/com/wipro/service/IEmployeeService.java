package com.wipro.service;

import com.wipro.dto.EmployeeRequestDTO;
import com.wipro.dto.EmployeeResponseDTO;
import com.wipro.dto.EmployeeSummaryDTO;

import java.util.List;

public interface IEmployeeService {

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto);

    public EmployeeResponseDTO getEmployee(Long id);

    public List<EmployeeSummaryDTO> getAllEmployees();

}
