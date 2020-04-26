package br.com.caelum.clines.shared.exceptions;

public class ResourceAlreadyExistsException extends IllegalArgumentException {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
