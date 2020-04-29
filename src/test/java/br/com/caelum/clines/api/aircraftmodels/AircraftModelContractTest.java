package br.com.caelum.clines.api.aircraftmodels;

import br.com.caelum.clines.shared.domain.AircraftModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static br.com.caelum.clines.utils.MockMvcFactory.configureMvcContextBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith({MockitoExtension.class})
public class AircraftModelContractTest {

    private final String DEFAULT_AIRCRAFT_MODEL_DESCRIPTION = "AX123AC";
    private final AircraftModel DEFAULT_AIRCRAFT_MODEL = new AircraftModel(DEFAULT_AIRCRAFT_MODEL_DESCRIPTION);
    private final AircraftModelForm DEFAULT_AIRCRAFT_MODEL_FROM = new AircraftModelForm(DEFAULT_AIRCRAFT_MODEL_DESCRIPTION);
    @Spy
    private AircraftModelFormMapper formMapper;

    @Mock
    private AircraftModelRepository repository;

    @InjectMocks
    private AircraftModelService service;

    @Captor
    private ArgumentCaptor<AircraftModel> modelCapture;

    @BeforeEach
    void setup() {
        configureMvcContextBy(new AircraftModelController(service));

        given(repository.findByDescription("AX123AC")).willReturn((Optional.empty()));

        then(repository).should().save(modelCapture.capture());

        AircraftModel model = modelCapture.getValue();
        ReflectionTestUtils.setField(model, "id", 1L);

        given(formMapper.map(DEFAULT_AIRCRAFT_MODEL_FROM)).willReturn(DEFAULT_AIRCRAFT_MODEL);
    }
}
