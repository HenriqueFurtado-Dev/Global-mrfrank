package br.com.fiap.mrfrank.repository;

import br.com.fiap.mrfrank.model.Relatorio;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RelatorioRepository {

    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall inserirRelatorioCall;

    public RelatorioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        this.inserirRelatorioCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("inserir_relatorio");
    }

    // Inserir Relatório usando a procedure
    public void inserirRelatorio(String titulo, String descricao, Date dataGeracao, String tipoRelatorio, Long idUsuario) {
        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_titulo", titulo);
        inParams.put("p_descricao", descricao);
        inParams.put("p_data_geracao", dataGeracao);
        inParams.put("p_tipo_relatorio", tipoRelatorio);
        inParams.put("p_id_usuario", idUsuario);

        inserirRelatorioCall.execute(inParams);
    }

    // Listar todos os Relatórios
    public List<Relatorio> listarTodos() {
        String sql = "SELECT id_relatorio, titulo, descricao, data_geracao, tipo_relatorio, id_usuario FROM Relatorios";
        return jdbcTemplate.query(sql, new RelatorioRowMapper());
    }

    // Buscar Relatório por ID
    public Relatorio buscarPorId(Long id) {
        String sql = "SELECT id_relatorio, titulo, descricao, data_geracao, tipo_relatorio, id_usuario FROM Relatorios WHERE id_relatorio = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RelatorioRowMapper());
    }

    // Atualizar Relatório
    public void atualizarRelatorio(Long id, String titulo, String descricao, Date dataGeracao, String tipoRelatorio, Long idUsuario) {
        String sql = "UPDATE Relatorios SET titulo = ?, descricao = ?, data_geracao = ?, tipo_relatorio = ?, id_usuario = ? WHERE id_relatorio = ?";
        jdbcTemplate.update(sql, titulo, descricao, dataGeracao, tipoRelatorio, idUsuario, id);
    }

    // Deletar Relatório
    public void deletarRelatorio(Long id) {
        String sql = "DELETE FROM Relatorios WHERE id_relatorio = ?";
        jdbcTemplate.update(sql, id);
    }

    // RowMapper para Relatorio
    private static class RelatorioRowMapper implements RowMapper<Relatorio> {
        @Override
        public Relatorio mapRow(ResultSet rs, int rowNum) throws SQLException {
            Relatorio relatorio = new Relatorio();
            relatorio.setIdRelatorio(rs.getLong("id_relatorio"));
            relatorio.setTitulo(rs.getString("titulo"));
            relatorio.setDescricao(rs.getString("descricao"));
            relatorio.setDataGeracao(rs.getDate("data_geracao").toLocalDate());
            relatorio.setTipoRelatorio(rs.getString("tipo_relatorio"));
            relatorio.setIdUsuario(rs.getLong("id_usuario"));
            return relatorio;
        }
    }
}
