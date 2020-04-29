package br.com.caelum.clines.api.aircraftmodels;

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
@RequestMapping("aircraft-model")
@AllArgsConstructor
public class AircraftModelController {
    private final AircraftModelService service;

    @PostMapping
    ResponseEntity<?> createBy(@RequestBody @Valid AircraftModelForm form) {
        var id = service.createAircraftModelBy(form);

        var uri = URI.create("/aircraft-model/").resolve(String.valueOf(id));

        return created(uri).build();
    }
}
