package br.com.caelum.clines.api.locations;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("location")
@AllArgsConstructor
public class LocationController {

    private final LocationService service;

    @PostMapping
    public ResponseEntity<?> createBy(@RequestBody @Valid LocationForm form) {
        Long code = service.createLocationBy(form);

        URI uri = URI.create("/location/")
                .resolve(code.toString());

        return created(uri).build();
    }
}
