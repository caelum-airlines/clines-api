package br.com.caelum.clines.api.airports;


import br.com.caelum.clines.shared.domain.Location;

import java.util.Optional;

public interface ExistingLocationService {
    Optional<Location> findById(Long locationId);
}
