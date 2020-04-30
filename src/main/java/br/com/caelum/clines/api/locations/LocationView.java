package br.com.caelum.clines.api.locations;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class LocationView {
    private Long id;
    private String country;

    private String state;

    private String city;
}
