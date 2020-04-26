package br.com.caelum.clines.api.flights;

import br.com.caelum.clines.api.aircraft.AircraftViewMapper;
import br.com.caelum.clines.shared.domain.Aircraft;
import br.com.caelum.clines.shared.domain.AircraftModel;
import br.com.caelum.clines.shared.domain.Airport;
import br.com.caelum.clines.shared.domain.Country;
import br.com.caelum.clines.shared.domain.Flight;
import br.com.caelum.clines.shared.domain.Location;
import br.com.caelum.clines.shared.domain.Waypoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static br.com.caelum.clines.utils.MockMvcFactory.configureMvcContextBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.will;

@ExtendWith(MockitoExtension.class)
public class FlightContractTest {

    private static final Long EXISTING_ID = 1L;
    private static final Long NON_EXISTING_ID = 2L;
    private static final String NON_EXISTING_DEPARTURE_CODE = "FRX";
    private static final String NON_EXISTING_ARRIVAL_CODE = "XRF";
    private static final Airport DEPARTURE_AIRPORT = new Airport(1L, "GRU", new Location(Country.BR, "SP", "Guarulhos"));
    private static final Airport ARRIVAL_AIRPORT = new Airport(2L, "CGH", new Location(Country.BR, "SP", "São Paulo"));
    private static final AircraftModel BOEING_737_800 = new AircraftModel(1L, "Boeing 737 800");
    private static final String AC123AX = "AC123AX";
    private static final String NON_EXISTING_AIRCRAFT_CODE = "BC123AX";
    private static final Aircraft DEFAULT_AIRCRAFT = new Aircraft(1L, AC123AX, BOEING_737_800);
    private static final Waypoint DEPARTURE = new Waypoint(1L, DEPARTURE_AIRPORT, LocalDateTime.now(), "23C");
    private static final Waypoint ARRIVAL = new Waypoint(2L, ARRIVAL_AIRPORT, LocalDateTime.now(), "12B");
    private static final Flight FLIGHT = new Flight(EXISTING_ID, BigDecimal.valueOf(235.0), DEPARTURE, ARRIVAL, DEFAULT_AIRCRAFT);

    @Spy
    private WaypointViewMapper waypointViewMapper;

    @Spy
    private AircraftViewMapper aircraftViewMapper;

    @InjectMocks
    private FlightViewFactory viewFactory;

    @Spy
    private WaypointFormMapper waypointFormMapper;

    @Mock(lenient = true)
    private ExistingAircraftService aircraftService;

    @Mock(lenient = true)
    private ExistingAirportService airportService;

    @InjectMocks
    private FlightFactory factory;

    @Mock(lenient = true)
    private FlightRepository repository;

    @BeforeEach
    void setup() {
        var service = new FlightService(repository, viewFactory, factory);
        configureMvcContextBy(new FlightController(service));

        given(repository.findById(EXISTING_ID)).willReturn(Optional.of(FLIGHT));
        given(repository.findById(NON_EXISTING_ID)).willReturn(Optional.empty());
        given(repository.findAll()).willReturn(List.of(FLIGHT));

        will(invocation -> {
            var flight = (Flight) invocation.getArgument(0);
            ReflectionTestUtils.setField(flight, "id", NON_EXISTING_ID);
            return null;
        }).given(repository).save(any(Flight.class));

        given(aircraftService.findByCode(AC123AX)).willReturn(Optional.of(DEFAULT_AIRCRAFT));
        given(airportService.findById(DEPARTURE_AIRPORT.getId())).willReturn(Optional.of(DEPARTURE_AIRPORT));
        given(airportService.findById(ARRIVAL_AIRPORT.getId())).willReturn(Optional.of(ARRIVAL_AIRPORT));

        given(airportService.findByCode(NON_EXISTING_DEPARTURE_CODE)).willReturn(Optional.empty());
        given(airportService.findByCode(NON_EXISTING_ARRIVAL_CODE)).willReturn(Optional.empty());
        given(aircraftService.findByCode(NON_EXISTING_AIRCRAFT_CODE)).willReturn(Optional.empty());
    }

}
