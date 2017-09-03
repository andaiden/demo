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
public class UserDtoValidationIT {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private MockMvc mockMvc;

    @Parameter(value = 0)
    public String testName;

    @Parameter(value = 1)
    public UserDto userDto;

    @Parameter(value = 2)
    public int expected;

    @Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"UserDto Success", new UserDto("username", "password"), 200},
                {"UserDto Null username", new UserDto(null, "password"), 400},
                {"UserDto Null password", new UserDto("username", null), 400},
                {"Username already exists", new UserDto("admin", "password"), 400}
        });
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void test() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(userDto))).andExpect(status().is(expected));
    }
}
