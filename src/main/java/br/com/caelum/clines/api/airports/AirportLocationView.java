package br.com.caelum.clines.api.airports;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class AirportLocationView {
    private String country;
    private String state;
    private String city;
}
