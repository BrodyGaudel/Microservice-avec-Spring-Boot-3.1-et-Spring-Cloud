package com.brody.missionservice.services;

import com.brody.missionservice.dto.MissionDTO;
import com.brody.missionservice.exceptions.DepartmentNotFoundException;
import com.brody.missionservice.exceptions.EmployeeNotFoundException;
import com.brody.missionservice.exceptions.MissionNotFoundException;

import java.util.List;

public interface MissionService {
    MissionDTO addNewMission(MissionDTO missionDTO) throws EmployeeNotFoundException, DepartmentNotFoundException;
    MissionDTO updateMission(MissionDTO missionDTO) throws MissionNotFoundException, EmployeeNotFoundException, DepartmentNotFoundException;
    MissionDTO getMissionById(String id) throws MissionNotFoundException;
    List<MissionDTO> getMissionByTitle(String title);
    List<MissionDTO> getAllMissions();
    List<MissionDTO> getAllMissionsByDepartmentId(String id);
    List<MissionDTO> getAllMissionsByEmployeeId(String id);
    void deleteMissionById(String id);
    void deleteAllMissions();
    void deleteAllMissionsByDepartmentId(String id);
    void deleteAllMissionsByEmployeeId(String id);
}
