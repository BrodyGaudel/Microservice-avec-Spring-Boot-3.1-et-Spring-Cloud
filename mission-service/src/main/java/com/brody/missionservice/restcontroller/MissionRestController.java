package com.brody.missionservice.restcontroller;


import com.brody.missionservice.dto.MissionDTO;
import com.brody.missionservice.exceptions.DepartmentNotFoundException;
import com.brody.missionservice.exceptions.EmployeeNotFoundException;
import com.brody.missionservice.exceptions.MissionNotFoundException;
import com.brody.missionservice.services.MissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mission")
@CrossOrigin(origins = "*")
public class MissionRestController {

    private final MissionService missionService;

    public MissionRestController(MissionService missionService) {
        this.missionService = missionService;
    }

    @PostMapping("/save")
    @ResponseBody
    public MissionDTO addNewMission(@RequestBody MissionDTO missionDTO) throws EmployeeNotFoundException, DepartmentNotFoundException {
        return missionService.addNewMission(missionDTO);
    }

    @PutMapping("/update")
    @ResponseBody
    public MissionDTO updateMission(@RequestBody MissionDTO missionDTO) throws MissionNotFoundException, EmployeeNotFoundException, DepartmentNotFoundException{
        return missionService.updateMission(missionDTO);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public MissionDTO getMissionById(@PathVariable(name = "id") String id) throws MissionNotFoundException{
        return missionService.getMissionById(id);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<MissionDTO> getMissionByTitle(@RequestParam(name = "title", defaultValue = "") String title){
        return missionService.getMissionByTitle("%"+title+"%");
    }

    @GetMapping("/list")
    @ResponseBody
    public List<MissionDTO> getAllMissions(){
        return missionService.getAllMissions();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMissionById(@PathVariable(name = "id") String id){
        missionService.deleteMissionById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllMissions(){
        missionService.deleteAllMissions();
    }

    @DeleteMapping("/deleteAllByDepartmentId/{departmentId}")
    void deleteAllMissionsByDepartmentId(@PathVariable(name = "departmentId")  String departmentId){
        missionService.deleteAllMissionsByDepartmentId(departmentId);
    }

    @DeleteMapping("/deleteAllByEmployeeId/{employeeId}")
    void deleteAllMissionsByEmployeeId(@PathVariable(name = "employeeId")  String employeeId){
        missionService.deleteAllMissionsByEmployeeId(employeeId);
    }

    @GetMapping("/getAllMissionsByDepartmentId/{id}")
    @ResponseBody
    List<MissionDTO> getAllMissionsByDepartmentId(@PathVariable(name = "id") String id){
        return missionService.getAllMissionsByDepartmentId(id);
    }

    @GetMapping("/getAllMissionsByEmployeeId/{id}")
    @ResponseBody
    List<MissionDTO> getAllMissionsByEmployeeId(@PathVariable(name = "id") String id){
        return missionService.getAllMissionsByEmployeeId(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
