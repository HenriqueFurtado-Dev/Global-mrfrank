package br.com.fiap.mrfrank.controller;

import br.com.fiap.mrfrank.controller.dto.LogEventoDTO;
import br.com.fiap.mrfrank.model.LogEvento;
import br.com.fiap.mrfrank.service.LogEventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogEventoController {
    private final LogEventoService service;

    @GetMapping
    public List<LogEvento> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public LogEvento findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public LogEvento save(@RequestBody LogEventoDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public LogEvento update(@PathVariable Long id, @RequestBody LogEventoDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
