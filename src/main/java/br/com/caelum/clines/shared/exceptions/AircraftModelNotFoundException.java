package br.com.caelum.clines.shared.exceptions;

public class AircraftModelNotFoundException extends IllegalArgumentException {
    public AircraftModelNotFoundException(String message) {
        super(message);
    }
}
