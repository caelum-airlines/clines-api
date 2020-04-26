package br.com.caelum.clines.api.airports;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocationView {
    private String country;
    private String state;
    private String city;
}
