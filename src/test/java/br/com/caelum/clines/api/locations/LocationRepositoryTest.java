package br.com.caelum.clines.api.locations;

import br.com.caelum.clines.shared.domain.Country;
import br.com.caelum.clines.shared.domain.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(properties = "DB_NAME=clines_test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LocationRepositoryTest {
    private static final Location SAO_PAULO = new Location(Country.BR, "SP", "São Paulo");
    private static final Location GUARULHOS = new Location(Country.BR, "SP", "Guarulhos");

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    LocationRepository repository;

    @Test
    void whenFindAllThenReturnAllLocations() {

        entityManager.persist(SAO_PAULO);
        entityManager.persist(GUARULHOS);

        var locations = repository.readAllBy();

        assertThat(locations).hasSize(2);
        assertEquals(SAO_PAULO.getCountry().getDescription(), locations.get(0).getCountry());
        assertEquals(SAO_PAULO.getState(), locations.get(0).getState());
        assertEquals(SAO_PAULO.getCity(), locations.get(0).getCity());
    }

}