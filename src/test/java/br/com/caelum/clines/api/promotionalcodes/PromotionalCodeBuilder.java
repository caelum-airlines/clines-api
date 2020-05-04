package br.com.caelum.clines.api.promotionalcodes;

import br.com.caelum.clines.shared.domain.PromotionalCode;

import java.time.LocalDate;

public class PromotionalCodeBuilder {
    Long id = null;
    String code = "CODE";
    LocalDate start = LocalDate.now();
    LocalDate expiration = LocalDate.now().plusMonths(1);
    String description = "DESCRIPTION";
    Integer discount = 10;

    public PromotionalCode getDomain() {
        return getDomain(start, expiration);
    }

    public PromotionalCode getDomain(LocalDate start, LocalDate expiration) {
        return getDomain(code, start, expiration);
    }

    public PromotionalCode getDomain(String code) {
        return getDomain(code, start, expiration);
    }

    public PromotionalCode getDomain(String code, LocalDate start, LocalDate expiration) {
        return new PromotionalCode(id, code, start, expiration, description, discount);
    }

    public PromotionalCodeForm getForm() {
        return new PromotionalCodeForm(code, start, expiration, description, discount);
    }

    public PromotionalCodeView getView() {
        return getView(code);
    }

    public PromotionalCodeView getView(String code) {
        return new PromotionalCodeView(code, start, expiration, description, discount);
    }
}
