package br.com.caelum.clines.api.promotionalcodes;

import br.com.caelum.clines.shared.domain.PromotionalCode;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class PromotionalCodeViewMapperTest {
    @Test
    public void map_returnPromotionalCodeViewProperly() {
        var start = LocalDate.now();
        var expiration = LocalDate.now().plusMonths(1);

        var promotionalCode = new PromotionalCode("CODE", start, expiration, "DESCRIPTION", 10);

        var mapper = new PromotionalCodeViewMapper();

        var view = mapper.map(promotionalCode);

        assertThat(view.getCode(), equalTo("CODE"));
        assertThat(view.getStartDate(), equalTo(start));
        assertThat(view.getExpirationDate(), equalTo(expiration));
        assertThat(view.getDescription(), equalTo("DESCRIPTION"));
        assertThat(view.getDiscount(), equalTo(10));
    }
}