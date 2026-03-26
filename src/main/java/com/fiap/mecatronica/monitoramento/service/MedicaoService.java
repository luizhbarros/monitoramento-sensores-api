package com.fiap.mecatronica.monitoramento.service;

import com.fiap.mecatronica.monitoramento.dto.MedicaoResponse;
import com.fiap.mecatronica.monitoramento.model.Medicao;
import com.fiap.mecatronica.monitoramento.model.Sensor;
import com.fiap.mecatronica.monitoramento.model.StatusMedicao;
import com.fiap.mecatronica.monitoramento.repository.MedicaoRepository;
import com.fiap.mecatronica.monitoramento.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicaoService {

    private final MedicaoRepository medicaoRepository;
    private final SensorRepository sensorRepository;

    public MedicaoService(MedicaoRepository medicaoRepository, SensorRepository sensorRepository) {
        this.medicaoRepository = medicaoRepository;
        this.sensorRepository = sensorRepository;
    }

    /**
     * Registra uma nova medição
     */
    public MedicaoResponse registrarMedicao(Long sensorId, Double valor) {
        Sensor sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));

        if (!sensor.getAtivo()) {
            throw new RuntimeException("Sensor está inativo");
        }

        Medicao medicao = new Medicao(sensor, valor, LocalDateTime.now());
        Medicao medicaoSalva = medicaoRepository.save(medicao);

        return converterParaResponse(medicaoSalva);
    }

    /**
     * Lista todas as medições com status
     */
    public List<MedicaoResponse> listarTodas() {
        return medicaoRepository.findAllByOrderByDataDesc()
                .stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    /**
     * Lista medições de um sensor específico
     */
    public List<MedicaoResponse> listarPorSensor(Long sensorId) {
        return medicaoRepository.findBySensorIdOrderByDataDesc(sensorId)
                .stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    /**
     * Busca uma medição por ID
     */
    public MedicaoResponse buscarPorId(Long id) {
        Medicao medicao = medicaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medição não encontrada"));
        return converterParaResponse(medicao);
    }

    /**
     * Calcula o status da medição baseado nos limites do sensor
     */
    private StatusMedicao calcularStatus(Medicao medicao) {
        Double valor = medicao.getValor();
        Sensor sensor = medicao.getSensor();

        // Verifica se o valor está dentro da faixa crítica (abaixo do mínimo ou acima do máximo)
        if (valor < sensor.getLimiteMinimo() || valor > sensor.getLimiteMaximo()) {
            return StatusMedicao.CRITICO;
        }

        // Calcula a margem de alerta (10% da faixa)
        Double faixa = sensor.getLimiteMaximo() - sensor.getLimiteMinimo();
        Double margemAlerta = faixa * 0.10;

        // Verifica se está próximo dos limites (zona de alerta)
        if (valor < (sensor.getLimiteMinimo() + margemAlerta) ||
                valor > (sensor.getLimiteMaximo() - margemAlerta)) {
            return StatusMedicao.ALERTA;
        }

        return StatusMedicao.NORMAL;
    }

    /**
     * Converte Medicao para MedicaoResponse incluindo o status calculado
     */
    private MedicaoResponse converterParaResponse(Medicao medicao) {
        StatusMedicao status = calcularStatus(medicao);
        return new MedicaoResponse(
                medicao.getId(),
                medicao.getSensor(),
                medicao.getValor(),
                medicao.getData(),
                status
        );
    }

    /**
     * Simula uma nova medição com valor aleatório (útil para testes)
     */
    public MedicaoResponse simularMedicao(Long sensorId) {
        Sensor sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));

        // Gera um valor aleatório dentro da faixa do sensor (com possibilidade de ultrapassar)
        Double faixa = sensor.getLimiteMaximo() - sensor.getLimiteMinimo();
        Double valorAleatorio = sensor.getLimiteMinimo() + (Math.random() * faixa * 1.2);

        return registrarMedicao(sensorId, valorAleatorio);
    }
}