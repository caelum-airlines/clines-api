package br.com.caelum.clines.api.airports;

import br.com.caelum.clines.shared.domain.Airport;
import br.com.caelum.clines.shared.domain.Country;
import br.com.caelum.clines.shared.domain.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static br.com.caelum.clines.utils.MockMvcFactory.configureMvcContextBy;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class AirportContractTest {

    private static final Long EXISTING_LOCATION_ID = 2L;
    private static final Long NON_EXISTING_LOCATION_ID = 3L;
    private static final String NON_EXISTING_AIRPORT_CODE = "CGH";
    private static final String EXISTING_AIRPORT_CODE = "GRU";
    private static final Location GRU_LOCATION = new Location(Country.BR, "SP", "Guarulhos");
    private static final Airport GRU_AIRPORT = new Airport("GRU", GRU_LOCATION);

    @Mock(lenient = true)
    private AirportRepository repository;

    @Spy
    private AirportLocationViewMapper airportLocationViewMapper;

    @InjectMocks
    private AirportViewMapper viewMapper;

    @Spy
    private AirportFormMapper formMapper;

    @Mock(lenient = true)
    private ExistingLocationService locationService;


    @BeforeEach
    void setup() {

        var service = new AirportService(repository, viewMapper, formMapper, locationService);

        configureMvcContextBy(new AirportController(service));

        given(repository.findByCode(NON_EXISTING_AIRPORT_CODE)).willReturn(Optional.empty());
        given(repository.findByCode(EXISTING_AIRPORT_CODE)).willReturn(Optional.of(GRU_AIRPORT));
        given(repository.findAll()).willReturn(List.of(GRU_AIRPORT));
        given(locationService.findById(EXISTING_LOCATION_ID)).willReturn(Optional.of(GRU_LOCATION));
        given(locationService.findById(NON_EXISTING_LOCATION_ID)).willReturn(Optional.empty());
    }
}
