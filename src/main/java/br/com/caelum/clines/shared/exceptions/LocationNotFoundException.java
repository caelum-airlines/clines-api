package br.com.caelum.clines.shared.exceptions;

public class LocationNotFoundException extends IllegalArgumentException {
    public LocationNotFoundException(String message) {
        super(message);
    }
}
