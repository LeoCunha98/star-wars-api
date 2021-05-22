package com.b2w.starwarsapi.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.b2w.starwarsapi.domain.Planeta;
import com.b2w.starwarsapi.repository.PlanetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PlanetaRepositoryImpl implements PlanetaRepository {

    @Autowired
    DynamoDBMapper dynamoDBMapper;

    @Override
    public Planeta salvar(Planeta planeta) {
        dynamoDBMapper.save(planeta);
        return planeta;
    }

    @Override
    public List<Planeta> listarTodos() {
        return dynamoDBMapper.query(Planeta.class, new DynamoDBQueryExpression<Planeta>());
    }

    @Override
    public Planeta buscarPorId(String planetaId) {
        return dynamoDBMapper.load(Planeta.class, planetaId);
    }

    @Override
    public void deletar(String planetaId) {
        Planeta planeta = dynamoDBMapper.load(Planeta.class, planetaId);
        dynamoDBMapper.delete(planeta);
    }
}
