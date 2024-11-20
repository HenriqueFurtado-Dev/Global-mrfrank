package br.com.fiap.mrfrank.service;

import br.com.fiap.mrfrank.model.ConsumoEnergia;
import br.com.fiap.mrfrank.repository.ConsumoEnergiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumoEnergiaService {

    @Autowired
    private ConsumoEnergiaRepository consumoEnergiaRepository;

    public ConsumoEnergia salvarConsumo(ConsumoEnergia consumoEnergia) {
        return consumoEnergiaRepository.save(consumoEnergia);
    }

    public List<ConsumoEnergia> listarConsumoPorDispositivo(Long dispositivoId) {
        return consumoEnergiaRepository.findByDispositivoId(dispositivoId);
    }

    public void deletarConsumo(Long id) {
        consumoEnergiaRepository.deleteById(id);
    }
}
