package br.com.caelum.clines.api.flights;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
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
    List<FlightView> list(@RequestParam(value = "date", required = false) String date,
                          @RequestParam(value = "airport_code", required = false) String airport_code) {


        return services.listAllFlights();
    }

    @PostMapping
    ResponseEntity<?> register(@RequestBody @Valid FlightForm form) {
        var id = services.createNewFlightBy(form);
        var uri = URI.create("/flights/").resolve(id.toString());

        return created(uri).build();
    }
}
