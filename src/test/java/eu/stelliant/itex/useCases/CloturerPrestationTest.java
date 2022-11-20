package eu.stelliant.itex.useCases;

import eu.stelliant.itex.domain.Prestation;
import eu.stelliant.itex.domain.repository.PrestationRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public final class CloturerPrestationTest {

    @Mock
    private PrestationRepository prestationRepository;

    @InjectMocks
    private CloturerPrestation cloturerPrestation;

    @Test
    public void doit_cloturer_la_prestation(){
        //Given
        final Prestation prestation = new Prestation(Prestation.StatutPrestation.ACTIVE);
        final String motifCloture = "mission terminée";

        //When
        cloturerPrestation.handle(prestation, motifCloture);

        //Then
        Mockito.verify(prestationRepository).enregistrer(prestation);
        Assertions.assertThat(prestation.getStatut()).isEqualTo(Prestation.StatutPrestation.ABANDONNE);
        Assertions.assertThat(prestation.getMotifCloture()).isEqualTo(motifCloture);
        Assertions.assertThat(prestation.getMotifRequalification()).isNull();
        Assertions.assertThat(prestation.getDateFin()).isToday();
    }
}