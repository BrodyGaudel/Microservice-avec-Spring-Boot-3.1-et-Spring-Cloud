package com.brody.employeeservice.restcontroller;

import com.brody.employeeservice.dto.EmployeeDTO;
import com.brody.employeeservice.exceptions.*;
import com.brody.employeeservice.services.EmployeeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin(origins = "*")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/save")
    @ResponseBody
    EmployeeDTO addNewEmployee(@RequestBody EmployeeDTO employeeDTO) throws EmployeePhoneException, EmployeeCinException, EmployeeEmailException, DepartmentNotFoundException {
        return employeeService.addNewEmployee(employeeDTO);
    }

    @PutMapping("/update")
    @ResponseBody
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO) throws EmployeeNotFoundException {
        return employeeService.updateEmployee(employeeDTO);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public EmployeeDTO getEmployeeById(@PathVariable(name = "id") String id) throws EmployeeNotFoundException{
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<EmployeeDTO> getByFirstnameOrName(@RequestParam(name = "keyword", defaultValue = "") String keyword){
        return employeeService.getByFirstnameOrName("%"+keyword+"%");
    }

    @GetMapping("/list")
    @ResponseBody
    public List<EmployeeDTO> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/find/{departmentId}")
    @ResponseBody
    public List<EmployeeDTO> getAllEmployeesByDepartmentId(@PathVariable(name = "departmentId") String departmentId){
        return employeeService.getAllEmployeesByDepartmentId(departmentId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployeeById(@PathVariable(name = "id") String id){
        employeeService.deleteEmployeeById(id);
    }

    @DeleteMapping("/delete/all")
    public void deleteAllEmployees(){
        employeeService.deleteAllEmployees();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
