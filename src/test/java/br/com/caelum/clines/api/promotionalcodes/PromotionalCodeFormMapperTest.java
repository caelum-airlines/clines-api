package br.com.caelum.clines.api.promotionalcodes;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class PromotionalCodeFormMapperTest {
    @Test
    void shouldReturnPromotionalCode() {
        var form = new PromotionalCodeBuilder().getForm();

        var mapper = new PromotionalCodeFormMapper();

        var promotionalCode = mapper.map(form);

        assertThat(promotionalCode.getCode(), equalTo(form.getCode()));
        assertThat(promotionalCode.getStartDate(), equalTo(form.getStartDate()));
        assertThat(promotionalCode.getExpirationDate(), equalTo(form.getExpirationDate()));
        assertThat(promotionalCode.getDescription(), equalTo(form.getDescription()));
        assertThat(promotionalCode.getDiscount(), equalTo(form.getDiscount()));
    }
}
