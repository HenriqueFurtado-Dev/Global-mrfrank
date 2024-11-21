package br.com.fiap.mrfrank.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogEventoDTO {
    @NotBlank(message = "O tipo do evento é obrigatório")
    private String tipoEvento;

    @NotBlank(message = "A descrição do evento é obrigatória")
    private String descricao;

    @NotNull(message = "A data e hora do evento são obrigatórias")
    private LocalDateTime dataHora;

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "O ID do dispositivo é obrigatório")
    private Long dispositivoId;
}
