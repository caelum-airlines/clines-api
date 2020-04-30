package br.com.caelum.clines.api.promotionalcodes;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class PromotionalCodeForm {
    private String code;
    private Date startDate;
    private Date expirationDate;
    private String description;
    private Integer discount;
}
