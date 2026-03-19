package com.fiap.mecatronica.monitoramento.dto;

import java.time.LocalDateTime;

import com.fiap.mecatronica.monitoramento.model.Sensor;
import com.fiap.mecatronica.monitoramento.model.StatusMedicao;

public class MedicaoResponse {

    private Long id;
    private Sensor sensor;
    private Double valor;
    private LocalDateTime data;
    private StatusMedicao status;

    public MedicaoResponse() {
    }

    public MedicaoResponse(Long id, Sensor sensor, Double valor, LocalDateTime data, StatusMedicao status) {
        this.id = id;
        this.sensor = sensor;
        this.valor = valor;
        this.data = data;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public StatusMedicao getStatus() {
        return status;
    }

    public void setStatus(StatusMedicao status) {
        this.status = status;
    }
}