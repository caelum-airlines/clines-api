package br.com.caelum.clines.api.aircraftmodels;

import br.com.caelum.clines.shared.domain.AircraftModel;
import br.com.caelum.clines.shared.infra.Mapper;

public class AircraftModelViewMapper implements Mapper<AircraftModel, AircraftModelView> {

    public AircraftModelView map(AircraftModel source) {
        return new AircraftModelView(source.getDescription());
    }
}
