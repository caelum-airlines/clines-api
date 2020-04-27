package br.com.caelum.clines.api.aircraftmodels;

import br.com.caelum.clines.api.aircraft.AircraftRepository;
import br.com.caelum.clines.shared.domain.Aircraft;
import br.com.caelum.clines.shared.domain.AircraftModel;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.only;

@ExtendWith(MockitoExtension.class)
public class AircraftModelServiceTest {

    private static final AircraftModel DEFAULT_AIRCRAFT_MODEL = new AircraftModel("Description");
    private static final List<AircraftModel> ALL_AIRCRAFT_MODELS = List.of(DEFAULT_AIRCRAFT_MODEL);

    @Mock
    private AircraftModelRepository repository;

    @InjectMocks
    private AircraftModelService service;

    @Test
    public void shouldReturnAListOfAircraftModelViewForEachAircraftInRepository() {
        given(repository.findAll()).willReturn(ALL_AIRCRAFT_MODELS);

        var allAircraftModelViews = service.listAllAircraftModels();

        then(repository).should(only()).findAll();

        assertEquals(ALL_AIRCRAFT_MODELS.size(), allAircraftModelViews.size());
    }
}
