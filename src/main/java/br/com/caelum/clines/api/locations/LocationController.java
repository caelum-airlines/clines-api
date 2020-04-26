package br.com.caelum.clines.api.locations;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("locations")
@AllArgsConstructor
public class LocationController {
    private final LocationRepository repository;

    @GetMapping
    List<LocationView> list() {
        return repository.readAllBy();
    }
}
