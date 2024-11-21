package br.com.fiap.mrfrank.service;

import br.com.fiap.mrfrank.controller.dto.PerfilConsumoDTO;
import br.com.fiap.mrfrank.model.DispositivoConsumo;
import br.com.fiap.mrfrank.model.PerfilConsumo;
import br.com.fiap.mrfrank.repository.DispositivoConsumoRepository;
import br.com.fiap.mrfrank.repository.PerfilConsumoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilConsumoService {
    private final PerfilConsumoRepository perfilConsumoRepository;
    private final DispositivoConsumoRepository dispositivoConsumoRepository;

    public List<PerfilConsumo> findAll() {
        return perfilConsumoRepository.findAll();
    }

    public PerfilConsumo findById(Long id) {
        return perfilConsumoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil de consumo não encontrado"));
    }

    public PerfilConsumo save(PerfilConsumoDTO dto) {
        DispositivoConsumo dispositivo = dispositivoConsumoRepository.findById(dto.getDispositivoId())
                .orElseThrow(() -> new RuntimeException("Dispositivo não encontrado"));

        PerfilConsumo perfilConsumo = new PerfilConsumo();
        perfilConsumo.setMediaConsumoKwh(dto.getMediaConsumoKwh());
        perfilConsumo.setPicoConsumoKwh(dto.getPicoConsumoKwh());
        perfilConsumo.setHorarioPico(dto.getHorarioPico());
        perfilConsumo.setDiasMaisAtivos(dto.getDiasMaisAtivos());
        perfilConsumo.setRecomendacoes(dto.getRecomendacoes());
        perfilConsumo.setDispositivo(dispositivo);

        return perfilConsumoRepository.save(perfilConsumo);
    }

    public PerfilConsumo update(Long id, PerfilConsumoDTO dto) {
        PerfilConsumo perfilConsumo = findById(id);
        DispositivoConsumo dispositivo = dispositivoConsumoRepository.findById(dto.getDispositivoId())
                .orElseThrow(() -> new RuntimeException("Dispositivo não encontrado"));

        perfilConsumo.setMediaConsumoKwh(dto.getMediaConsumoKwh());
        perfilConsumo.setPicoConsumoKwh(dto.getPicoConsumoKwh());
        perfilConsumo.setHorarioPico(dto.getHorarioPico());
        perfilConsumo.setDiasMaisAtivos(dto.getDiasMaisAtivos());
        perfilConsumo.setRecomendacoes(dto.getRecomendacoes());
        perfilConsumo.setDispositivo(dispositivo);

        return perfilConsumoRepository.save(perfilConsumo);
    }

    public void deleteById(Long id) {
        perfilConsumoRepository.deleteById(id);
    }
}
