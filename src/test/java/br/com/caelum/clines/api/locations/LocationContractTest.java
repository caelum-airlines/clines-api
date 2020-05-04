package br.com.caelum.clines.api.locations;

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
public class LocationContractTest {

    private static final Long EXISTING_LOCATION_ID = 2L;
    private static final Long NON_EXISTING_LOCATION_ID = 3L;
    private static final Location EXISTING_LOCATION = new Location(Country.BR, "SP", "Guarulhos");

    @Mock(lenient = true)
    private LocationRepository repository;

    @Spy
    private LocationViewMapper viewMapper;

    @Spy
    private LocationFormMapper formMapper;

    @InjectMocks
    private LocationService service;

    @BeforeEach
    void setup() {
        configureMvcContextBy(new LocationController(service));

        given(repository.findById(EXISTING_LOCATION_ID)).willReturn(Optional.of(EXISTING_LOCATION));
        given(repository.findById(NON_EXISTING_LOCATION_ID)).willReturn(Optional.empty());

        given(repository.findAll()).willReturn(List.of(EXISTING_LOCATION));
    }
}