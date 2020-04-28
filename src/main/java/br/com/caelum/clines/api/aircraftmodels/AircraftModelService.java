package br.com.caelum.clines.api.aircraftmodels;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
