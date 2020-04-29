package br.com.caelum.clines.api.aircraftmodels;

import br.com.caelum.clines.shared.exceptions.ResourceAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AircraftModelService {
    private final AircraftModelRepository repository;
    private final AircraftModelFormMapper formMapper;

    public Long createAircraftModelBy(AircraftModelForm form) {
        repository.findByDescription(form.getDescription()).ifPresent(
                (existingAircraftModel) -> {
                    throw new ResourceAlreadyExistsException("Aircraft Model already exists");
                });
        var aircraftModel = formMapper.map(form);

        repository.save(aircraftModel);

        return aircraftModel.getId();
    }
}
