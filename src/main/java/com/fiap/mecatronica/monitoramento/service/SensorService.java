package com.fiap.mecatronica.monitoramento.service;
import com.fiap.mecatronica.monitoramento.model.Sensor;
import com.fiap.mecatronica.monitoramento.repository.SensorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SensorService {
    private final SensorRepository repository;
    public SensorService(SensorRepository repository) {
        this.repository = repository;
    }
    public Sensor salvar(Sensor sensor) {
        if (sensor.getLimiteMinimo() >= sensor.getLimiteMaximo()) {
            throw new RuntimeException("Limite mínimo não pode ser maior ou igual ao limite máximo.");
        }
        return repository.save(sensor);
    }
    public List<Sensor> listarTodos() {
        return repository.findAll();
    }
    public Sensor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));
    }
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}