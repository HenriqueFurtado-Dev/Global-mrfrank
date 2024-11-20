package br.com.fiap.mrfrank.repository;

import br.com.fiap.mrfrank.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
    List<Dispositivo> findByUsuarioId(Long usuarioId);
}
