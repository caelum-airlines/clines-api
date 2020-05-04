package br.com.caelum.clines.api.promotionalcodes;

import br.com.caelum.clines.shared.domain.PromotionalCode;
import br.com.caelum.clines.shared.exceptions.ResourceAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.only;

@ExtendWith(MockitoExtension.class)
public class PromotionalCodeServiceTest {
    @InjectMocks
    private PromotionalCodeService service;

    @Mock
    private PromotionalCodeRepository repository;

    @Spy
    private PromotionalCodeFormMapper formMapper;

    @Spy
    private PromotionalCodeViewMapper viewMapper;

    private PromotionalCodeBuilder builder = new PromotionalCodeBuilder();

    @Test
    void createPromotionalCodeBy_newPromotionalCode() {
        var form = builder.getForm();
        var promotionalCode = builder.getDomain(form.getStartDate(), form.getExpirationDate());

        given(repository.findByCode(any())).willReturn(Optional.empty());
        given(repository.save(any())).willReturn(promotionalCode);

        var code = service.createPromotionalCodeBy(form);

        then(repository).should().save(any());

        assertEquals("CODE", code);
    }

    @Test
    void createPromotionalCodeBy_throwErrorIfAlreadyExists() {
        var form = builder.getForm();
        var promotionalCode = builder.getDomain();

        given(repository.findByCode(any())).willReturn(Optional.of(promotionalCode));

        assertThrows(
                ResourceAlreadyExistsException.class,
                () -> service.createPromotionalCodeBy(form)
        );

        then(repository).should(only()).findByCode(any());
        then(repository).should(never()).save(any());
    }

    @Test
    void listAllPromotionalCodes_listOfPromotionalCode() {
        var promotionalCode1 = builder.getDomain();
        var promotionalCode2 = builder.getDomain();

        given(repository.findAll()).willReturn(List.of(promotionalCode1, promotionalCode2));

        var list = service.listAllPromotionalCodes();
        var view1 = list.get(0);
        var view2 = list.get(1);

        then(repository).should(only()).findAll();
        then(viewMapper).should().map(promotionalCode1);
        then(viewMapper).should().map(promotionalCode2);

        assertEquals(list.size(), 2);

        assertEquals(promotionalCode1.getCode(), view1.getCode());
        assertEquals(promotionalCode1.getDescription(), view1.getDescription());
        assertEquals(promotionalCode1.getDiscount(), view1.getDiscount());
        assertEquals(promotionalCode1.getStartDate(), view1.getStartDate());
        assertEquals(promotionalCode1.getExpirationDate(), view1.getExpirationDate());

        assertEquals(promotionalCode2.getCode(), view2.getCode());
        assertEquals(promotionalCode2.getDescription(), view2.getDescription());
        assertEquals(promotionalCode2.getDiscount(), view2.getDiscount());
        assertEquals(promotionalCode2.getStartDate(), view2.getStartDate());
        assertEquals(promotionalCode2.getExpirationDate(), view2.getExpirationDate());
    }

    @Test
    void updatePromotionalCode_updatePromotionalCode() {
        var promotionalCode = builder.getDomain();
        var form = new PromotionalCodeForm(
                promotionalCode.getCode(),
                LocalDate.now().plusYears(1),
                LocalDate.now().plusYears(2),
                "OUTRA DESCRIPTION",
                50
        );

        given(repository.findByCode(promotionalCode.getCode())).willReturn(Optional.of(promotionalCode));
        given(formMapper.map(promotionalCode.getId(), form)).willReturn(promotionalCode);
        given(repository.save(promotionalCode)).willReturn(promotionalCode);

        var updatedCode = service.updatePromotionalCode(form);

        then(repository).should().findByCode(promotionalCode.getCode());
        then(repository).should().save(promotionalCode);
        then(formMapper).should(only()).map(form);

        assertEquals(updatedCode, form.getCode());
    }
}
