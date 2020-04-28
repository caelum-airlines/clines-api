package br.com.caelum.clines.api.locations;

import br.com.caelum.clines.shared.domain.Country;
import br.com.caelum.clines.shared.domain.Location;
import br.com.caelum.clines.shared.exceptions.LocationNotFoundException;
import br.com.caelum.clines.shared.exceptions.ResourceAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    private final Long locationId = 1L;
    private final String countryDescription = "Brazil";
    private final Country country = Country.findByDescription(countryDescription);
    private final String state = "Rio Grande do Sul";
    private final String city = "Esteio";
    private final Location location = new Location(locationId, country, state, city);
    private final LocationForm locationForm = new LocationForm(countryDescription, state, city);

    @InjectMocks
    private LocationService service;

    @Mock
    private LocationRepository repository;

    @Spy
    private LocationViewMapper mapper;

    @Spy
    private LocationFormMapper formMapper;

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

    @Test
    public void shouldCreateALocation() {
        given(formMapper.map(locationForm)).willReturn(location);
        given(repository.findByCountryAndStateAndCity(country, state, city)).willReturn(Optional.empty());

        var createdLocationId = service.createLocationBy(locationForm);

        then(formMapper).should(only()).map(locationForm);
        then(repository).should().findByCountryAndStateAndCity(country, state, city);
        then(repository).should().save(location);

        assertEquals(locationId, createdLocationId);
    }

    @Test
    public void shouldThrowResourceAlreadyExistsIfLocationAlreadyExists() {
        given(repository.findByCountryAndStateAndCity(country, state, city)).willReturn(Optional.of(location));

        assertThrows(ResourceAlreadyExistsException.class,
                () -> service.createLocationBy(locationForm)
        );
    }
}
