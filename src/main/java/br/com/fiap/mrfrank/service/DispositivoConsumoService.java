package br.com.fiap.mrfrank.service;

import br.com.fiap.mrfrank.controller.dto.DispositivoConsumoDTO;
import br.com.fiap.mrfrank.model.DispositivoConsumo;
import br.com.fiap.mrfrank.model.StatusDispositivo;
import br.com.fiap.mrfrank.model.Usuario;
import br.com.fiap.mrfrank.repository.DispositivoConsumoRepository;
import br.com.fiap.mrfrank.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DispositivoConsumoService {
    private final DispositivoConsumoRepository dispositivoRepository;
    private final UsuarioRepository usuarioRepository;

    public List<DispositivoConsumo> findAll() {
        return dispositivoRepository.findAll();
    }

    public DispositivoConsumo findById(Long id) {
        return dispositivoRepository.findById(id).orElseThrow(() -> new RuntimeException("Dispositivo não encontrado"));
    }

    public DispositivoConsumo save(DispositivoConsumoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        DispositivoConsumo dispositivo = new DispositivoConsumo();
        dispositivo.setNomeDispositivo(dto.getNomeDispositivo());
        dispositivo.setTipoDispositivo(dto.getTipoDispositivo());
        dispositivo.setLocalizacao(dto.getLocalizacao());
        dispositivo.setConsumoEnergiaKwh(dto.getConsumoEnergiaKwh());
        dispositivo.setStatus(StatusDispositivo.valueOf(dto.getStatus()));
        dispositivo.setUsuario(usuario);

        return dispositivoRepository.save(dispositivo);
    }

    public DispositivoConsumo update(Long id, DispositivoConsumoDTO dto) {
        DispositivoConsumo dispositivo = findById(id);
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        dispositivo.setNomeDispositivo(dto.getNomeDispositivo());
        dispositivo.setTipoDispositivo(dto.getTipoDispositivo());
        dispositivo.setLocalizacao(dto.getLocalizacao());
        dispositivo.setConsumoEnergiaKwh(dto.getConsumoEnergiaKwh());
        dispositivo.setStatus(StatusDispositivo.valueOf(dto.getStatus()));
        dispositivo.setUsuario(usuario);

        return dispositivoRepository.save(dispositivo);
    }

    public void deleteById(Long id) {
        dispositivoRepository.deleteById(id);
    }
}
