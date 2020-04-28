package br.com.caelum.clines.api.locations;

import br.com.caelum.clines.api.airports.AirportForm;
import br.com.caelum.clines.api.airports.AirportService;
import br.com.caelum.clines.api.airports.AirportView;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static br.com.caelum.clines.shared.util.StringNormalizer.normalize;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("locations")
@AllArgsConstructor
public class LocationController {

    private final LocationService service;

}
