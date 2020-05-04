package br.com.caelum.clines.api.flights;

import br.com.caelum.clines.api.locations.LocationView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(MockitoJUnitRunner.class)
class FlightControllerTest {
    @Mock
    private FlightService flightService;


    @InjectMocks
    private FlightController flightController;

    private MockMvc mockMvc;

    private final String DEFAULT_DATE = "01-05-2020";
    private final LocalDateTime DEFAULT_DATE_TIME = LocalDate.parse(DEFAULT_DATE, DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay();
    private final String DEFAULT_COUNTRY = "BR";
    private final String DEFAULT_STATE = "RJ";
    private final String DEFAULT_CITY = "Rio de Janeiro";
    private final LocationView DEFAULT_LOCATION = new LocationView(DEFAULT_COUNTRY, DEFAULT_STATE, DEFAULT_CITY);

    @BeforeEach
    public void setup() {
        initMocks(this);
        mockMvc = standaloneSetup(flightController).build();
    }


    @Test
    void shouldReturnEmptyWhenNotFoundFlight() throws Exception {
        given(flightService.searchBy(DEFAULT_DATE_TIME, DEFAULT_LOCATION)).willReturn(Collections.emptyList());
        mockMvc.perform(get("/flights/search")
                .param("date", DEFAULT_DATE)
                .param("country", DEFAULT_COUNTRY)
                .param("state", DEFAULT_STATE)
                .param("city", DEFAULT_CITY))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }
}