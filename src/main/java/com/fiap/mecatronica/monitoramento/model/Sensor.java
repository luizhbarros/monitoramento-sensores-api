package com.fiap.mecatronica.monitoramento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sensores")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipo;
    private String unidade;
    private Double limiteMinimo;
    private Double limiteMaximo;
    private Boolean ativo;

    public Sensor() {
    }

    public Sensor(String nome, String tipo, String unidade, Double limiteMinimo, Double limiteMaximo, Boolean ativo) {
        this.nome = nome;
        this.tipo = tipo;
        this.unidade = unidade;
        this.limiteMinimo = limiteMinimo;
        this.limiteMaximo = limiteMaximo;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Double getLimiteMinimo() {
        return limiteMinimo;
    }

    public void setLimiteMinimo(Double limiteMinimo) {
        this.limiteMinimo = limiteMinimo;
    }

    public Double getLimiteMaximo() {
        return limiteMaximo;
    }

    public void setLimiteMaximo(Double limiteMaximo) {
        this.limiteMaximo = limiteMaximo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}