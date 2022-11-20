package eu.stelliant.itex.useCases;

import eu.stelliant.itex.domain.Prestation;
import eu.stelliant.itex.domain.repository.PrestationRepository;

import java.util.Date;

public final class CloturerPrestation {

    private final PrestationRepository prestationRepository;

    public CloturerPrestation(PrestationRepository prestationRepository) {
        this.prestationRepository = prestationRepository;
    }

    public void handle(Prestation prestation, String motifCloture) {
        prestation.setStatut(Prestation.StatutPrestation.ABANDONNE);
        prestation.setDateFin(new Date());
        prestation.setMotifCloture(motifCloture);

        prestationRepository.enregistrer(prestation);

    }
}
