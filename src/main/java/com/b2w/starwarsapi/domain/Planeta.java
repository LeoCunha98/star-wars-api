package com.b2w.starwarsapi.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "planeta")
public class Planeta {
    private String nome;
    private String clima;
    private String terreno;
    private String qtdAparicoes;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    public String getQtdAparicoes() {
        return qtdAparicoes;
    }

    public void setQtdAparicoes(String qtdAparicoes) {
        this.qtdAparicoes = qtdAparicoes;
    }
}
