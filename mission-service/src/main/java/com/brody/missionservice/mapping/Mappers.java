package com.brody.missionservice.mapping;

import com.brody.missionservice.dto.MissionDTO;
import com.brody.missionservice.entities.Mission;

import java.util.List;

public interface Mappers {
    Mission fromMissionDTO(MissionDTO missionDTO);
    MissionDTO fromMission(Mission mission);
    List<MissionDTO> fromMissions(List<Mission> missions);
}
