package com.b2w.starwarsapi.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "star.wars.api.planeta")
public class Planeta {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    @Null
    private String planetaId;

    @DynamoDBAttribute
    @NotBlank(message = "O nome do planeta deve ser informado")
    private String nome;

    @DynamoDBAttribute
    @NotEmpty(message = "O clima do planeta deve ser informado")
    private List<String> clima;

    @DynamoDBAttribute
    @NotEmpty(message = "O terreno do planeta deve ser informado")
    private List<String> terreno;

    @Null
    private int qtdAparicoes;

    public String getPlanetaId() {
        return planetaId;
    }

    public void setPlanetaId(String planetaId) {
        this.planetaId = planetaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getClima() {
        return clima;
    }

    public void setClima(List<String> clima) {
        this.clima = clima;
    }

    public List<String> getTerreno() {
        return terreno;
    }

    public void setTerreno(List<String> terreno) {
        this.terreno = terreno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planeta planeta = (Planeta) o;
        return planetaId.equals(planeta.planetaId) && nome.equals(planeta.nome) && clima.equals(planeta.clima) && terreno.equals(planeta.terreno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planetaId, nome, clima, terreno);
    }

    public int getQtdAparicoes() {
        return qtdAparicoes;
    }

    public void setQtdAparicoes(int qtdAparicoes) {
        this.qtdAparicoes = qtdAparicoes;
    }
}
