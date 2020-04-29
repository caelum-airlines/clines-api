package br.com.caelum.clines.api.locations;

import br.com.caelum.clines.shared.domain.Country;
import br.com.caelum.clines.shared.domain.Location;
import br.com.caelum.clines.shared.exceptions.LocationNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;


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

        var expectedView = new LocationView(null, "Brazil", "São Paulo", "Poá");
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
    void listAllLocationsShouldReturnAListWithAllLocations() {
        Location location1 = new Location(Country.BR, "SAO PAULO", "SAO PAULO");
        Location location2 = new Location(Country.BR, "SAO PAULO", "CAMPINAS");
        List<Location> locations = List.of(location1, location2);

        given(repository.findAll()).willReturn(locations);

        List<LocationView> locationViews = service.listAllLocations();

        then(repository).should(only()).findAll();
        then(mapper).should(times(2)).map(any(Location.class));

        assertEquals(2, locationViews.size());

        for (int i = 0; i < locationViews.size(); i++) {
            assertEquals(locations.get(i).getId(), locationViews.get(i).getId());
            assertEquals(locations.get(i).getCountry().getDescription(), locationViews.get(i).getCountry());
            assertEquals(locations.get(i).getState(), locationViews.get(i).getState());
            assertEquals(locations.get(i).getCity(), locationViews.get(i).getCity());
        }
    }

    @Test
    void listAllLocationsShouldReturnAnEmptyList() {
        given(repository.findAll()).willReturn(Collections.emptyList());

        List<LocationView> locationViews = service.listAllLocations();

        then(repository).should(only()).findAll();
        then(mapper).should(times(0)).map(any(Location.class));

        assertEquals(0, locationViews.size());
    }
}
