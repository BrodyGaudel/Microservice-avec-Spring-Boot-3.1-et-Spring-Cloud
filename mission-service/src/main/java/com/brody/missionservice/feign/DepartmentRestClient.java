package com.brody.missionservice.feign;

import com.brody.missionservice.dto.DepartmentDTO;
import com.brody.missionservice.exceptions.DepartmentNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface DepartmentRestClient {

    @GetMapping(path="api/v1/department/get/{id}")
    DepartmentDTO getDepartmentById(@PathVariable(name = "id") String id) throws DepartmentNotFoundException;
}
