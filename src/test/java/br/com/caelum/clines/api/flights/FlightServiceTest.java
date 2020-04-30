package br.com.caelum.clines.api.flights;

import br.com.caelum.clines.shared.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    @Mock(lenient = true)
    private FlightFactory flightFactory;

    @Mock(lenient = true)
    private FlightViewFactory viewFactory;

    @Mock
    private FlightRepository repository;

    @InjectMocks
    private FlightService service;

    private final static String DEFAULT_DATE = "20-10-2020";

    private final static String DEFAULT_DEPARTURE_AIRPORT_CODE = "GRU";

    private final static Location DEFAULT_DEPARTURE_LOCATON = new Location(Country.BR, "São Paulo", "Guarulhos");

    private final static Airport DEFAULT_DEPARTURE_AIRPORT = new Airport(DEFAULT_DEPARTURE_AIRPORT_CODE, DEFAULT_DEPARTURE_LOCATON);

    private final static LocalDateTime DEFAULT_DEPARTURE_DATETIME = LocalDateTime.now();

    private final static Waypoint DEFAULT_DEPARTURE_WAYPOINT = new Waypoint(DEFAULT_DEPARTURE_AIRPORT, DEFAULT_DEPARTURE_DATETIME, "Gate 4");

    private final static String DEFAULT_ARRIVAL_AIRPORT_CODE = "GIG";

    private final static Location DEFAULT_ARRIVAL_LOCATON = new Location(Country.BR, "Rio de Janeiro", "Rio de Janeiro");

    private final static Airport DEFAULT_ARRIVAL_AIRPORT = new Airport(DEFAULT_ARRIVAL_AIRPORT_CODE, DEFAULT_ARRIVAL_LOCATON);

    private final static LocalDateTime DEFAULT_ARRIVAL_DATETIME = LocalDateTime.now();

    private final static Waypoint DEFAULT_ARRIVAL_WAYPOINT = new Waypoint(DEFAULT_ARRIVAL_AIRPORT, DEFAULT_ARRIVAL_DATETIME, "Gate 4");

    // private final static Flight DEFAULT_FLIGHT = new Flight()

    @Test
    public void shouldReturnEmptyListWhenFindAllByDateAndAirportCodeReturnsEmpty(){
        given(repository.findAllByDateAndAirportCode(DEFAULT_DATE, DEFAULT_DEPARTURE_AIRPORT_CODE)).willReturn(Collections.emptyList());

        List<FlightView> results = service.searchFlightBy(DEFAULT_DATE, DEFAULT_DEPARTURE_AIRPORT_CODE);

        assertThat(results.isEmpty());
    }

    @Test
    public void shouldReturnDatabaseItems(){
        //given(repository.findAllByDateAndAirportCode(DEFAULT_DATE, DEFAULT_DEPARTURE_AIRPORT_CODE)).willReturn(List.of());
    }


}
