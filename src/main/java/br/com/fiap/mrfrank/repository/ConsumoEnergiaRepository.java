package br.com.fiap.mrfrank.repository;

import br.com.fiap.mrfrank.model.ConsumoEnergia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsumoEnergiaRepository extends JpaRepository<ConsumoEnergia, Long> {
    List<ConsumoEnergia> findByDispositivoId(Long dispositivoId);
}
