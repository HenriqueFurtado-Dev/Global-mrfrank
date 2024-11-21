package br.com.fiap.mrfrank.repository;

import br.com.fiap.mrfrank.model.LogEvento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEventoRepository extends JpaRepository<LogEvento, Long> {}
