package com.fedorizvekov.http.server.spring.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {

    private final String endpoint = "/users";

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Should return CREATED and user data")
    @Test
    void shouldReturn_CREATED_andUserData() throws Exception {
        mockMvc.perform(post(endpoint).contentType(APPLICATION_JSON).content("{\"email\": \"test@email.com\", \"name\": \"TestName\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("UserDto(email=test@email.com, firstName=TestName, lastName=null)"));
    }


    @DisplayName("Should return CREATED and full user data")
    @Test
    void shouldReturn_CREATED_andFullUserData() throws Exception {
        mockMvc.perform(post(endpoint).contentType(APPLICATION_JSON)
                        .content("{\"email\": \"test@email.com\", \"firstName\": \"TestFirstName\", \"lastName\": \"TestLastName\", \"unknownParameter\": \"unknownValue\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("UserDto(email=test@email.com, firstName=TestFirstName, lastName=TestLastName)"));
    }


    @DisplayName("Should return BAD_REQUEST with invalid request body")
    @Test
    void shouldReturn_BAD_REQUEST_withInvalidRequestBody() throws Exception {
        mockMvc.perform(post(endpoint).contentType(APPLICATION_JSON).content("Bad request"))
                .andExpect(status().isBadRequest());
    }


    @DisplayName("Should return BAD_REQUEST missing required parameters")
    @Test
    void shouldReturn_BAD_REQUEST_missingRequiredParameters() throws Exception {
        mockMvc.perform(post(endpoint).contentType(APPLICATION_JSON).content("{\"firstName\": \"TestFirstName\"}"))
                .andExpect(status().isBadRequest());
    }

}
