package br.com.caelum.clines.api.locations;

import br.com.caelum.clines.shared.exceptions.LocationNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LocationService {

    private LocationRepository repository;
    private LocationViewMapper viewMapper;

    public LocationView showLocationBy(Long id) {
        return repository.findById(id)
                .map(viewMapper::map)
                .orElseThrow(() ->
                        new LocationNotFoundException("Cannot find location")
                );
    }
}
