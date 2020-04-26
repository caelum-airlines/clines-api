package br.com.caelum.clines.api.locations;

import br.com.caelum.clines.shared.domain.Country;
import br.com.caelum.clines.shared.domain.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "DB_NAME=clines_test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
class LocationControllerTest {
    private static final Location SAO_PAULO = new Location(Country.BR, "SP", "São Paulo");
    private static final Location GUARULHOS = new Location(Country.BR, "SP", "Guarulhos");

    @Autowired
    MockMvc mvc;

    @Autowired
    TestEntityManager entityManager;

    @Test
    @Transactional
    void shouldReturnAllLocations() throws Exception {
        entityManager.persist(SAO_PAULO);
        entityManager.persist(GUARULHOS);

        mvc.perform(get("/locations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].country", equalTo("Brazil")))
                .andExpect(jsonPath("$[0].state", equalTo("SP")))
                .andExpect(jsonPath("$[0].city", equalTo("São Paulo")))
                .andExpect(jsonPath("$[1].country", equalTo("Brazil")))
                .andExpect(jsonPath("$[1].state", equalTo("SP")))
                .andExpect(jsonPath("$[1].city", equalTo("Guarulhos")));

    }
}