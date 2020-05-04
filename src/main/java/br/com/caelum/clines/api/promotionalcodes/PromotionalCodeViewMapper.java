package br.com.caelum.clines.api.promotionalcodes;

import br.com.caelum.clines.shared.domain.PromotionalCode;

public class PromotionalCodeViewMapper {
    public PromotionalCodeView map(PromotionalCode promotionalCode) {
        return new PromotionalCodeView(
                promotionalCode.getCode(),
                promotionalCode.getStartDate(),
                promotionalCode.getExpirationDate(),
                promotionalCode.getDescription(),
                promotionalCode.getDiscount()
        );
    }
}
