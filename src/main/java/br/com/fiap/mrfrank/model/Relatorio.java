package br.com.fiap.mrfrank.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Relatorio {
    private Long idRelatorio;
    private String titulo;
    private String descricao;
    private LocalDate dataGeracao;
    private String tipoRelatorio;
    private Long idUsuario;
}
