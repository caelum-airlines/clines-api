package br.com.caelum.clines.api.locations;

import br.com.caelum.clines.api.airports.ExistingLocationService;
import br.com.caelum.clines.shared.domain.Country;
import br.com.caelum.clines.shared.domain.Location;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface LocationRepository extends Repository<Location, Long>, ExistingLocationService {
}
