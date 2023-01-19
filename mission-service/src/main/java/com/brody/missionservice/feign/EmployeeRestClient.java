package com.brody.missionservice.feign;

import com.brody.missionservice.dto.EmployeeDTO;
import com.brody.missionservice.exceptions.EmployeeNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "EMPLOYEE-SERVICE")
public interface EmployeeRestClient {
    @GetMapping(path="api/v1/employee/get/{id}")
    EmployeeDTO getEmployeeById(@PathVariable(name = "id") String id) throws EmployeeNotFoundException;
}
