package br.com.caelum.clines.api.promotionalcodes;

import br.com.caelum.clines.shared.domain.PromotionalCode;
import org.springframework.data.repository.Repository;

public interface PromotionalCodeRepository extends Repository<PromotionalCode, Long> {
    void save(PromotionalCode promotionalCode);
}
