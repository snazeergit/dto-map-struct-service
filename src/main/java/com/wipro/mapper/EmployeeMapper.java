package com.wipro.mapper;

import com.wipro.dto.EmployeeRequestDTO;
import com.wipro.dto.EmployeeResponseDTO;
import com.wipro.dto.EmployeeSummaryDTO;
import com.wipro.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class EmployeeMapper {

    @Autowired
    private ModelMapper modelMapper;

    // For mapping EmployeeRequestDTO to Employee
    public Employee mapToEntity(EmployeeRequestDTO dto) {
        return modelMapper.map(dto, Employee.class);
    }

    // For mapping Employee to EmployeeResponseDTO
    public EmployeeResponseDTO mapToResponseDTO(Employee emp) {
        return modelMapper.map(emp, EmployeeResponseDTO.class);
    }

    // For mapping List<Employee> to List<EmployeeSummaryDTO>
    public List<EmployeeSummaryDTO> mapToSummaryDTO(List<Employee> emps) {
        return emps.stream()
                .map(emp -> modelMapper.map(emp, EmployeeSummaryDTO.class))
                .toList();
    }

}
