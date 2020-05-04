package br.com.caelum.clines.api.promotionalcodes;

import br.com.caelum.clines.shared.domain.PromotionalCode;
import br.com.caelum.clines.utils.JsonMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@TestPropertySource(properties = {"DB_NAME=clines_test", "spring.jpa.hibernate.ddlAuto:create-drop"})
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
public class PromotionalCodeControllerTest {
    String promotionalCodeJson = "{ \"code\": \"B2CC71\", \"startDate\": \"2020-02-02\", \"expirationDate\": \"2020-03-03\", \"description\": \"Utilize este código promocional para ganhar 10% de desconto\", \"discount\": 10 }";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestEntityManager entityManager;

    private final PromotionalCodeBuilder builder = new PromotionalCodeBuilder();

    private String stringify(Object value) throws JsonProcessingException {
        return JsonMapper.mapper().writeValueAsString(value);
    }

    @Test
    public void shouldReturnHttpStatus201AndHeaderAttributeLocationWhenValidFormIsInformed() throws Exception {
        mockMvc.perform(post("/promotional-code/").contentType(MediaType.APPLICATION_JSON).content(promotionalCodeJson))
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"));
    }

    @Test
    public void shouldReturnHttpStatus409IfCodeAlreadyExists() throws Exception {
        var start = LocalDate.now();
        var expiration = LocalDate.now().plusMonths(1);
        var promotionalCode = new PromotionalCode("B2CC71", start, expiration, "DESCRIPTION", 10);
        entityManager.persist(promotionalCode);

        mockMvc.perform(post("/promotional-code/").contentType(MediaType.APPLICATION_JSON).content(promotionalCodeJson))
                .andExpect(status().isConflict());
    }

    @Test
    public void shouldReturnAListOfPromotionalCode() throws Exception {
        var promotionalCode1 = builder.getDomain();
        var promotionalCode2 = builder.getDomain();

        entityManager.persist(promotionalCode1);
        entityManager.persist(promotionalCode2);

        var promotionalCodeView1 = builder.getView();
        var promotionalCodeView2 = builder.getView();

        var list = List.of(promotionalCodeView1, promotionalCodeView2);

        mockMvc.perform(get("/promotional-code/"))
                .andExpect(status().isOk())
                .andExpect(
                        MockMvcResultMatchers.content()
                                .json(stringify(list))
                );
    }
}
