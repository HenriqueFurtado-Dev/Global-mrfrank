package br.com.fiap.mrfrank.controller;

import br.com.fiap.mrfrank.controller.dto.DispositivoConsumoDTO;
import br.com.fiap.mrfrank.model.DispositivoConsumo;
import br.com.fiap.mrfrank.service.DispositivoConsumoService;
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
@RequestMapping("/dispositivos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Dispositivos de Consumo", description = "Operações relacionadas a dispositivos de consumo")
public class DispositivoConsumoController {
    private final DispositivoConsumoService service;

    // GET: Listar Todos os Dispositivos de Consumo
    @GetMapping
    @Operation(summary = "Listar todos os dispositivos de consumo", description = "Recupera uma lista de todos os dispositivos de consumo cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de dispositivos retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<CollectionModel<EntityModel<DispositivoConsumo>>> findAll() {
        List<EntityModel<DispositivoConsumo>> dispositivos = service.findAll().stream()
                .map(dispositivo -> EntityModel.of(dispositivo,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DispositivoConsumoController.class).findById(dispositivo.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DispositivoConsumoController.class).findAll()).withRel("dispositivos")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<DispositivoConsumo>> collectionModel = CollectionModel.of(dispositivos,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DispositivoConsumoController.class).findAll()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    // GET: Buscar Dispositivo de Consumo por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar dispositivo de consumo por ID", description = "Recupera os detalhes de um dispositivo de consumo específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dispositivo encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Dispositivo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EntityModel<DispositivoConsumo>> findById(
            @Parameter(description = "ID do dispositivo a ser buscado", required = true)
            @PathVariable Long id) {
        DispositivoConsumo dispositivo = service.findById(id);
        EntityModel<DispositivoConsumo> entityModel = EntityModel.of(dispositivo,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DispositivoConsumoController.class).findById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DispositivoConsumoController.class).findAll()).withRel("dispositivos"));

        return ResponseEntity.ok(entityModel);
    }

    // POST: Inserir Dispositivo de Consumo
    @PostMapping
    @Operation(summary = "Inserir novo dispositivo de consumo", description = "Cria um novo dispositivo de consumo com as informações fornecidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dispositivo criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EntityModel<DispositivoConsumo>> save(
            @Parameter(description = "Informações do dispositivo a ser criado", required = true)
            @Validated @RequestBody DispositivoConsumoDTO dto) {
        DispositivoConsumo novoDispositivo = service.save(dto);
        EntityModel<DispositivoConsumo> entityModel = EntityModel.of(novoDispositivo,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DispositivoConsumoController.class).findById(novoDispositivo.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DispositivoConsumoController.class).findAll()).withRel("dispositivos"));

        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }

    // PUT: Atualizar Dispositivo de Consumo
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dispositivo de consumo", description = "Atualiza as informações de um dispositivo de consumo existente identificado pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dispositivo atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Dispositivo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EntityModel<DispositivoConsumo>> update(
            @Parameter(description = "ID do dispositivo a ser atualizado", required = true)
            @PathVariable Long id,
            @Parameter(description = "Novas informações do dispositivo", required = true)
            @RequestBody DispositivoConsumoDTO dto) {
        DispositivoConsumo dispositivoAtualizado = service.update(id, dto);
        EntityModel<DispositivoConsumo> entityModel = EntityModel.of(dispositivoAtualizado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DispositivoConsumoController.class).findById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DispositivoConsumoController.class).findAll()).withRel("dispositivos"));

        return ResponseEntity.ok(entityModel);
    }

    // DELETE: Deletar Dispositivo de Consumo
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar dispositivo de consumo", description = "Remove um dispositivo de consumo existente identificado pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Dispositivo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Dispositivo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ID do dispositivo a ser deletado", required = true)
            @PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
