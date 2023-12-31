package com.fact.finder.controller;

import com.fact.finder.services.FactService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class FactControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FactService factService;

    @Test
    @DisplayName("Deve retornar 200 para solicitação de todos os fatos dentro do banco")
    void getAllFacts() throws Exception {

        var response = mvc.perform(
                MockMvcRequestBuilders.get("/facts/all")
        ).andReturn().getResponse();

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(response.getContentAsString(), "[]");
    }

    @Test
    @DisplayName("Deve retornar 404 para solicitação de fato aleatorio com o banco de dados vazio")
    void shouldReturn404ForRequestWithDatabaseEmpty() throws Exception {
        var response = mvc.perform(
                MockMvcRequestBuilders.get("/facts/random")
        ).andReturn().getResponse();

        System.out.println(response.getContentAsString());

        Assertions.assertEquals(404, response.getStatus());
        Assertions.assertEquals(response.getContentAsString(), "Tabela de fatos está vazia");
    }
}