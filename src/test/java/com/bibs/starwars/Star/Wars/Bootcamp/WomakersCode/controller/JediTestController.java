package com.bibs.starwars.Star.Wars.Bootcamp.WomakersCode.controller;

import com.bibs.starwars.service.JediService;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JediTestController {

    @MockBean
    private JediService jediService;

    @Autowired
    private MockMvc mockMvc;

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}


/*
* O @AutoConfigMockMvc é uma biblioteca do Spring e serve para mockarmos rotas
* */