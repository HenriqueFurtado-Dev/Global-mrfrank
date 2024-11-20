package br.com.fiap.mrfrank.controller;

import br.com.fiap.mrfrank.model.ConsumoEnergia;
import br.com.fiap.mrfrank.service.ConsumoEnergiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/consumo-energia")
public class ConsumoEnergiaController {

    @Autowired
    private ConsumoEnergiaService consumoEnergiaService;

    @PostMapping
    public ResponseEntity<ConsumoEnergia> criarConsumo(@Valid @RequestBody ConsumoEnergia consumoEnergia) {
        ConsumoEnergia novoConsumo = consumoEnergiaService.salvarConsumo(consumoEnergia);
        return ResponseEntity.ok(novoConsumo);
    }

    @GetMapping("/dispositivo/{dispositivoId}")
    public ResponseEntity<List<ConsumoEnergia>> listarConsumoPorDispositivo(@PathVariable Long dispositivoId) {
        return ResponseEntity.ok(consumoEnergiaService.listarConsumoPorDispositivo(dispositivoId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConsumo(@PathVariable Long id) {
        consumoEnergiaService.deletarConsumo(id);
        return ResponseEntity.noContent().build();
    }
}
