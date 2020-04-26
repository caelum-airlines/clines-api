package br.com.caelum.clines.api.flights;

import br.com.caelum.clines.shared.domain.Aircraft;
import br.com.caelum.clines.shared.domain.Airport;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FlightForm {
    @JsonIgnore
    private Aircraft aircraft;

    @Positive
    @NotNull
    private BigDecimal price;

    @NotBlank
    private String aircraftId;

    @NotNull
    @Valid
    private WaypointForm arrival;

    @NotNull
    @Valid
    private WaypointForm departure;
}

