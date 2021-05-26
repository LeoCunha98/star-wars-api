package com.b2w.starwarsapi.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.b2w.starwarsapi.domain.Planeta;
import com.b2w.starwarsapi.repository.PlanetaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PlanetaRepositoryImpl implements PlanetaRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public PlanetaRepositoryImpl (DynamoDBMapper dynamoDBMapper){
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public Planeta salvar(Planeta planeta) {
        dynamoDBMapper.save(planeta);
        return planeta;
    }

    @Override
    public List<Planeta> listarTodos() {
        return dynamoDBMapper.scan(Planeta.class, new DynamoDBScanExpression());
    }

    @Override
    public Planeta buscarPorId(String planetaId) {
        return dynamoDBMapper.load(Planeta.class, planetaId);
    }

    @Override
    public Planeta buscarPorNome(String nome) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":nomeVal", new AttributeValue().withS(nome));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("nome = :nomeVal").withExpressionAttributeValues(eav);

        List<Planeta> scanResult = dynamoDBMapper.scan(Planeta.class, scanExpression);
        return scanResult.stream().findFirst().orElse(null);
    }

    @Override
    public void deletar(String planetaId) {
        var planeta = dynamoDBMapper.load(Planeta.class, planetaId);
        dynamoDBMapper.delete(planeta);
    }
}
