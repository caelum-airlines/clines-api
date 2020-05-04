package br.com.caelum.clines.api.promotionalcodes;

import br.com.caelum.clines.shared.domain.PromotionalCode;

import java.time.LocalDate;

public class PromotionalCodeBuilder {
    Long id = 1L;
    String code = "CODE";
    LocalDate start = LocalDate.now();
    LocalDate expiration = LocalDate.now().plusMonths(1);

    public PromotionalCode getDomain() {
        return getDomain(start, expiration);
    }

    public PromotionalCode getDomain(LocalDate start, LocalDate expiration) {
        return getDomain(id, code, start, expiration);
    }

    public PromotionalCode getDomain(Long id, String code) {
        return getDomain(id, code, start, expiration);
    }

    public PromotionalCode getDomain(Long id) {
        return getDomain(id, code, start, expiration);
    }

    public PromotionalCode getDomain(Long id, String code, LocalDate start, LocalDate expiration) {
        return new PromotionalCode(id, code, start, expiration, "DESCRIPTION", 10);
    }

    public PromotionalCodeForm getForm() {
        return new PromotionalCodeForm("CODE", start, expiration, "DESCRIPTION", 10);
    }
}
