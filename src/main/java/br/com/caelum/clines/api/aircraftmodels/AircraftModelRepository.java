package br.com.caelum.clines.api.aircraftmodels;

import br.com.caelum.clines.api.aircraft.ExistingAircraftModelService;
import br.com.caelum.clines.shared.domain.AircraftModel;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface AircraftModelRepository extends Repository<AircraftModel, Long>, ExistingAircraftModelService {
    List<AircraftModel> findAll();
}
