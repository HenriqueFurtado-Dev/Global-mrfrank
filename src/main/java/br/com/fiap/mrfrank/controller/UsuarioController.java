package br.com.fiap.mrfrank.controller;

import br.com.fiap.mrfrank.model.Usuario;
import br.com.fiap.mrfrank.service.UsuarioService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;

    // GET: Listar Todos os Usuários
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Usuario>>> findAll() {
        List<EntityModel<Usuario>> usuarios = service.findAll().stream()
                .map(usuario -> EntityModel.of(usuario,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).findById(usuario.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).findAll()).withRel("usuarios")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Usuario>> collectionModel = CollectionModel.of(usuarios,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).findAll()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    // GET: Buscar Usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> findById(@PathVariable Long id) {
        Usuario usuario = service.findById(id);
        EntityModel<Usuario> entityModel = EntityModel.of(usuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).findById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).findAll()).withRel("usuarios"));

        return ResponseEntity.ok(entityModel);
    }

    // POST: Inserir Usuário
    @PostMapping
    public ResponseEntity<EntityModel<Usuario>> save(@Validated @RequestBody Usuario usuario) {
        Usuario novoUsuario = service.save(usuario);
        EntityModel<Usuario> entityModel = EntityModel.of(novoUsuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).findById(novoUsuario.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).findAll()).withRel("usuarios"));

        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }

    // PUT: Atualizar Usuário
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = service.update(id, usuario);
        EntityModel<Usuario> entityModel = EntityModel.of(usuarioAtualizado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).findById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).findAll()).withRel("usuarios"));

        return ResponseEntity.ok(entityModel);
    }

    // DELETE: Deletar Usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
