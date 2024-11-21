package br.com.fiap.mrfrank.controller.dto;

import lombok.Data;

@Data
public class PerfilConsumoDTO {
    private Double mediaConsumoKwh;
    private Double picoConsumoKwh;
    private String horarioPico;
    private String diasMaisAtivos;
    private String recomendacoes;
    private Long dispositivoId; // Relaciona o perfil ao dispositivo
}
