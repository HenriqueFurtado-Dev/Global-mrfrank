package br.com.fiap.mrfrank.service;

import br.com.fiap.mrfrank.model.Dispositivo;
import br.com.fiap.mrfrank.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    public Dispositivo salvarDispositivo(Dispositivo dispositivo) {
        return dispositivoRepository.save(dispositivo);
    }

    public List<Dispositivo> listarDispositivosPorUsuario(Long usuarioId) {
        return dispositivoRepository.findByUsuarioId(usuarioId);
    }

    public void deletarDispositivo(Long id) {
        dispositivoRepository.deleteById(id);
    }
}
