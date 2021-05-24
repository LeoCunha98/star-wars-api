package com.b2w.starwarsapi.endpoints;

import com.b2w.starwarsapi.domain.Planeta;
import com.b2w.starwarsapi.service.PlanetaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/planetas")
public class PlanetaEndpoint {

    private final PlanetaService planetaService;

    public PlanetaEndpoint(PlanetaService planetaService){
        this.planetaService = planetaService;
    }

    @PostMapping
    public ResponseEntity<Planeta> adicionar(@Valid @RequestBody Planeta planeta){
        return ResponseEntity.ok(planetaService.adicionar(planeta));
    }

    @GetMapping("/id/{planetaId}")
    public ResponseEntity<Planeta> buscarPorId(@PathVariable String planetaId){
        return ResponseEntity.ok(planetaService.buscarPorId(planetaId));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Planeta> buscarPorNome(@PathVariable String nome){
        return ResponseEntity.ok(planetaService.buscarPorNome(nome));
    }

    @GetMapping("/")
    public ResponseEntity<List<Planeta>> buscarTodos(){
        return ResponseEntity.ok(planetaService.buscarTodos());
    }

    @DeleteMapping("/{planetaId}")
    public void deletar(@PathVariable String planetaId){
        planetaService.deletar(planetaId);
    }
}
