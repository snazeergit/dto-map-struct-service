package com.wipro.mapper;

import com.wipro.dto.EmployeeRequestDTO;
import com.wipro.dto.EmployeeResponseDTO;
import com.wipro.dto.EmployeeSummaryDTO;
import com.wipro.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/*
MapStruct is a code generation library that simplifies the mapping between Java objects, especially between DTOs and entities.
It generates the mapping code at compile time, which is efficient and type-safe.

The EmployeeMapper interface defines methods for converting between EmployeeRequestDTO, EmployeeResponseDTO,
EmployeeSummaryDTO, and the Employee entity. MapStruct will generate the implementation of this interface at compile time.

The toEntity method maps an EmployeeRequestDTO to an Employee entity, ignoring the id, createdAt, updatedAt, and dateOfJoining fields
since they are managed by the database or set in the service layer.

The toResponseDTO method maps an Employee entity to an EmployeeResponseDTO, which is used for sending employee data back to the client.

The toSummaryDTOs method maps a list of Employee entities to a list of EmployeeSummaryDTOs, which can be used for displaying
a summary of employee information in a list view.

This mapper helps to keep the service layer clean and focused on business logic, while MapStruct handles the conversion between
different data representations.

The use of MapStruct improves code maintainability and reduces the likelihood of errors in manual mapping code, as it generates
the mapping logic based on the defined method signatures and annotations.

The @Mapping annotations specify how to handle specific fields during the mapping process, such as ignoring certain fields or
mapping fields with different names.

@Mapper(componentModel = "spring") tells MapStruct to generate a Spring Bean for this mapper, allowing it to be injected into other
components like services.
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    //Request DTO to Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateOfJoining", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Employee toEntity(EmployeeRequestDTO dto);

    //Entity to Response DTO
    EmployeeResponseDTO toResponseDTO(Employee emp);

    //Entity to Summary DTO
    EmployeeSummaryDTO toSummaryDTO(Employee employee);
    List<EmployeeSummaryDTO> toSummaryDTOs(List<Employee> employees);
}
