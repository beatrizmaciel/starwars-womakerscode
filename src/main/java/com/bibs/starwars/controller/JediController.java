package com.bibs.starwars.controller;

import com.bibs.starwars.model.Jedi;
import com.bibs.starwars.service.JediService;
import org.apache.coyote.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class JediController {

    private static final Logger logger = LogManager.getLogger(JediController.class);

    private final JediService jediService;

    // construtor do jedi service:
    public JediController(JediService jediService) {
        this.jediService = jediService;
    }

    @GetMapping("/jedi/")
    public ResponseEntity<?> getJedi(@PathVariable int id) {
        return jediService.findById(id)
                // mapeia as infos com lambda:
                .map(jedi -> {
                    try {
                        return ResponseEntity
                                .ok()
                                .eTag(Integer.toString(jedi.getVersion()))
                                .location(new URI("/jedi/" + jedi.getId()))
                                .body(jedi);
                    } catch (URISyntaxException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })

                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/jedi") // mapeando a rota
    public ResponseEntity<Jedi> saveJedi(@RequestBody Jedi jedi){
        // para mapear o cenário:
        Jedi newJedi = jediService.save(jedi);

        try{
            return ResponseEntity
                    .created(new URI("/jedi/" + newJedi.getId()))
                    .eTag(Integer.toString(newJedi.getVersion()))
                    .body(newJedi);
        } catch (URISyntaxException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

/* Qual a diferença entre Controller e RestController?
 * O Controller identifica a classe só com uma propriedade de controlar
 * as aplicações. Desse forma, você pode ter endpoints, mas eles não vão
 * ter comportamento de REST (métodos, http responses, etc).
 * O ideal então é usar o RestController para uma API Rest.
 * */