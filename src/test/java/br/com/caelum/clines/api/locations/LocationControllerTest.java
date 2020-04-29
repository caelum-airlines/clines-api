package br.com.caelum.clines.api.locations;

import br.com.caelum.clines.shared.domain.Country;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(LocationController.class)
public class LocationControllerTest {

    @MockBean
    private LocationService locationService;

    @Autowired
    private MockMvc mvc;

    private Gson gson = new Gson();

    @Test
    public void listAllLocationsShouldReturnListOfLocations() throws Exception {
        LocationView location1 = new LocationView(1L, Country.BR.name(), "SAO PAULO", "SAO PAULO");
        LocationView location2 = new LocationView(2L, Country.BR.name(), "SAO PAULO", "CAMPINAS");
        List<LocationView> locations = List.of(location2, location1);

        given(locationService.listAllLocations()).willReturn(locations);

        mvc.perform(MockMvcRequestBuilders.get("/locations").accept(MediaType.APPLICATION_JSON)).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json(gson.toJson(locations)));
    }

    @Test
    public void listAllLocationsShouldReturnAnEmptyList() throws Exception {
        List<LocationView> locations = Collections.emptyList();
        given(locationService.listAllLocations()).willReturn(locations);

        mvc.perform(MockMvcRequestBuilders.get("/locations").accept(MediaType.APPLICATION_JSON)).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json(gson.toJson(locations)));
    }
}
