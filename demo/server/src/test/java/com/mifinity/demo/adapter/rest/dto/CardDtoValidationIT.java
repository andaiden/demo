package com.mifinity.demo.adapter.rest.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.runners.Parameterized.Parameter;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(Parameterized.class)
public class CardDtoValidationIT {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    public static final String VALID_CARD_NUMBER = "1111222233334444";

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private MockMvc mockMvc;

    @Parameter(value = 0)
    public String testName;

    @Parameter(value = 1)
    public CardDto cardDto;

    @Parameter(value = 2)
    public int expected;

    @Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"CardDto Success", new CardDto(VALID_CARD_NUMBER, "NameOnCard", "19/02"), 200},
                {"CardDto Null cardNumber", new CardDto(null, "NameOnCard", "19/02"), 400},
                {"CardDto Null nameOnCard", new CardDto(VALID_CARD_NUMBER, null, "19/02"), 400},
                {"CardDto Null expiryDate", new CardDto(VALID_CARD_NUMBER, "NameOnCard", null), 400},
                {"Invalid expiryDate", new CardDto(VALID_CARD_NUMBER, "NameOnCard", "2222/13"), 400},
                {"Invalid month", new CardDto(VALID_CARD_NUMBER, "NameOnCard", "19/13"), 400},
                {"Invalid year", new CardDto(VALID_CARD_NUMBER, "NameOnCard", "0000/12"), 400},
                {"Invalid card number", new CardDto("0000000000000000", "NameOnCard", "0000/12"), 400}
        });
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void test() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/cards").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(cardDto))).andExpect(status().is(expected));
    }
}
