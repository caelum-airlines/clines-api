package br.com.caelum.clines.api.locations;

import br.com.caelum.clines.shared.domain.Country;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationControllerTest {

    @Mock
    private LocationService service;

    @InjectMocks
    private LocationController locationController;

    // Ao passar um form válido, deve retornar http status 201 e a URL do objeto no location da resposta HTTP.
    @Test
    public void shouldReturnHttpStatus201AndHeaderAttributeLocationWhenValidFormIsInformed() {
        Long locationId = 1L;
        LocationForm locationForm = new LocationForm(Country.BR.getDescription(), "São Paulo", "Osasco");
        when(service.createLocationBy(eq(locationForm))).thenReturn(locationId);

        ResponseEntity<?>  responseEntity = locationController.createBy(locationForm);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getHeaders().getLocation());
    }

    // Ao passar um form inválido (incompleto), deve retornar http status 400.
    @Test
    @Disabled
    public void shouldReturnHttpStatus400WhenFormDataIsInvalid() {

    }


}