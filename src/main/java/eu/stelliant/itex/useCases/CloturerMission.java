package eu.stelliant.itex.useCases;

import eu.stelliant.itex.domain.Mission;
import eu.stelliant.itex.domain.repository.MissionRepository;

import java.util.Date;

public final class CloturerMission {

    private final MissionRepository missionRepository;

    public CloturerMission(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public void handle(Mission mission, String typeCloture, String commentaireCloture) {
        mission.setTypeCloture(Mission.TypeCloture.valueOf(typeCloture));
        mission.setDateCloture(new Date());
        mission.setCommentaireCloture(commentaireCloture);
        mission.setStatutMission(Mission.StatutMission.CLOTURER);

        missionRepository.enregistrer(mission);
    }
}
