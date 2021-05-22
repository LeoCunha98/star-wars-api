package com.b2w.starwarsapi.repository;

import com.b2w.starwarsapi.domain.Planeta;

import java.util.List;

public interface PlanetaRepository {
    Planeta salvar(Planeta planeta);
    List<Planeta> listarTodos();
    Planeta buscarPorId(String planetaId);
    void deletar(String planetaId);

}
