package br.com.fiap.mrfrank.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDate;

@Data
public class RelatorioDTO {
    @NotBlank(message = "Título é obrigatório.")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatória.")
    private String descricao;

    @NotNull(message = "Data de geração é obrigatória.")
    private LocalDate dataGeracao;

    @NotBlank(message = "Tipo de relatório é obrigatório.")
    private String tipoRelatorio;

    @NotNull(message = "ID do usuário é obrigatório.")
    private Long idUsuario;
}