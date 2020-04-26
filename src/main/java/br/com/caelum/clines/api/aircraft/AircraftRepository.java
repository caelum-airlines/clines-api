package br.com.caelum.clines.api.aircraft;

import br.com.caelum.clines.shared.domain.Aircraft;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface AircraftRepository extends Repository<Aircraft, String> {
    Optional<Aircraft> findByCode(String code);

    List<Aircraft> findAll();

    void save(Aircraft aircraft);
}
