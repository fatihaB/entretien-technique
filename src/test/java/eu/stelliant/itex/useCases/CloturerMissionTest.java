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

@RunWith(MockitoJUnitRunner.class)
public final class CloturerMissionTest {

    @Mock
    private MissionRepository missionRepository;

    @InjectMocks
    private CloturerMission cloturerMission;

    @Test
    public void doit_cloturer_une_mission() {
        //Given
        Prestation prestation = new Prestation(Prestation.StatutPrestation.ACTIVE);
        Mission mission = new Mission(Mission.StatutMission.A_QUALIFIER, prestation);

        final String typeCloture = "NORMALE";
        final String commentaireCloture = "fin de la mission";

        //When
        cloturerMission.handle(mission, typeCloture, commentaireCloture);

        //Then
        Mockito.verify(missionRepository).enregistrer(mission);
        Assertions.assertThat(mission.getStatutMission()).isEqualTo(Mission.StatutMission.CLOTURER);
        Assertions.assertThat(mission.getCommentaireCloture()).isEqualTo(commentaireCloture);
        Assertions.assertThat(mission.getTypeCloture().name()).isEqualTo(typeCloture);
        Assertions.assertThat(mission.getDateCloture()).isToday();
    }

}