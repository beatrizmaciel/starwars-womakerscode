package com.bibs.starwars.service;

import com.bibs.starwars.model.Jedi;
import com.bibs.starwars.repository.JediRepositoryImpl;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

// para reconhecer que é um serviço: @Service
@Service
public class JediService {

    // criando classe de logs
    private static final Logger logger = LogManager.getLogger(JediService.class);

    // repository
    private final JediRepositoryImpl jediRepositoryImpl;

    // construtor do repository:
    public JediService(JediRepositoryImpl jediRepositoryImpl) {
        this.jediRepositoryImpl = jediRepositoryImpl;
    }

    public Optional<Jedi> findById(int id){
        logger.info("Find Jedi with id: {}", id);
        return jediRepositoryImpl.findById(id);
    }

    public List<Jedi> findAll(){
        logger.info("Bring all Jedis from the Galaxy");

        return jediRepositoryImpl.findAll();
    }

    public Jedi save(Jedi jedi){
        jedi.setVersion(1);

        logger.info("Update Jedi from system");

        return jediRepositoryImpl.save(jedi);
    }

    public boolean update(Jedi jedi){
        boolean updated = false;

        Jedi savedJedi = this.save(jedi);
        if (savedJedi != null){
            updated = true;
        }

        return updated;
    }

    public boolean delete(int id){
        return true;
    }
}
