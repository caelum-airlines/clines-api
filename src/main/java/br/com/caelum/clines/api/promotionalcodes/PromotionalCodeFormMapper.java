package br.com.caelum.clines.api.promotionalcodes;

import br.com.caelum.clines.shared.domain.PromotionalCode;
import br.com.caelum.clines.shared.infra.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PromotionalCodeFormMapper implements Mapper<PromotionalCodeForm, PromotionalCode> {

    public PromotionalCode map(Long id, PromotionalCodeForm form) {
        return new PromotionalCode(
                id,
                form.getCode(),
                form.getStartDate(),
                form.getExpirationDate(),
                form.getDescription(),
                form.getDiscount()
        );
    }

    @Override
    public PromotionalCode map(PromotionalCodeForm form) {
        return map(null, form);
    }
}
