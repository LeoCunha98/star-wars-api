package com.b2w.starwarsapi.service.impl;

import com.b2w.starwarsapi.domain.Planeta;
import com.b2w.starwarsapi.domain.PlanetasFeign;
import com.b2w.starwarsapi.domain.SwapiResponse;
import com.b2w.starwarsapi.exception.DuplicatedNameExcpetion;
import com.b2w.starwarsapi.exception.ResourceNotFoundException;
import com.b2w.starwarsapi.feign.SwapiFeign;
import com.b2w.starwarsapi.repository.PlanetaRepository;
import com.b2w.starwarsapi.service.PlanetaService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetaServiceImpl implements PlanetaService {

    private final PlanetaRepository planetaRepository;
    private final SwapiFeign swapiFeign;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static SwapiResponse swapiResponse;

    public PlanetaServiceImpl(PlanetaRepository planetaRepository, SwapiFeign swapiFeign){
        this.planetaRepository = planetaRepository;
        this.swapiFeign = swapiFeign;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.swapiResponse = objectMapper.convertValue(swapiFeign.getPlanets(), SwapiResponse.class);
    }

    @Override
    public Planeta adicionar(Planeta planeta) {
        if(planetaRepository.buscarPorNome(planeta.getNome()) != null) {
            throw new DuplicatedNameExcpetion("Já existe um planeta com o nome de - " + planeta.getNome() +
                    " - no Banco de Dados.");
        }
        PlanetasFeign planetaSwapi = isPlanetaSwapi(swapiResponse.getResults(), planeta.getNome());
        if(planetaSwapi != null)
                planeta.setQtdAparicoes(planetaSwapi.getFilms().size());
        return planetaRepository.salvar(planeta);

    }

    @Override
    public Planeta buscarPorId(String planetaId) {
        var planeta = planetaRepository.buscarPorId(planetaId);
        if(planeta != null){
            PlanetasFeign planetaSwapi = isPlanetaSwapi(swapiResponse.getResults(), planeta.getNome());
            if(planetaSwapi != null)
                planeta.setQtdAparicoes(planetaSwapi.getFilms().size());
            return planeta;
        } else {
            throw new ResourceNotFoundException("O planeta de id - " + planetaId + " - não foi encontrado!");
        }

    }

    @Override
    public Planeta buscarPorNome(String nome) {
        var planeta = planetaRepository.buscarPorNome(nome);
        if(planeta != null){
            PlanetasFeign planetaSwapi = isPlanetaSwapi(swapiResponse.getResults(), planeta.getNome());
            if(planetaSwapi != null)
                planeta.setQtdAparicoes(planetaSwapi.getFilms().size());
            return planeta;
        } else {
            throw new ResourceNotFoundException("O planeta - " + nome + " - não foi encontrado!");
        }
    }

    @Override
    public List<Planeta> buscarTodos() {
        List<Planeta> planetas = planetaRepository.listarTodos();

        for (Planeta planeta : planetas) {
            PlanetasFeign planetaSwapi = isPlanetaSwapi(swapiResponse.getResults(), planeta.getNome());
            if(planetaSwapi != null)
                planeta.setQtdAparicoes(planetaSwapi.getFilms().size());
        }

        return planetas;
    }

    @Override
    public void deletar(String planetaId) {
        planetaRepository.deletar(planetaId);
    }

    private PlanetasFeign isPlanetaSwapi(List<PlanetasFeign> planetasSwapi, String nomePlanetaDB){
        return planetasSwapi.stream().filter(p -> p.getName().contentEquals(nomePlanetaDB)).findAny().orElse(null);
    }

}
