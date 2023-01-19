package com.brody.missionservice.services.implementation;

import com.brody.missionservice.dto.DepartmentDTO;
import com.brody.missionservice.dto.EmployeeDTO;
import com.brody.missionservice.dto.MissionDTO;
import com.brody.missionservice.entities.Mission;
import com.brody.missionservice.exceptions.DepartmentNotFoundException;
import com.brody.missionservice.exceptions.EmployeeNotFoundException;
import com.brody.missionservice.exceptions.MissionNotFoundException;
import com.brody.missionservice.feign.DepartmentRestClient;
import com.brody.missionservice.feign.EmployeeRestClient;
import com.brody.missionservice.mapping.Mappers;
import com.brody.missionservice.repositories.MissionRepository;
import com.brody.missionservice.services.MissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class MissionServiceImpl implements MissionService {

    private static final String MISSION_NOT_FOUND = "MISSION NOT FOUND";
    private static final String MISSION_FOUND = "MISSION(S) FOUND";

    private final MissionRepository missionRepository;
    private final DepartmentRestClient departmentRestClient;
    private final EmployeeRestClient employeeRestClient;
    private final Mappers mappers;

    public MissionServiceImpl(MissionRepository missionRepository, DepartmentRestClient departmentRestClient,
                              EmployeeRestClient employeeRestClient, Mappers mappers) {
        this.missionRepository = missionRepository;
        this.departmentRestClient = departmentRestClient;
        this.employeeRestClient = employeeRestClient;
        this.mappers = mappers;
    }


    @Override
    public MissionDTO addNewMission(MissionDTO missionDTO) throws EmployeeNotFoundException, DepartmentNotFoundException {
        log.info("In addNewMission()");
        EmployeeDTO employee = employeeRestClient.getEmployeeById(missionDTO.getEmployeeId());
        DepartmentDTO department = departmentRestClient.getDepartmentById(missionDTO.getDepartmentId());

        Mission mission = mappers.fromMissionDTO(missionDTO);
        if(mission == null){
            log.warn("mission not saved");
            return null;
        }else {
            mission.setDepartmentId(department.getId());
            mission.setEmployeeId(employee.getId());
            mission.setId(UUID.randomUUID().toString());
            Mission missionSaved = missionRepository.save(mission);
            log.info("mission saved");
            return mappers.fromMission(missionSaved);
        }
    }

    @Override
    public MissionDTO updateMission(MissionDTO missionDTO) throws MissionNotFoundException, EmployeeNotFoundException, DepartmentNotFoundException {
        log.info("In updateMission()");
        Mission mission = missionRepository.findById(missionDTO.getId())
                .orElseThrow( () -> new MissionNotFoundException(MISSION_NOT_FOUND));

        EmployeeDTO employee = employeeRestClient.getEmployeeById(missionDTO.getEmployeeId());
        DepartmentDTO department = departmentRestClient.getDepartmentById(missionDTO.getDepartmentId());


        Mission mission1 = mappers.fromMissionDTO(missionDTO);
        if(mission1 == null){
            log.warn("mission not updated");
            return null;
        }else {
            mission1.setId(mission.getId());
            mission1.setDepartmentId(department.getId());
            mission1.setEmployeeId(employee.getId());
            mission1.setId(mission.getId());
            Mission missionUpdated = missionRepository.save(mission1);
            log.info("mission updated");
            return mappers.fromMission(missionUpdated);
        }
    }

    @Override
    public MissionDTO getMissionById(String id) throws MissionNotFoundException {
        log.info("In getMissionById()");
        Mission mission = missionRepository.findById(id)
                .orElseThrow( () -> new MissionNotFoundException(MISSION_NOT_FOUND));
        log.info(MISSION_FOUND);
        return mappers.fromMission(mission);
    }

    @Override
    public List<MissionDTO> getMissionByTitle(String title) {
        log.info("In getMissionByTitle()");
        List<Mission> missions = missionRepository.findByTitleContains(title);
        log.info(MISSION_FOUND);
        return mappers.fromMissions(missions);
    }

    @Override
    public List<MissionDTO> getAllMissions() {
        log.info("In getAllMissions()");
        List<Mission> missions = missionRepository.findAll();
        log.info(MISSION_FOUND);
        return mappers.fromMissions(missions);
    }

    @Override
    public List<MissionDTO> getAllMissionsByDepartmentId(String id) {
        log.info("In getAllMissionsByDepartmentId()");
        List<Mission> missions = missionRepository.finByDepartmentId(id);
        log.info(MISSION_FOUND);
        return mappers.fromMissions(missions);
    }

    @Override
    public List<MissionDTO> getAllMissionsByEmployeeId(String id) {
        log.info("In getAllMissionsByEmployeeId()");
        List<Mission> missions = missionRepository.finByEmployeeId(id);
        log.info(MISSION_FOUND);
        return mappers.fromMissions(missions);
    }

    @Override
    public void deleteMissionById(String id) {
        log.info("In deleteMissionById()");
        missionRepository.deleteById(id);
        log.info("mission deleted");
    }

    @Override
    public void deleteAllMissions() {
        log.info("In deleteAllMissions()");
        missionRepository.deleteAll();
        log.info("all missions deleted");
    }

    @Override
    public void deleteAllMissionsByDepartmentId(String id) {
        log.info("In deleteAllMissionsByDepartmentId()");
        List<Mission> missions = missionRepository.finByDepartmentId(id);
        missionRepository.deleteAll(missions);
        log.info("all missions for department deleted");
    }

    @Override
    public void deleteAllMissionsByEmployeeId(String id) {
        log.info("In deleteAllMissionsByEmployeeId()");
        List<Mission> missions = missionRepository.finByEmployeeId(id);
        missionRepository.deleteAll(missions);
        log.info("all missions for department deleted");
    }
}
