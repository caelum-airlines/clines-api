package br.com.caelum.clines.shared.exceptions;

public class AirportNotFoundException extends IllegalArgumentException {
    public AirportNotFoundException(String message) {
        super(message);
    }
}
