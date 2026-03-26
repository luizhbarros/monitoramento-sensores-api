package com.fiap.mecatronica.monitoramento.repository;
import com.fiap.mecatronica.monitoramento.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SensorRepository extends JpaRepository<Sensor, Long> {
}