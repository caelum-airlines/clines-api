package br.com.caelum.clines.api.aircraftmodels;

import br.com.caelum.clines.shared.exceptions.ResourceAlreadyExistsException;
import br.com.caelum.clines.shared.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AircraftModelService {
    private final AircraftModelRepository repository;
    private final AircraftModelFormMapper formMapper;
    private final AircraftModelViewMapper viewMapper;

    public Long createAircraftModelBy(AircraftModelForm form) {
        repository.findByDescription(form.getDescription()).ifPresent(
                (existingAircraftModel) -> {
                    throw new ResourceAlreadyExistsException("Aircraft Model already exists");
                });
        var aircraftModel = formMapper.map(form);

        repository.save(aircraftModel);

        return aircraftModel.getId();
    }

    public AircraftModelView showAircraftModelBy(Long aircraftModelId) {
        var aircraftModel = repository.findById(aircraftModelId).orElseThrow(() -> new ResourceNotFoundException("Cannot find aircraft model"));
        return viewMapper.map(aircraftModel);
    }
}
