package com.fiap.mecatronica.monitoramento.controller;
import com.fiap.mecatronica.monitoramento.model.Sensor;
import com.fiap.mecatronica.monitoramento.service.SensorService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/sensores")
@CrossOrigin
public class SensorController {
    private final SensorService service;
    public SensorController(SensorService service) {
        this.service = service;
    }
    @PostMapping
    public Sensor criar(@RequestBody Sensor sensor) {
        return service.salvar(sensor);
    }
    @GetMapping
    public List<Sensor> listar() {
        return service.listarTodos();
    }
    @GetMapping("/{id}")
    public Sensor buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}