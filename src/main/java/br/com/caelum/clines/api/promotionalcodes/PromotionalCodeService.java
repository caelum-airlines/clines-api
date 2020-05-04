package br.com.caelum.clines.api.promotionalcodes;

import br.com.caelum.clines.shared.exceptions.ResourceAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PromotionalCodeService {
    private final PromotionalCodeRepository repository;
    private final PromotionalCodeFormMapper formMapper;
    private final PromotionalCodeViewMapper viewMapper;

    public String createPromotionalCodeBy(PromotionalCodeForm form) {
        repository.findByCode(form.getCode()).ifPresent(entity -> {
            throw new ResourceAlreadyExistsException("Promotional code already exists");
        });

        var promotionalCode = formMapper.map(form);

        promotionalCode = repository.save(promotionalCode);

        return promotionalCode.getCode();
    }

    public List<PromotionalCodeView> listAllPromotionalCodes() {
        return repository.findAll().stream()
                .map(viewMapper::map)
                .collect(Collectors.toList());
    }
}
