package br.com.fiap.mrfrank.service;

import br.com.fiap.mrfrank.controller.dto.LogEventoDTO;
import br.com.fiap.mrfrank.model.DispositivoConsumo;
import br.com.fiap.mrfrank.model.LogEvento;
import br.com.fiap.mrfrank.model.Usuario;
import br.com.fiap.mrfrank.repository.DispositivoConsumoRepository;
import br.com.fiap.mrfrank.repository.LogEventoRepository;
import br.com.fiap.mrfrank.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogEventoService {
    private final LogEventoRepository logEventoRepository;
    private final UsuarioRepository usuarioRepository;
    private final DispositivoConsumoRepository dispositivoConsumoRepository;

    public List<LogEvento> findAll() {
        return logEventoRepository.findAll();
    }

    public LogEvento findById(Long id) {
        return logEventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log não encontrado"));
    }

    public LogEvento save(LogEventoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        DispositivoConsumo dispositivo = dispositivoConsumoRepository.findById(dto.getDispositivoId())
                .orElseThrow(() -> new RuntimeException("Dispositivo não encontrado"));

        LogEvento logEvento = new LogEvento();
        logEvento.setTipoEvento(dto.getTipoEvento());
        logEvento.setDescricao(dto.getDescricao());
        logEvento.setDataHora(dto.getDataHora());
        logEvento.setUsuario(usuario);
        logEvento.setDispositivo(dispositivo);

        return logEventoRepository.save(logEvento);
    }

    public LogEvento update(Long id, LogEventoDTO dto) {
        LogEvento logEvento = findById(id);
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        DispositivoConsumo dispositivo = dispositivoConsumoRepository.findById(dto.getDispositivoId())
                .orElseThrow(() -> new RuntimeException("Dispositivo não encontrado"));

        logEvento.setTipoEvento(dto.getTipoEvento());
        logEvento.setDescricao(dto.getDescricao());
        logEvento.setDataHora(dto.getDataHora());
        logEvento.setUsuario(usuario);
        logEvento.setDispositivo(dispositivo);

        return logEventoRepository.save(logEvento);
    }

    public void deleteById(Long id) {
        logEventoRepository.deleteById(id);
    }
}
