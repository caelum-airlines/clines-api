package br.com.caelum.clines.api.promotionalcodes;

import br.com.caelum.clines.shared.domain.PromotionalCode;
import br.com.caelum.clines.shared.infra.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PromotionalCodeViewMapper implements Mapper<PromotionalCode, PromotionalCodeView> {
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
