package br.com.caelum.clines.api.flights;

import br.com.caelum.clines.shared.domain.Flight;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends Repository<Flight, Long> {
    Optional<Flight> findById(Long id);

    List<Flight> findAll();

    void save(Flight flight);
}
