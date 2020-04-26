package br.com.caelum.clines.api.aircraftmodels;

import br.com.caelum.clines.shared.domain.AircraftModel;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("aircraft-models")
public class AircraftModelController {

    private final AircraftModelRepository repository;

    @GetMapping
    List<AircraftModel> list() {
        return repository.findAll();
    }
}
