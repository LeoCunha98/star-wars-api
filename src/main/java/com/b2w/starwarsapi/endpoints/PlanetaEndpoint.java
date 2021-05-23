package com.b2w.starwarsapi.endpoints;

import com.b2w.starwarsapi.domain.Planeta;
import com.b2w.starwarsapi.service.PlanetaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planetas")
public class PlanetaEndpoint {

    private final PlanetaService planetaService;

    public PlanetaEndpoint(PlanetaService planetaService){
        this.planetaService = planetaService;
    }

    @PostMapping
    public Planeta adicionar(@RequestBody Planeta planeta){
        return planetaService.adicionar(planeta);
    }

    @GetMapping("/id/{planetaId}")
    public Planeta buscarPorId(@PathVariable String planetaId){
        return planetaService.buscarPorId(planetaId);
    }

    @GetMapping("/nome/{nome}")
    public Planeta buscarPorNome(@PathVariable String nome){
        return planetaService.buscarPorNome(nome);
    }

    @GetMapping("/")
    public List<Planeta> buscarTodos(){
        return planetaService.buscarTodos();
    }

    @DeleteMapping("/{planetaId}")
    public void deletar(@PathVariable String planetaId){
        planetaService.deletar(planetaId);
    }
}
