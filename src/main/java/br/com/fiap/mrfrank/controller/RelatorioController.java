package br.com.fiap.mrfrank.controller;

import br.com.fiap.mrfrank.controller.dto.RelatorioDTO;
import br.com.fiap.mrfrank.model.Relatorio;
import br.com.fiap.mrfrank.service.RelatorioService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    // POST: Inserir Relatório
    @PostMapping
    public ResponseEntity<String> inserirRelatorio(@Validated @RequestBody RelatorioDTO relatorioDTO) {
        relatorioService.inserirRelatorio(relatorioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Relatório inserido com sucesso!");
    }

    // GET: Listar Todos os Relatórios
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Relatorio>>> listarTodos() {
        List<EntityModel<Relatorio>> relatorios = relatorioService.listarTodos().stream()
                .map(relatorio -> EntityModel.of(relatorio,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RelatorioController.class).buscarPorId(relatorio.getIdRelatorio())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RelatorioController.class).listarTodos()).withRel("relatorios")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Relatorio>> collectionModel = CollectionModel.of(relatorios,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RelatorioController.class).listarTodos()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    // GET: Buscar Relatório por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Relatorio>> buscarPorId(@PathVariable Long id) {
        Relatorio relatorio = relatorioService.buscarPorId(id);
        EntityModel<Relatorio> entityModel = EntityModel.of(relatorio,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RelatorioController.class).buscarPorId(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RelatorioController.class).listarTodos()).withRel("relatorios"));

        return ResponseEntity.ok(entityModel);
    }

    // PUT: Atualizar Relatório
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarRelatorio(@PathVariable Long id, @Validated @RequestBody RelatorioDTO relatorioDTO) {
        relatorioService.atualizarRelatorio(id, relatorioDTO);
        return ResponseEntity.ok("Relatório atualizado com sucesso!");
    }

    // DELETE: Deletar Relatório
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarRelatorio(@PathVariable Long id) {
        relatorioService.deletarRelatorio(id);
        return ResponseEntity.ok("Relatório deletado com sucesso!");
    }
}
