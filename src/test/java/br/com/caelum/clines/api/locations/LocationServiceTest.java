package br.com.caelum.clines.api.locations;

import br.com.caelum.clines.shared.domain.Country;
import br.com.caelum.clines.shared.domain.Location;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LocationServiceTest {
    @Mock
    LocationRepository repository;

    @InjectMocks
    LocationService service;

    // Ao passar um form válido e a localização já existir, deve retornar http status 409.
    @Test
    @Disabled
    public void shouldReturnHttpStatus409WhenCreationConflictsWithExistingObject() {
        Country country = Country.BR;
        String state = "Rio Grande do Sul";
        String city = "Esteio";
        Location location = new Location(country, state, city);

        when(repository.findByCountryAndStateAndCity(country, state, city)).thenReturn(Optional.of(location));

    }


}