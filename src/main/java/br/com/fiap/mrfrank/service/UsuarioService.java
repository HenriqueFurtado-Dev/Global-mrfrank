package br.com.fiap.mrfrank.service;

import br.com.fiap.mrfrank.model.Usuario;
import br.com.fiap.mrfrank.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario update(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = findById(id); // Lança exceção se o ID não for encontrado

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setTipoConta(usuarioAtualizado.getTipoConta());

        return repository.save(usuario); // Salva o usuário atualizado
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
