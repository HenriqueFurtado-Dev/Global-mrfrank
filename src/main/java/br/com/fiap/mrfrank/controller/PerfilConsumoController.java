package br.com.fiap.mrfrank.controller;

import br.com.fiap.mrfrank.controller.dto.PerfilConsumoDTO;
import br.com.fiap.mrfrank.model.PerfilConsumo;
import br.com.fiap.mrfrank.service.PerfilConsumoService;
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
@RequestMapping("/perfis")
@RequiredArgsConstructor
@Tag(name = "Perfis de Consumo", description = "Operações relacionadas a perfis de consumo")
public class PerfilConsumoController {
    private final PerfilConsumoService service;

    // GET: Listar Todos os Perfis de Consumo
    @GetMapping
    @Operation(summary = "Listar todos os perfis de consumo", description = "Recupera uma lista de todos os perfis de consumo cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de perfis retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<CollectionModel<EntityModel<PerfilConsumo>>> findAll() {
        List<EntityModel<PerfilConsumo>> perfis = service.findAll().stream()
                .map(perfil -> EntityModel.of(perfil,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerfilConsumoController.class).findById(perfil.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerfilConsumoController.class).findAll()).withRel("perfis")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<PerfilConsumo>> collectionModel = CollectionModel.of(perfis,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerfilConsumoController.class).findAll()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    // GET: Buscar Perfil de Consumo por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar perfil de consumo por ID", description = "Recupera os detalhes de um perfil de consumo específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Perfil não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EntityModel<PerfilConsumo>> findById(
            @Parameter(description = "ID do perfil a ser buscado", required = true)
            @PathVariable Long id) {
        PerfilConsumo perfil = service.findById(id);
        EntityModel<PerfilConsumo> entityModel = EntityModel.of(perfil,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerfilConsumoController.class).findById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerfilConsumoController.class).findAll()).withRel("perfis"));

        return ResponseEntity.ok(entityModel);
    }

    // POST: Inserir Perfil de Consumo
    @PostMapping
    @Operation(summary = "Inserir novo perfil de consumo", description = "Cria um novo perfil de consumo com as informações fornecidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Perfil criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EntityModel<PerfilConsumo>> save(
            @Parameter(description = "Informações do perfil a ser criado", required = true)
            @Validated @RequestBody PerfilConsumoDTO dto) {
        PerfilConsumo novoPerfil = service.save(dto);
        EntityModel<PerfilConsumo> entityModel = EntityModel.of(novoPerfil,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerfilConsumoController.class).findById(novoPerfil.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerfilConsumoController.class).findAll()).withRel("perfis"));

        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }

    // PUT: Atualizar Perfil de Consumo
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar perfil de consumo", description = "Atualiza as informações de um perfil de consumo existente identificado pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Perfil não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EntityModel<PerfilConsumo>> update(
            @Parameter(description = "ID do perfil a ser atualizado", required = true)
            @PathVariable Long id,
            @Parameter(description = "Novas informações do perfil", required = true)
            @RequestBody PerfilConsumoDTO dto) {
        PerfilConsumo perfilAtualizado = service.update(id, dto);
        EntityModel<PerfilConsumo> entityModel = EntityModel.of(perfilAtualizado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerfilConsumoController.class).findById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerfilConsumoController.class).findAll()).withRel("perfis"));

        return ResponseEntity.ok(entityModel);
    }

    // DELETE: Deletar Perfil de Consumo
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar perfil de consumo", description = "Remove um perfil de consumo existente identificado pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Perfil deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Perfil não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ID do perfil a ser deletado", required = true)
            @PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
