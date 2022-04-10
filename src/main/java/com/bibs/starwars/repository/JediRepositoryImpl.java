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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// usamos a anotação só para o Repository de classe
@Repository
public class JediRepositoryImpl implements JediRepository {

    private static final Logger logger = LogManager.getLogger(JediService.class);

    // para fazer as queries:
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbInsert;

    public JediRepositoryImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("jedis")
                .usingGeneratedKeyColumns("id"); // selecionar o campo de id
    }

    @Override
    public Optional<Jedi> findById(Integer id) {
        try {
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
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Jedi> findAll() {
        return jdbcTemplate.query("SELECT * FROM jedis",
                (rs, rowNum) -> {
                    Jedi jedi = new Jedi();
                    jedi.setId(rs.getInt("id"));
                    jedi.setName(rs.getString("name"));
                    jedi.setStrength(rs.getInt("strength"));
                    jedi.setVersion(rs.getInt("version"));
                    return jedi;
                });
    }

    @Override
    public boolean update(Jedi jedi) {
        return jdbcTemplate.update("UPDATE jedis SET name = ?, strength = ?, version = ? WHERE id = ?",

                jedi.getName(),
                jedi.getStrength(),
                jedi.getVersion(),
                jedi.getId()) == 1;
    }

    @Override
    public Jedi save(Jedi jedi) {
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put("name", jedi.getName());
        parameters.put("strength", jedi.getStrength());
        parameters.put("version", jedi.getVersion());

        Number newId = simpleJdbInsert.executeAndReturnKey(parameters);

        logger.info("Inserindo jedi na database. A chave gerada é: {}", newId);
        jedi.setId((Integer) newId);

        return jedi;
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM jedis WHERE id = ?", id) == 1;
    }

}

/* Precisamos de construtores para identificar o JDBC Template
 * (usamos como public JediRepositoryImpl(construtores)
 *
 * O DataSource (parte do SQL Javax) serve para fazer queries SQL dentro
 * do JDBC. Biblioteca que serve para construir tabelas.
 */