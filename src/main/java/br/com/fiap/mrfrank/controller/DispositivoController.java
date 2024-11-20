package br.com.fiap.mrfrank.controller;

import br.com.fiap.mrfrank.model.Dispositivo;
import br.com.fiap.mrfrank.service.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/dispositivos")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    @PostMapping
    public ResponseEntity<Dispositivo> criarDispositivo(@Valid @RequestBody Dispositivo dispositivo) {
        Dispositivo novoDispositivo = dispositivoService.salvarDispositivo(dispositivo);
        return ResponseEntity.ok(novoDispositivo);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Dispositivo>> listarDispositivosPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(dispositivoService.listarDispositivosPorUsuario(usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDispositivo(@PathVariable Long id) {
        dispositivoService.deletarDispositivo(id);
        return ResponseEntity.noContent().build();
    }
}
