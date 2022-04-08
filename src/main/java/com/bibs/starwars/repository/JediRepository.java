package com.bibs.starwars.repository;

import com.bibs.starwars.model.Jedi;

import java.util.List;
import java.util.Optional;

// Repository serve como interface
public interface JediRepository {

    Optional<Jedi> findById(int id);

    List<Jedi> findAll();

    boolean update(Jedi jedi);

    Jedi save(Jedi jedi);

    boolean delete(int id);
}
