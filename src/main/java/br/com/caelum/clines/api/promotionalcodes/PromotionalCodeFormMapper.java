package br.com.caelum.clines.api.promotionalcodes;

import br.com.caelum.clines.shared.domain.PromotionalCode;

public class PromotionalCodeFormMapper {
    public PromotionalCode map(PromotionalCodeForm form) {
        return new PromotionalCode(
                form.getCode(),
                form.getStartDate(),
                form.getExpirationDate(),
                form.getDescription(),
                form.getDiscount()
        );
    }
}
