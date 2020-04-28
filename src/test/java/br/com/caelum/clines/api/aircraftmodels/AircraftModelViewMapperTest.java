package br.com.caelum.clines.api.aircraftmodels;

import br.com.caelum.clines.shared.domain.AircraftModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AircraftModelViewMapperTest {
    private static final String AIRCRAFT_DESCRIPTION = "abc";

    private AircraftModelViewMapper mapper = new AircraftModelViewMapper();

    @Test
    void shouldConvertAircraftModelToAircrafModeltView() {
        var aircraftModel = new AircraftModel(AIRCRAFT_DESCRIPTION);

        var aircraftModelView = mapper.map(aircraftModel);

        assertEquals(AIRCRAFT_DESCRIPTION, aircraftModelView.getDescription());
    }
}

