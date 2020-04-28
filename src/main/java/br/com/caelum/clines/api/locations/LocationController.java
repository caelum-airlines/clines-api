package br.com.caelum.clines.api.locations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.net.URI;

import static org.springframework.http.ResponseEntity.created;

@Controller
public class LocationController {

    private final LocationService service;

    @Autowired
    public LocationController(LocationService service) {
        this.service = service;
    }

    public ResponseEntity<?> createBy(LocationForm form) {
        Long code = service.createLocationBy(form);

        URI uri = URI.create("/location/").resolve(code.toString());

        return created(uri).build();
    }
}
