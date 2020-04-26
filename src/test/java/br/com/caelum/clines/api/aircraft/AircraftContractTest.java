package br.com.caelum.clines.api.aircraft;


import br.com.caelum.clines.shared.domain.Aircraft;
import br.com.caelum.clines.shared.domain.AircraftModel;
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

@ExtendWith({MockitoExtension.class})
public class AircraftContractTest {

    private static final Long EXISTING_MODEL_ID = 1L;
    private static final Long NON_EXISTING_MODEL_ID = 2L;
    private static final String AIRCRAFT_CODE = "BX123AC";
    private static final AircraftModel AIRCRAFT_MODEL = new AircraftModel(EXISTING_MODEL_ID, "Boeing 737");
    private static final Aircraft DEFAULT_AIRCRAFT = new Aircraft(AIRCRAFT_CODE, AIRCRAFT_MODEL);
    private static final List<Aircraft> ALL_AIRCRAFT = List.of(DEFAULT_AIRCRAFT);

    @Spy
    private AircraftFormMapper formMapper;

    @Spy
    private AircraftViewMapper viewMapper;

    @Mock(lenient = true)
    private ExistingAircraftModelService modelService;

    @Mock(lenient = true)
    private AircraftRepository repository;

    @InjectMocks
    private AircraftService service;

    @BeforeEach
    void setup() {
        configureMvcContextBy(new AircraftController(service));

        given(repository.findByCode("BX123AC")).willReturn(Optional.of(DEFAULT_AIRCRAFT));
        given(repository.findByCode("AX123AC")).willReturn(Optional.empty());

        given(repository.findAll()).willReturn(ALL_AIRCRAFT);
        given(modelService.findById(EXISTING_MODEL_ID)).willReturn(Optional.of(AIRCRAFT_MODEL));
        given(modelService.findById(NON_EXISTING_MODEL_ID)).willReturn(Optional.empty());
    }

}
