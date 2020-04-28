package br.com.caelum.clines.api.locations;

import br.com.caelum.clines.shared.domain.Country;
import br.com.caelum.clines.shared.domain.Location;
import br.com.caelum.clines.shared.exceptions.LocationNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {
    @InjectMocks
    private LocationService service;

    @Mock
    private LocationRepository repository;

    @Spy
    private LocationViewMapper mapper;

    @Test
    public void shouldReturnLocation() {
        var location = new Location(Country.BR, "São Paulo", "Poá");

        when(repository.findById(1L))
                .thenReturn(Optional.of(location));

        var actualView = service.showLocationBy(1L);

        assertNotNull(actualView);

        var expectedView = new LocationView("Brazil", "São Paulo", "Poá");
        assertThat(actualView, is(expectedView));
    }

    @Test
    public void shouldThrowExceptionIfLocationDoesNotExists() {
        assertThrows(
                LocationNotFoundException.class,
                () -> service.showLocationBy(1L)
        );
    }

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
