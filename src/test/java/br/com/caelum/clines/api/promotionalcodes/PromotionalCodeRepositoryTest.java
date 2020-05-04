package br.com.caelum.clines.api.promotionalcodes;

import br.com.caelum.clines.shared.domain.PromotionalCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@TestPropertySource(properties = {"DB_NAME=clines_test", "spring.jpa.hibernate.ddlAuto:create-drop"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class PromotionalCodeRepositoryTest {
    @Autowired
    private PromotionalCodeRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void save_saveNewPromotionalCode() {
        var start = LocalDate.now();
        var expiration = LocalDate.now().plusMonths(1);

        var promotionalCode = new PromotionalCode("CODE", start, expiration, "DESCRIPTION", 10);

        assertNull(promotionalCode.getId());

        repository.save(promotionalCode);

        assertNotNull(promotionalCode.getId());

        var newObject = entityManager.find(
                PromotionalCode.class,
                promotionalCode.getId()
        );

        assertThat(newObject.getCode(), equalTo("CODE"));
        assertThat(newObject.getStartDate(), equalTo(start));
        assertThat(newObject.getExpirationDate(), equalTo(expiration));
        assertThat(newObject.getDescription(), equalTo("DESCRIPTION"));
        assertThat(newObject.getDiscount(), equalTo(10));
    }

    @Test
    void findAll_returnListElements() {
        var promotionalCode1 = getPromotionalCode("CODE1");
        entityManager.persist(promotionalCode1);

        var promotionalCode2 = getPromotionalCode("CODE2");
        entityManager.persist(promotionalCode2);

        var list = repository.findAll();

        assertThat(list.size(), equalTo(2));

        var firstItem = list.get(0);
        assertThat(firstItem.getCode(), equalTo("CODE1"));
        assertThat(firstItem.getStartDate(), equalTo(promotionalCode1.getStartDate()));
        assertThat(firstItem.getExpirationDate(), equalTo(promotionalCode1.getExpirationDate()));
        assertThat(firstItem.getDescription(), equalTo("DESCRIPTION"));
        assertThat(firstItem.getDiscount(), equalTo(10));

        var secondItem = list.get(1);
        assertThat(secondItem.getCode(), equalTo("CODE2"));
        assertThat(secondItem.getStartDate(), equalTo(promotionalCode2.getStartDate()));
        assertThat(secondItem.getExpirationDate(), equalTo(promotionalCode2.getExpirationDate()));
        assertThat(secondItem.getDescription(), equalTo("DESCRIPTION"));
        assertThat(secondItem.getDiscount(), equalTo(10));
    }

    private PromotionalCode getPromotionalCode(String code) {
        var calendar = Calendar.getInstance();
        var start = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        var expiration = calendar.getTime();

        return new PromotionalCode(code, start, expiration, "DESCRIPTION", 10);
    }

    @Test
    void findAll_returnEmptyList() {
        var list = repository.findAll();

        assertThat(list.size(), equalTo(0));
    }
}
