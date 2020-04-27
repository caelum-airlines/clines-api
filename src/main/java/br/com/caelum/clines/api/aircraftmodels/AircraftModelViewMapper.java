package br.com.caelum.clines.api.aircraftmodels;

import br.com.caelum.clines.shared.domain.AircraftModel;
import br.com.caelum.clines.shared.infra.Mapper;

public class AircraftModelViewMapper implements Mapper<AircraftModel, AircraftModelView> {

    public AircraftModelView map(AircraftModel source) {
//        var model = source.getModel();
//        return new AircraftModelView(source.getCode(), model);
        return new AircraftModelView();
    }
}
