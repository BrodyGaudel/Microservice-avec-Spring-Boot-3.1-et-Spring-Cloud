package com.brody.missionservice.mapping.implementation;

import com.brody.missionservice.dto.MissionDTO;
import com.brody.missionservice.entities.Mission;
import com.brody.missionservice.mapping.Mappers;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MappersImpl implements Mappers {

    @Override
    public Mission fromMissionDTO(MissionDTO missionDTO) {
        try{
            return new Mission(
                    missionDTO.getId(),
                    missionDTO.getTitle(),
                    missionDTO.getDescription(),
                    missionDTO.getDepartmentId(),
                    missionDTO.getEmployeeId()
            );
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public MissionDTO fromMission(Mission mission) {
        try{
            return new MissionDTO(
                    mission.getId(),
                    mission.getTitle(),
                    mission.getDescription(),
                    mission.getDepartmentId(),
                    mission.getEmployeeId()
            );
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<MissionDTO> fromMissions(List<Mission> missions) {
        try{
            return missions.stream().map(this::fromMission).toList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }
}
