package com.b2w.starwarsapi.service.impl;

import com.b2w.starwarsapi.domain.Planeta;
import com.b2w.starwarsapi.exception.DuplicatedNameExcpetion;
import com.b2w.starwarsapi.repository.PlanetaRepository;
import com.b2w.starwarsapi.service.PlanetaService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
public class PlanetaServiceImpl implements PlanetaService {

    private final PlanetaRepository planetaRepository;

    public PlanetaServiceImpl(PlanetaRepository planetaRepository){
        this.planetaRepository = planetaRepository;
    }

    @Override
    public Planeta adicionar(Planeta planeta) {
        if(planetaRepository.buscarPorNome(planeta.getNome()) != null) {
            throw new DuplicatedNameExcpetion("Já existe um planeta com o nome de - " + planeta.getNome() + " - no Banco de Dados.");
        }
        return planetaRepository.salvar(planeta);
    }

    @Override
    public Planeta buscarPorId(String planetaId) {
        var planeta = planetaRepository.buscarPorId(planetaId);
        if(planeta == null){
            throw new ResourceAccessException("O planeta de id - " + planetaId + " - não foi encontrado!");
        }
        return planeta;
    }

    @Override
    public Planeta buscarPorNome(String nome) {
        var planeta = planetaRepository.buscarPorNome(nome);
        if(planeta == null){
            throw new ResourceAccessException("O planeta - " + nome + " - não foi encontrado!");
        }
        return planeta;
    }

    @Override
    public List<Planeta> buscarTodos() {
        return planetaRepository.listarTodos();
    }

    @Override
    public void deletar(String planetaId) {
        planetaRepository.deletar(planetaId);
    }
}
