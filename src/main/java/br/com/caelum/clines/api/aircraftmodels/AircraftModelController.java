package br.com.caelum.clines.api.aircraftmodels;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("aircraftmodel")
@AllArgsConstructor
public class AircraftModelController {

    private final AircraftModelService service;

    @GetMapping
    List<AircraftModelView> list() {
        return service.listAllAircraftModels();
    }
}

