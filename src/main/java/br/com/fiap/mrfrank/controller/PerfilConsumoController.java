package br.com.fiap.mrfrank.controller;

import br.com.fiap.mrfrank.controller.dto.PerfilConsumoDTO;
import br.com.fiap.mrfrank.model.PerfilConsumo;
import br.com.fiap.mrfrank.service.PerfilConsumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfis")
@RequiredArgsConstructor
public class PerfilConsumoController {
    private final PerfilConsumoService service;

    @GetMapping
    public List<PerfilConsumo> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PerfilConsumo findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public PerfilConsumo save(@RequestBody PerfilConsumoDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public PerfilConsumo update(@PathVariable Long id, @RequestBody PerfilConsumoDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
