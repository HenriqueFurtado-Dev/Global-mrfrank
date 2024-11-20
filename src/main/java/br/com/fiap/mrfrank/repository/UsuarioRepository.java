package br.com.fiap.mrfrank.repository;

import br.com.fiap.mrfrank.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Métodos adicionais se necessário
}
