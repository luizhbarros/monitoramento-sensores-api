package com.fiap.mecatronica.monitoramento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.mecatronica.monitoramento.model.Medicao;

public interface MedicaoRepository extends JpaRepository<Medicao, Long> {

    List<Medicao> findBySensorIdOrderByDataDesc(Long sensorId);

    List<Medicao> findAllByOrderByDataDesc();
}