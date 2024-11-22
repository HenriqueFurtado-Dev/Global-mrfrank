package br.com.fiap.mrfrank.controller;

import br.com.fiap.mrfrank.controller.dto.LogEventoDTO;
import br.com.fiap.mrfrank.model.LogEvento;
import br.com.fiap.mrfrank.service.LogEventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Logs de Eventos", description = "Operações relacionadas a logs de eventos")
public class LogEventoController {
    private final LogEventoService service;

    // GET: Listar Todos os Logs de Eventos
    @GetMapping
    @Operation(summary = "Listar todos os logs de eventos", description = "Recupera uma lista de todos os logs de eventos cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de logs retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<CollectionModel<EntityModel<LogEvento>>> findAll() {
        List<EntityModel<LogEvento>> logs = service.findAll().stream()
                .map(log -> EntityModel.of(log,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LogEventoController.class).findById(log.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LogEventoController.class).findAll()).withRel("logs")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<LogEvento>> collectionModel = CollectionModel.of(logs,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LogEventoController.class).findAll()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    // GET: Buscar Log de Evento por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar log de evento por ID", description = "Recupera os detalhes de um log de evento específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Log encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Log não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EntityModel<LogEvento>> findById(
            @Parameter(description = "ID do log a ser buscado", required = true)
            @PathVariable Long id) {
        LogEvento log = service.findById(id);
        EntityModel<LogEvento> entityModel = EntityModel.of(log,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LogEventoController.class).findById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LogEventoController.class).findAll()).withRel("logs"));

        return ResponseEntity.ok(entityModel);
    }

    // POST: Inserir Log de Evento
    @PostMapping
    @Operation(summary = "Inserir novo log de evento", description = "Cria um novo log de evento com as informações fornecidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Log criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EntityModel<LogEvento>> save(
            @Parameter(description = "Informações do log a ser criado", required = true)
            @Validated @RequestBody LogEventoDTO dto) {
        LogEvento novoLog = service.save(dto);
        EntityModel<LogEvento> entityModel = EntityModel.of(novoLog,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LogEventoController.class).findById(novoLog.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LogEventoController.class).findAll()).withRel("logs"));

        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }

    // PUT: Atualizar Log de Evento
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar log de evento", description = "Atualiza as informações de um log de evento existente identificado pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Log atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Log não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EntityModel<LogEvento>> update(
            @Parameter(description = "ID do log a ser atualizado", required = true)
            @PathVariable Long id,
            @Parameter(description = "Novas informações do log", required = true)
            @RequestBody LogEventoDTO dto) {
        LogEvento logAtualizado = service.update(id, dto);
        EntityModel<LogEvento> entityModel = EntityModel.of(logAtualizado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LogEventoController.class).findById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LogEventoController.class).findAll()).withRel("logs"));

        return ResponseEntity.ok(entityModel);
    }

    // DELETE: Deletar Log de Evento
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar log de evento", description = "Remove um log de evento existente identificado pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Log deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Log não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ID do log a ser deletado", required = true)
            @PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
