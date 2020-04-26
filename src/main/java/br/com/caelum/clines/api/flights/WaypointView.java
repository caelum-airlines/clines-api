package br.com.caelum.clines.api.flights;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class WaypointView {
    private LocalDateTime time;
    private String code;
    private String gate;
}
