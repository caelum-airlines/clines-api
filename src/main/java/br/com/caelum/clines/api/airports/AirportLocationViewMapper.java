package br.com.caelum.clines.api.airports;

import br.com.caelum.clines.shared.domain.Location;
import br.com.caelum.clines.shared.infra.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AirportLocationViewMapper implements Mapper<Location, AirportLocationView> {
    @Override
    public AirportLocationView map(Location source) {
        var country = source.getCountry();
        return new AirportLocationView(country.getDescription(), source.getState(), source.getCity());
    }
}
