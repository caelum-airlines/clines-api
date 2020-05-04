package br.com.caelum.clines.api.promotionalcodes;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class PromotionalCodeViewMapperTest {
    @Test
    public void map_returnPromotionalCodeViewProperly() {
        var promotionalCode = new PromotionalCodeBuilder().getDomain();

        var mapper = new PromotionalCodeViewMapper();

        var view = mapper.map(promotionalCode);

        assertThat(view.getCode(), equalTo(promotionalCode.getCode()));
        assertThat(view.getStartDate(), equalTo(promotionalCode.getStartDate()));
        assertThat(view.getExpirationDate(), equalTo(promotionalCode.getExpirationDate()));
        assertThat(view.getDescription(), equalTo(promotionalCode.getDescription()));
        assertThat(view.getDiscount(), equalTo(promotionalCode.getDiscount()));
    }
}
