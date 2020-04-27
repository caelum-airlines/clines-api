package br.com.caelum.clines.api.aircraftmodels;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AircraftModelService {
    private final AircraftModelRepository repository;
    private final AircraftModelFormMapper formMapper;

    public Long createAircraftModelBy(AircraftModelForm form) {
        var aircraftModel = formMapper.map(form);

        repository.save(aircraftModel);

        return aircraftModel.getId();
    }
}
