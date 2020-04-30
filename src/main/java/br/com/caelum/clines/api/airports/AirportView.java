package br.com.caelum.clines.api.airports;

import br.com.caelum.clines.api.locations.LocationView;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AirportView {
    private String code;
    private AircraftLocationView location;
}
