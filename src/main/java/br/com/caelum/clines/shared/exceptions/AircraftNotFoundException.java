package br.com.caelum.clines.shared.exceptions;

public class AircraftNotFoundException extends IllegalArgumentException {
    public AircraftNotFoundException(String message) {
        super(message);
    }
}
