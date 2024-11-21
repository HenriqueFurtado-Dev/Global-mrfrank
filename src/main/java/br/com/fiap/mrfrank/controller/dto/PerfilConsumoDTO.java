package br.com.fiap.mrfrank.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PerfilConsumoDTO {
    @NotNull(message = "A média de consumo em kWh é obrigatória")
    @DecimalMin(value = "0.0", inclusive = false, message = "A média de consumo deve ser maior que 0")
    private Double mediaConsumoKwh;

    @NotNull(message = "O pico de consumo em kWh é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O pico de consumo deve ser maior que 0")
    private Double picoConsumoKwh;

    @NotBlank(message = "O horário de pico é obrigatório")
    private String horarioPico;

    @NotBlank(message = "Os dias mais ativos são obrigatórios")
    private String diasMaisAtivos;

    @NotBlank(message = "As recomendações são obrigatórias")
    private String recomendacoes;

    @NotNull(message = "O ID do dispositivo é obrigatório")
    private Long dispositivoId;
}
