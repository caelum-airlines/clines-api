package br.com.caelum.clines.api.promotionalcodes;

import br.com.caelum.clines.shared.exceptions.ResourceAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
