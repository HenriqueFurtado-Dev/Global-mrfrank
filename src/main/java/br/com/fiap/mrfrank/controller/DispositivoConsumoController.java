package br.com.fiap.mrfrank.controller;

import br.com.fiap.mrfrank.controller.dto.DispositivoConsumoDTO;
import br.com.fiap.mrfrank.model.DispositivoConsumo;
import br.com.fiap.mrfrank.service.DispositivoConsumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dispositivos")
@RequiredArgsConstructor
public class DispositivoConsumoController {
    private final DispositivoConsumoService service;

    @GetMapping
    public List<DispositivoConsumo> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public DispositivoConsumo findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public DispositivoConsumo save(@RequestBody DispositivoConsumoDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public DispositivoConsumo update(@PathVariable Long id, @RequestBody DispositivoConsumoDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
