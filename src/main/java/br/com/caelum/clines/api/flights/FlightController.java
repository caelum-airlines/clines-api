package br.com.caelum.clines.api.flights;

import br.com.caelum.clines.api.locations.LocationView;
import br.com.caelum.clines.shared.domain.Country;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("flights")
@AllArgsConstructor
public class FlightController {

    private final FlightService services;

    @GetMapping("{id}")
    FlightView show(@PathVariable Long id) {
        return services.showFlightBy(id);
    }

    @GetMapping
    List<FlightView> list() {
        return services.listAllFlights();
    }

    @PostMapping
    ResponseEntity<?> register(@RequestBody @Valid FlightForm form) {
        var id = services.createNewFlightBy(form);
        var uri = URI.create("/flights/").resolve(id.toString());

        return created(uri).build();
    }

    @GetMapping("/search")
    ResponseEntity<?> search(@RequestParam(name = "date") String date,
                             @RequestParam("country") String country,
                             @RequestParam("state") String state,
                             @RequestParam("city") String city) {
        LocationView location = new LocationView(country, state, city);
        List<FlightView> fligthts = services.searchBy(parseDate(date), location);
        return ResponseEntity.ok(fligthts);
    }

    private LocalDateTime parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay();
    }
}
