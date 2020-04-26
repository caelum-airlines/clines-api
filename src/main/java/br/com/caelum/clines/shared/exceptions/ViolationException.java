package br.com.caelum.clines.shared.exceptions;

import lombok.Getter;

import java.util.Collection;

@Getter
public class ViolationException extends IllegalArgumentException {
    private final Collection<Exception> violations;

    public ViolationException(Collection<Exception> violations) {
        this.violations = violations;
    }
}
