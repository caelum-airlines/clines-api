package br.com.caelum.clines.api.locations;

import br.com.caelum.clines.shared.exceptions.ResourceAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LocationService {

    private final LocationRepository repository;
    private final LocationViewMapper viewMapper;
    private final LocationFormMapper formMapper;


    public Long createLocationBy(LocationForm form) {
        repository.findById(form.getId()).ifPresent(location -> {
            throw new ResourceAlreadyExistsException("Location already exists");
        });

        return null;
    }
}
