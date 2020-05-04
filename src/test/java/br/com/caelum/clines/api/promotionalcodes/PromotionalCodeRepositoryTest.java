package br.com.caelum.clines.api.promotionalcodes;

import br.com.caelum.clines.shared.domain.PromotionalCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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

    private PromotionalCodeBuilder builder = new PromotionalCodeBuilder();

    @Test
    void save_saveNewPromotionalCode() {
        Long id = null;
        var promotionalCode = builder.getDomain(id);

        assertNull(promotionalCode.getId());

        repository.save(promotionalCode);

        assertNotNull(promotionalCode.getId());

        var newObject = entityManager.find(
                PromotionalCode.class,
                promotionalCode.getId()
        );

        assertThat(newObject.getCode(), equalTo(promotionalCode.getCode()));
        assertThat(newObject.getStartDate(), equalTo(promotionalCode.getStartDate()));
        assertThat(newObject.getExpirationDate(), equalTo(promotionalCode.getExpirationDate()));
        assertThat(newObject.getDescription(), equalTo(promotionalCode.getDescription()));
        assertThat(newObject.getDiscount(), equalTo(promotionalCode.getDiscount()));
    }

    @Test
    void findAll_returnListElements() {
        var promotionalCode1 = builder.getDomain(null, "CODE1");
        entityManager.persist(promotionalCode1);

        var promotionalCode2 = builder.getDomain(null, "CODE2");
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

    @Test
    void findAll_returnEmptyList() {
        var list = repository.findAll();

        assertThat(list.size(), equalTo(0));
    }
}
