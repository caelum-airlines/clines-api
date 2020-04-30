package br.com.caelum.clines.api.flights;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(properties = {"DB_NAME=clines_test","spring.jpa.hibernate.ddlAuto:create-drop"})
public class FlightControllerTest {
    @Mock
    private FlightService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnFiltered() throws Exception {
        given(service.listAllFlights()).willReturn(List.of());

        mockMvc.perform(
                get("/flights")
                        .requestAttr("date", "20-10-2020")
                        .requestAttr("airport_code", "GRU")
        ).andExpect(status().isOk());
    }
}
