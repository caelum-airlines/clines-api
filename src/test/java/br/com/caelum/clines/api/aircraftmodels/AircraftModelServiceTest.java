package br.com.caelum.clines.api.aircraftmodels;

import br.com.caelum.clines.shared.domain.AircraftModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.only;

@ExtendWith(MockitoExtension.class)
class AircraftModelServiceTest {

    private static final AircraftModel DEFAULT_AIRCRAFT_MODEL = new AircraftModel();
    private static final List<AircraftModel> ALL_AIRCRAFT_MODEL = List.of(DEFAULT_AIRCRAFT_MODEL);

    @Spy
    private AircraftModelViewMapper viewMapper;

    @Mock
    private AircraftModelRepository repository;

    @InjectMocks
    private AircraftModelService service;

    @Test
    void shouldReturnAListOfAircraftModelViewForEachAircraftInRepository() {
        given(repository.findAll()).willReturn(ALL_AIRCRAFT_MODEL);

        var allAircraftModelViews = service.listAllAircraftModels();
        assertEquals(ALL_AIRCRAFT_MODEL.size(), allAircraftModelViews.size());

    }

    @Test
    void shouldReturnAnEmptyListWhenHasNoAircraftModelInRepository() {
        given(repository.findAll()).willReturn(List.of());
        var allAircraftModelViews = service.listAllAircraftModels();

        assertEquals(0, allAircraftModelViews.size());
        then(repository).should(only()).findAll();
        then(viewMapper).shouldHaveNoInteractions();
    }
}
