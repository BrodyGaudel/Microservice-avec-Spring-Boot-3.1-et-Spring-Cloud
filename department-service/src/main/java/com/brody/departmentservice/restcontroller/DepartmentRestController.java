package com.brody.departmentservice.restcontroller;

import com.brody.departmentservice.dto.DepartmentDTO;
import com.brody.departmentservice.exceptions.DepartmentNotFoundException;
import com.brody.departmentservice.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
@CrossOrigin(origins = "*")
public class DepartmentRestController {
    private final DepartmentService departmentService;

    public DepartmentRestController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/save")
    @ResponseBody
    public DepartmentDTO addNewDepartment(@RequestBody DepartmentDTO departmentDTO){
        return departmentService.addNewDepartment(departmentDTO);
    }

    @PutMapping("/update")
    @ResponseBody
    public DepartmentDTO updateDepartment(@RequestBody DepartmentDTO departmentDTO) throws DepartmentNotFoundException {
        return departmentService.updateDepartment(departmentDTO);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public DepartmentDTO getDepartmentById(@PathVariable(name = "id") String id) throws DepartmentNotFoundException{
        return departmentService.getDepartmentById(id);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<DepartmentDTO> getDepartmentByName(@RequestParam(name = "name", defaultValue = "") String name){
        return departmentService.getDepartmentByName("%"+name+"%");
    }

    @GetMapping("/list")
    @ResponseBody
    public List<DepartmentDTO> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDepartmentById(@PathVariable(name = "id") String id){
        departmentService.deleteDepartmentById(id);
    }

    @DeleteMapping("/delete/all")
    public void deleteAllDepartments(){
        departmentService.deleteAllDepartments();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
