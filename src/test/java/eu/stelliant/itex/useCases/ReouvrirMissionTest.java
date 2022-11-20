package eu.stelliant.itex.useCases;

import eu.stelliant.itex.domain.Mission;
import eu.stelliant.itex.domain.Prestation;
import eu.stelliant.itex.domain.repository.MissionRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public final class ReouvrirMissionTest {

    @Mock
    private MissionRepository missionRepository;

    @InjectMocks
    private ReouvrirMission reouvrirMission;

    @Test
    public void doit_réouvrir_la_mission() {
        //Given
        Prestation prestation = new Prestation(Prestation.StatutPrestation.ACTIVE);

        Mission mission = new Mission(Mission.StatutMission.CLOTURER, prestation);
        mission.setCommentaireCloture("Mission terminée");
        mission.setTypeCloture(Mission.TypeCloture.ANNULATION);
        mission.setDateCloture(new Date());

        //When
        reouvrirMission.handle(mission);

        //Then
        Assertions.assertThat(mission.getStatutMission()).isEqualTo(Mission.StatutMission.A_QUALIFIER);
        Assertions.assertThat(mission.getCommentaireCloture()).isNull();
        Assertions.assertThat(mission.getDateCloture()).isNull();
        Assertions.assertThat(mission.getTypeCloture()).isNull();

        Mockito.verify(missionRepository).enregistrer(mission);
    }
}