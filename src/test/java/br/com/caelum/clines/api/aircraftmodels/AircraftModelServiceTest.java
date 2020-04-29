package br.com.caelum.clines.api.aircraftmodels;

import br.com.caelum.clines.shared.domain.AircraftModel;
import br.com.caelum.clines.shared.exceptions.ResourceAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.only;

@ExtendWith(MockitoExtension.class)
public class AircraftModelServiceTest {
   private static final String AIRCRAFT_MODEL_DESCRIPTION = "Boeing 737";
   private static final Long AIRCRAFT_MODEL_ID = 1L;
   private static final AircraftModelForm AIRCRAFT_MODEL_FORM = new AircraftModelForm(AIRCRAFT_MODEL_DESCRIPTION);
   private static final AircraftModel AIRCRAFT_MODEL = new AircraftModel(AIRCRAFT_MODEL_ID, AIRCRAFT_MODEL_DESCRIPTION);

   @Spy
   private AircraftModelFormMapper formMapper;

   @Mock
   private AircraftModelRepository repository;

   @InjectMocks
   private AircraftModelService service;

   @Test
   void shouldCreateAnAircraftModel() {
      given(formMapper.map(AIRCRAFT_MODEL_FORM)).willReturn(AIRCRAFT_MODEL);
      given(repository.findByDescription(AIRCRAFT_MODEL_DESCRIPTION)).willReturn(Optional.empty());

      var aircraftModel = service.createAircraftModelBy(AIRCRAFT_MODEL_FORM);

      assertEquals(AIRCRAFT_MODEL_ID, aircraftModel);

      then(repository).should().findByDescription(AIRCRAFT_MODEL_DESCRIPTION);
      then(repository).should().save(AIRCRAFT_MODEL);
      then(formMapper).should(only()).map(AIRCRAFT_MODEL_FORM);
   }
    @Test
   void shouldThrowResourceAlreadyExistsIfAircraftModelAlreadyExists() {
      given(repository.findByDescription(AIRCRAFT_MODEL_DESCRIPTION)).willReturn(Optional.of(AIRCRAFT_MODEL));

      assertThrows(ResourceAlreadyExistsException.class,
              () -> {
                 service.createAircraftModelBy(AIRCRAFT_MODEL_FORM);
              });
   }
}
