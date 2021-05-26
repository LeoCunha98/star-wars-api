package com.b2w.starwarsapi.service.impl;

import com.b2w.starwarsapi.domain.Planeta;
import com.b2w.starwarsapi.domain.PlanetasFeign;
import com.b2w.starwarsapi.domain.SwapiResponse;
import com.b2w.starwarsapi.exception.DuplicatedNameExcpetion;
import com.b2w.starwarsapi.exception.ResourceNotFoundException;
import com.b2w.starwarsapi.feign.SwapiFeign;
import com.b2w.starwarsapi.repository.PlanetaRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class PlanetaServiceImplTest {

    private final PlanetaRepository planetaRepository = mock(PlanetaRepository.class);

    private final SwapiFeign swapiFeign = mock(SwapiFeign.class);

    @InjectMocks
    private final PlanetaServiceImpl planetaService = spy(new PlanetaServiceImpl(planetaRepository, swapiFeign));

    @Spy
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private Planeta planetaExpected;
    private PlanetasFeign planetaFeign;
    private List<Planeta> planetasExpected;
    private SwapiResponse swapiResponse;

    @BeforeEach
    void setUp() {
        planetaExpected = new Planeta();
        planetaExpected.setNome("Tatooine");
        planetaExpected.setClima(Arrays.asList("Árido", "Temperado"));
        planetaExpected.setTerreno(Arrays.asList("Deserto", "Selvas"));
        planetaExpected.setQtdAparicoes(2);
        planetaExpected.setPlanetaId("8499fc63-dcf9-4b5f-8779-20e39a57f504");

        planetaFeign = new PlanetasFeign();
        planetaFeign.setFilms(Arrays.asList("http://swapi.dev/api/films/1/",
                "http://swapi.dev/api/films/3/",
                "http://swapi.dev/api/films/4/",
                "http://swapi.dev/api/films/5/",
                "http://swapi.dev/api/films/6/"));
        planetaFeign.setName("Tatooine");
        swapiResponse = new SwapiResponse();
        swapiResponse.setResults(Collections.singletonList(planetaFeign));

        planetasExpected = Collections.singletonList(planetaExpected);
    }

    @Test
    void testAdicionarSucesso() {
        when(planetaRepository.buscarPorNome(planetaExpected.getNome())).thenReturn(null);
        when(objectMapper.convertValue(swapiFeign.getPlanets(), SwapiResponse.class)).thenReturn(swapiResponse);
        when(planetaRepository.salvar(planetaExpected)).thenReturn(planetaExpected);
        assertThatCode(() -> planetaService.adicionar(planetaExpected)).doesNotThrowAnyException();
        Planeta response = planetaService.adicionar(planetaExpected);
        assertEquals(response, planetaExpected);
    }

    @Test
    void testAdicionarFalha() {
        when(planetaRepository.buscarPorNome(planetaExpected.getNome())).thenReturn(planetaExpected);
        when(objectMapper.convertValue(swapiFeign.getPlanets(), SwapiResponse.class)).thenReturn(swapiResponse);
        assertThrows(DuplicatedNameExcpetion.class, () -> planetaService.adicionar(planetaExpected));
    }

    @Test
    void testBuscarPorIdSucesso() {
        when(objectMapper.convertValue(swapiFeign.getPlanets(), SwapiResponse.class)).thenReturn(swapiResponse);
        when(planetaRepository.buscarPorId(planetaExpected.getPlanetaId())).thenReturn(planetaExpected);
        assertThatCode(() -> planetaService.buscarPorId(planetaExpected.getPlanetaId())).doesNotThrowAnyException();
        assertEquals(planetaExpected, planetaService.buscarPorId(planetaExpected.getPlanetaId()));
    }

    @Test
    void testBuscarPorIdFalha() {
        when(objectMapper.convertValue(swapiFeign.getPlanets(), SwapiResponse.class)).thenReturn(swapiResponse);
        when(planetaRepository.buscarPorId(planetaExpected.getPlanetaId())).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> planetaService.buscarPorId(planetaExpected.getPlanetaId()));
    }

    @Test
    void buscarPorNome() {
        when(objectMapper.convertValue(swapiFeign.getPlanets(), SwapiResponse.class)).thenReturn(swapiResponse);
        when(planetaRepository.buscarPorNome(planetaExpected.getNome())).thenReturn(planetaExpected);
        assertThatCode(() -> planetaService.buscarPorNome(planetaExpected.getNome())).doesNotThrowAnyException();
        assertEquals(planetaExpected, planetaService.buscarPorNome(planetaExpected.getNome()));
    }

    @Test
    void testBuscarPorNomeFalha() {
        when(objectMapper.convertValue(swapiFeign.getPlanets(), SwapiResponse.class)).thenReturn(swapiResponse);
        when(planetaRepository.buscarPorNome(planetaExpected.getNome())).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> planetaService.buscarPorNome(planetaExpected.getNome()));
    }

    @Test
    void buscarTodos() {
        when(objectMapper.convertValue(swapiFeign.getPlanets(), SwapiResponse.class)).thenReturn(swapiResponse);
        when(planetaRepository.listarTodos()).thenReturn(planetasExpected);
        assertThatCode(planetaService::buscarTodos).doesNotThrowAnyException();
        assertEquals(planetasExpected, planetaService.buscarTodos());
    }

    @Test
    void deletar() {
        planetaService.deletar(planetaExpected.getPlanetaId());
        verify(planetaRepository).deletar(planetaExpected.getPlanetaId());
    }
}