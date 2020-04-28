package br.com.caelum.clines.api.locations;

import br.com.caelum.clines.shared.exceptions.ResourceAlreadyExistsException;
import br.com.caelum.clines.shared.exceptions.LocationNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LocationService {

    private final LocationRepository repository;
    private final LocationViewMapper viewMapper;
    private final LocationFormMapper formMapper;


    public Long createLocationBy(LocationForm form) {
        repository.findById(form.getId())
                .ifPresent(location -> {
                    throw new ResourceAlreadyExistsException("Location already exists");
                });

        return null;
    }

    public LocationView showLocationBy(Long id) {
        return repository.findById(id)
                .map(viewMapper::map)
                .orElseThrow(() ->
                        new LocationNotFoundException("Cannot find location")
                );
    }
}
