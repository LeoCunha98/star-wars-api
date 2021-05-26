package com.b2w.starwarsapi.service;


import com.b2w.starwarsapi.domain.Planeta;

import java.util.List;

public interface PlanetaService {
    Planeta adicionar(Planeta planeta);
    Planeta buscarPorId(String planetaId);
    Planeta buscarPorNome(String nome);
    List<Planeta> buscarTodos();
    void deletar(String planetaId);

}
