package br.com.caelum.clines.api.aircraftmodels;

import br.com.caelum.clines.api.aircraft.*;
import br.com.caelum.clines.shared.domain.AircraftModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class AircraftModelService {

    private final AircraftModelRepository repository;
    private final AircraftModelViewMapper viewMapper;

    public List<AircraftModelView> listAllAircraftModels() {
        return repository.findAll().stream().map(viewMapper::map).collect(toList());
    }

}
