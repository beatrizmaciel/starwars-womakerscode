package com.bibs.starwars.Star.Wars.Bootcamp.WomakersCode.service;

import com.bibs.starwars.model.Jedi;
import com.bibs.starwars.repository.JediRepositoryImpl;
import com.bibs.starwars.service.JediService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JediTestService {

    @Autowired
    private JediService jediService;

    @MockBean
    private JediRepositoryImpl jediRepository;

    @Test
    @DisplayName("Should return Jedi with success")
    public void testFoundSuccessfully(){
        Jedi mockJedi = new Jedi(1, "Obi-Wan Kenobi", 100, 1);

    }
}

/*
* O JupiterAPI (JUnit5) tem métodos auxiliares para asserts e mocks
* O @AutoWired injeta dependências no construtor.
* * O MockBean ajuda a mockar resultados e comportamentos
* O @DisplayName aparece a mensagem no terminal
*/