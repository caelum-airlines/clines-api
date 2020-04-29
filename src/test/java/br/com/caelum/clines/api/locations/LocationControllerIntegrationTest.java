package br.com.caelum.clines.api.locations;

import br.com.caelum.clines.shared.domain.Country;
import br.com.caelum.clines.shared.domain.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(properties = {"DB_NAME=clines_test","spring.jpa.hibernate.ddlAuto:create-drop"})
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
class LocationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldReturnLocationById() throws Exception {
        var location = new Location(Country.BR, "SP", "Poá");

        entityManager.persist(location);

        mockMvc.perform(get("/location/" + location.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country", equalTo("Brazil")))
                .andExpect(jsonPath("$.state", equalTo("SP")))
                .andExpect(jsonPath("$.city", equalTo("Poá")));
    }

    @Test
    void shouldReturnNotFoundForNotFoundLocation() throws Exception {
        mockMvc.perform(get("/location/42"))
                .andExpect(status().isNotFound());
    }
}