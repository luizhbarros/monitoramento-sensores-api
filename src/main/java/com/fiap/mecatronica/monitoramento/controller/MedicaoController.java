package com.fiap.mecatronica.monitoramento.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.mecatronica.monitoramento.dto.MedicaoResponse;
import com.fiap.mecatronica.monitoramento.service.MedicaoService;

@RestController
@RequestMapping("/medicoes")
@CrossOrigin
public class MedicaoController {

    private final MedicaoService service;

    public MedicaoController(MedicaoService service) {
        this.service = service;
    }

    /**
     * Registra uma nova medição
     * POST /medicoes
     * Body: { "sensorId": 1, "valor": 75.5 }
     */
    @PostMapping
    public MedicaoResponse registrar(@RequestBody Map<String, Object> dados) {
        Long sensorId = Long.valueOf(dados.get("sensorId").toString());
        Double valor = Double.valueOf(dados.get("valor").toString());
        return service.registrarMedicao(sensorId, valor);
    }

    /**
     * Lista todas as medições com status calculado
     * GET /medicoes
     */
    @GetMapping
    public List<MedicaoResponse> listar() {
        return service.listarTodas();
    }

    /**
     * Busca uma medição específica por ID
     * GET /medicoes/{id}
     */
    @GetMapping("/{id}")
    public MedicaoResponse buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    /**
     * Lista medições de um sensor específico
     * GET /medicoes/sensor/{sensorId}
     */
    @GetMapping("/sensor/{sensorId}")
    public List<MedicaoResponse> listarPorSensor(@PathVariable Long sensorId) {
        return service.listarPorSensor(sensorId);
    }

    /**
     * Simula uma medição aleatória (útil para testes)
     * POST /medicoes/simular/{sensorId}
     */
    @PostMapping("/simular/{sensorId}")
    public MedicaoResponse simular(@PathVariable Long sensorId) {
        return service.simularMedicao(sensorId);
    }
}