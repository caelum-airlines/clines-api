package br.com.caelum.clines.shared.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightTest {
    private static final Location DEFAULT_LOCATION = new Location(Country.BR, "SP", "São Paulo");
    private static final Airport DEFAULT_AIRPORT = new Airport("COD", DEFAULT_LOCATION);
    private static final BigDecimal DEFAULT_PRICE = BigDecimal.TEN;
    private static final LocalDate DEFAULT_DATE = LocalDate.of(2020, Month.MAY, 28);
    private static final Waypoint DEPARTURE = new Waypoint(DEFAULT_AIRPORT, LocalDateTime.of(DEFAULT_DATE, LocalTime.of(10, 30)), "22A");
    private static final Waypoint ARRIVAL = new Waypoint(DEFAULT_AIRPORT, LocalDateTime.of(DEFAULT_DATE, LocalTime.of(13, 30)), "12C");
    private static final Aircraft DEFAULT_AIRCRAFT = new Aircraft();
    @Test
    void shouldReturnTheDurationBetweenDepartureTimeAndArrivalTime() {
        var flight = new Flight(DEFAULT_PRICE, DEPARTURE, ARRIVAL, DEFAULT_AIRCRAFT);

        assertEquals(Duration.ofHours(3), flight.getDuration());
    }
}