package com.bibs.starwars.Star.Wars.Bootcamp.WomakersCode.service;

import com.bibs.starwars.model.Jedi;
import com.bibs.starwars.repository.JediRepositoryImpl;
import com.bibs.starwars.service.JediService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

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

        // cenario
        Jedi mockJedi = new Jedi(1, "Obi-Wan Kenobi", 100, 1);
        Mockito.doReturn(Optional.of(mockJedi)).when(jediRepository).findById(1);

        // execução
        Optional<Jedi> returnedJedi = jediService.findById(1);

        // assert
        Assertions.assertTrue(returnedJedi.isPresent(), "Jedi was not found");
        Assertions.assertSame(returnedJedi.get(), mockJedi, "Jedis must be the same");
    }

    //TODO: Criar teste de erro NOT FOUND

    //TODO: Criar um teste pro findAll
}

/*
* O JupiterAPI (JUnit5) tem métodos auxiliares para asserts e mocks
* O @AutoWired injeta dependências no construtor.
* * O MockBean ajuda a mockar resultados e comportamentos
* O @DisplayName aparece a mensagem no terminal
*/