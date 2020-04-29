package br.com.caelum.clines.api.locations;

import br.com.caelum.clines.shared.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LocationService {

    private LocationRepository repository;
    private LocationViewMapper viewMapper;

    public List<LocationView> listAllLocations() {
        return repository.findAll().stream()
                .map(viewMapper::map)
                .collect(Collectors.toList());
    }

    public LocationView showLocationBy(Long id) {
        return repository.findById(id)
                .map(viewMapper::map)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cannot find location")
                );
    }
}
