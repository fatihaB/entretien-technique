package eu.stelliant.itex.useCases;

import eu.stelliant.itex.domain.Mission;
import eu.stelliant.itex.domain.Prestation;
import eu.stelliant.itex.domain.repository.MissionRepository;
import eu.stelliant.itex.domain.repository.PrestationRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public final class RequalifierMissionTest {

    @Mock
    private PrestationRepository prestationRepository;

    @Mock
    private MissionRepository missionRepository;

    @InjectMocks
    private RequalifierMission requalifierMission;

    @Test
    public void doit_requalifier_la_mission(){
        //Given
        final Prestation prestation = new Prestation(Prestation.StatutPrestation.ACTIVE);
        final Mission mission = new Mission(Mission.StatutMission.A_EXPERTISER, prestation);
        final String motifRequalification = "première qualification erronée";
        final String commentaireRequalification = "requalifier";

        //When
        requalifierMission.handle(mission, motifRequalification, commentaireRequalification);

        //Then
        Assertions.assertThat(prestation.getStatut()).isEqualTo(Prestation.StatutPrestation.ABANDONNE);
        Assertions.assertThat(prestation.getDateFin()).isToday();
        Assertions.assertThat(prestation.getMotifCloture()).isEqualTo(motifRequalification);
        Assertions.assertThat(prestation.getMotifRequalification()).isEqualTo(motifRequalification);
        Assertions.assertThat(prestation.getCommentaireRequalification()).isEqualTo(commentaireRequalification);

        final Prestation nouvellePrestation = mission.getPrestation();
        Assertions.assertThat(nouvellePrestation.getStatut()).isEqualTo(Prestation.StatutPrestation.ACTIVE);

        Assertions.assertThat(mission.getStatutMission()).isEqualTo(Mission.StatutMission.REQUALIFIER);

        Mockito.verify(missionRepository).enregistrer(mission);
        Mockito.verify(prestationRepository).enregistrer(prestation);
        Mockito.verify(prestationRepository).enregistrer(nouvellePrestation);
    }

}