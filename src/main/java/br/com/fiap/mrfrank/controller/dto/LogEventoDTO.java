package br.com.fiap.mrfrank.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogEventoDTO {
    private String tipoEvento;
    private String descricao;
    private LocalDateTime dataHora; // Permite customizar a data
    private Long usuarioId; // Relaciona o log ao usuário
    private Long dispositivoId; // Relaciona o log ao dispositivo
}
