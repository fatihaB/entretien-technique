package eu.stelliant.itex.useCases;

import eu.stelliant.itex.domain.Mission;
import eu.stelliant.itex.domain.Prestation;
import eu.stelliant.itex.domain.repository.MissionRepository;
import eu.stelliant.itex.domain.repository.PrestationRepository;

import java.util.Date;

public final class RequalifierMission {
    private final PrestationRepository prestationRepository;
    private final MissionRepository missionRepository;

    public RequalifierMission(MissionRepository missionRepository, PrestationRepository prestationRepository) {
        this.missionRepository = missionRepository;
        this.prestationRepository = prestationRepository;
    }

    public void handle(Mission mission, String motifRequalification, String commentaireRequalification){

        //Cloture de la prestation courante
        final Prestation prestation = mission.getPrestation();
        prestation.setStatut(Prestation.StatutPrestation.ABANDONNE);
        prestation.setDateFin(new Date());
        prestation.setMotifRequalification(motifRequalification);
        prestation.setMotifCloture(motifRequalification);
        prestation.setCommentaireRequalification(commentaireRequalification);
        prestationRepository.enregistrer(prestation);

        //Création de la nouvelle prestation
        Prestation nouvellePrestation = new Prestation(Prestation.StatutPrestation.ACTIVE);
        prestationRepository.enregistrer(nouvellePrestation);

        //Mise à jour de la mission
        mission.setPrestation(nouvellePrestation);
        mission.setStatutMission(Mission.StatutMission.REQUALIFIER);

        missionRepository.enregistrer(mission);

    }
}
