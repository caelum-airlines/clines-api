package br.com.caelum.clines.api.locations;

import br.com.caelum.clines.shared.domain.Country;
import br.com.caelum.clines.shared.domain.Location;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class AircraftLocationViewMapperTest {
    @Test
    public void shouldReturnLocationView() {
        var location = new Location(Country.BR, "São Paulo", "São Paulo");
        LocationViewMapper locationViewMapper = new LocationViewMapper();

        var result = locationViewMapper.map(location);

        assertThat(result.getCountry(), is("Brazil"));
        assertThat(result.getState(), is("São Paulo"));
        assertThat(result.getCity(), is("São Paulo"));
    }
}