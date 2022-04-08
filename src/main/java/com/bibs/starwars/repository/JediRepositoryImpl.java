package com.bibs.starwars.repository;

import com.bibs.starwars.model.Jedi;
import com.bibs.starwars.service.JediService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

// usamos a anotação só para o Repository de classe
@Repository
public class JediRepositoryImpl implements JediRepository {

    private static final Logger logger = LogManager.getLogger(JediService.class);

    // para fazer as queries:
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbInsert;

    public JediRepositoryImpl(JdbcTemplate jdbcTemplate, DataSource dataSource){
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("jedis")
                .usingGeneratedKeyColumns("id"); // selecionar o campo de id
    }

    @Override
    public Optional<Jedi> findById(int id) {
        try{
            Jedi jedi = jdbcTemplate.queryForObject("SELECT * FROM jedis WHERE id = ?",
                    new Object[]{id},
                    (rs, rowNum) -> {
                        Jedi p = new Jedi();
                        p.setId(rs.getInt("id"));
                        p.setName(rs.getString("name"));
                        p.setStrength(rs.getInt("strength"));
                        p.setVersion(rs.getInt("version"));
                        return p;
                    });

                    return Optional.of(jedi);
        } catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Jedi> findAll() {
        return null;
    }

    @Override
    public boolean update(Jedi jedi) {
        return false;
    }

    @Override
    public Jedi save(Jedi jedi) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

}

/* Precisamos de construtores para identificar o JDBC Template
* (usamos como public JediRepositoryImpl(construtores)
*
* O DataSource (parte do SQL Javax) serve para fazer queries SQL dentro
* do JDBC. Biblioteca que serve para construir tabelas.
*/