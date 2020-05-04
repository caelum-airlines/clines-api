package br.com.caelum.clines.api.promotionalcodes;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class PromotionalCodeFormMapperTest {
    @Test
    void map_returnNewPromotionalCode() {
        var form = new PromotionalCodeBuilder().getForm();

        var mapper = new PromotionalCodeFormMapper();

        var promotionalCode = mapper.map(form);

        assertThat(promotionalCode.getCode(), equalTo(form.getCode()));
        assertThat(promotionalCode.getStartDate(), equalTo(form.getStartDate()));
        assertThat(promotionalCode.getExpirationDate(), equalTo(form.getExpirationDate()));
        assertThat(promotionalCode.getDescription(), equalTo(form.getDescription()));
        assertThat(promotionalCode.getDiscount(), equalTo(form.getDiscount()));
    }

    @Test
    void map_returnPromotionalCodeWithId() {
        var form = new PromotionalCodeBuilder().getForm();

        var mapper = new PromotionalCodeFormMapper();

        var promotionalCode = mapper.map(1L, form);

        assertThat(promotionalCode.getId(), equalTo(1L));
        assertThat(promotionalCode.getCode(), equalTo(form.getCode()));
        assertThat(promotionalCode.getStartDate(), equalTo(form.getStartDate()));
        assertThat(promotionalCode.getExpirationDate(), equalTo(form.getExpirationDate()));
        assertThat(promotionalCode.getDescription(), equalTo(form.getDescription()));
        assertThat(promotionalCode.getDiscount(), equalTo(form.getDiscount()));
    }
}
