package br.com.fiap.mrfrank.service;

import br.com.fiap.mrfrank.controller.dto.RelatorioDTO;
import br.com.fiap.mrfrank.model.Relatorio;
import br.com.fiap.mrfrank.repository.RelatorioRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;

    public RelatorioService(RelatorioRepository relatorioRepository) {
        this.relatorioRepository = relatorioRepository;
    }

    // Inserir Relatório
    public void inserirRelatorio(RelatorioDTO relatorioDTO) {
        Date sqlDate = Date.valueOf(relatorioDTO.getDataGeracao());
        relatorioRepository.inserirRelatorio(
                relatorioDTO.getTitulo(),
                relatorioDTO.getDescricao(),
                sqlDate,
                relatorioDTO.getTipoRelatorio(),
                relatorioDTO.getIdUsuario()
        );
    }

    // Listar Todos os Relatórios
    public List<Relatorio> listarTodos() {
        return relatorioRepository.listarTodos();
    }

    // Buscar Relatório por ID
    public Relatorio buscarPorId(Long id) {
        return relatorioRepository.buscarPorId(id);
    }

    // Atualizar Relatório
    public void atualizarRelatorio(Long id, RelatorioDTO relatorioDTO) {
        Date sqlDate = Date.valueOf(relatorioDTO.getDataGeracao());
        relatorioRepository.atualizarRelatorio(
                id,
                relatorioDTO.getTitulo(),
                relatorioDTO.getDescricao(),
                sqlDate,
                relatorioDTO.getTipoRelatorio(),
                relatorioDTO.getIdUsuario()
        );
    }

    // Deletar Relatório
    public void deletarRelatorio(Long id) {
        relatorioRepository.deletarRelatorio(id);
    }
}
