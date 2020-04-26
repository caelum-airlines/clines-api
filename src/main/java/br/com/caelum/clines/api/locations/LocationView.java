package br.com.caelum.clines.api.locations;

import org.springframework.beans.factory.annotation.Value;

public interface LocationView {
    Long getId();
    @Value("#{target.country.description}")
    String getCountry();
    String getState();
    String getCity();
}
