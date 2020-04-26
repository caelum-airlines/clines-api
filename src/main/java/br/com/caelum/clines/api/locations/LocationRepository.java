package br.com.caelum.clines.api.locations;

import br.com.caelum.clines.shared.domain.Location;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface LocationRepository extends Repository<Location, Long> {
    List<LocationView> readAllBy();
}
