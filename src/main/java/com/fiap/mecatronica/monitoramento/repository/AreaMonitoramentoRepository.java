package com.fiap.mecatronica.monitoramento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.mecatronica.monitoramento.model.AreaMonitoramento;
import com.fiap.mecatronica.monitoramento.model.StatusVegetacao;

@Repository
public interface AreaMonitoramentoRepository extends JpaRepository<AreaMonitoramento, Long> {

    Optional<AreaMonitoramento> findByCodigo(String codigo);

    List<AreaMonitoramento> findByStatus(StatusVegetacao status);

    List<AreaMonitoramento> findByRodoviaContainingIgnoreCase(String rodovia);

    List<AreaMonitoramento> findByStatusOrderByAtualizadoEmDesc(StatusVegetacao status);

    List<AreaMonitoramento> findAllByOrderByStatusDesc();
}
