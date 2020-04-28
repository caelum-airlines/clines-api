package br.com.caelum.clines.api.locations;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("locations")
@AllArgsConstructor
public class LocationController {

    private final LocationService service;

    @GetMapping
    public List<LocationView> list() {
        return service.listAllLocations();
    }
}
