package br.com.caelum.clines.api.locations;

import br.com.caelum.clines.shared.domain.Country;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
@Getter
public class LocationForm {
    private Long id;

    @NotBlank
    private String state;

    @NotBlank
    private String city;

    @NotBlank
    private String country;
}
