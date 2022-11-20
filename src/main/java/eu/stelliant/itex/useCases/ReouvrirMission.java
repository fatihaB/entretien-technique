package eu.stelliant.itex.useCases;

import eu.stelliant.itex.domain.Mission;
import eu.stelliant.itex.domain.repository.MissionRepository;

public final class ReouvrirMission {

    private final MissionRepository missionRepository;

    public ReouvrirMission(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public void handle(Mission mission) {
        //Réouverture de la mission
        mission.setStatutMission(Mission.StatutMission.A_QUALIFIER);
        mission.setCommentaireCloture(null);
        mission.setDateCloture(null);
        mission.setTypeCloture(null);

        missionRepository.enregistrer(mission);


    }
}
