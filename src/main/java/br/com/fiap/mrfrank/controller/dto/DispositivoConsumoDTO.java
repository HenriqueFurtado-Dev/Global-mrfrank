package br.com.fiap.mrfrank.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DispositivoConsumoDTO {
    @NotBlank(message = "O nome do dispositivo é obrigatório")
    @Size(min = 3, max = 100, message = "O nome do dispositivo deve ter entre 3 e 100 caracteres")
    private String nomeDispositivo;

    @NotBlank(message = "O tipo do dispositivo é obrigatório")
    @Size(min = 3, max = 50, message = "O tipo do dispositivo deve ter entre 3 e 50 caracteres")
    private String tipoDispositivo;

    @NotBlank(message = "A localização é obrigatória")
    private String localizacao;

    @NotNull(message = "O consumo de energia é obrigatório")
    private Double consumoEnergiaKwh;

    @NotBlank(message = "O status do dispositivo é obrigatório")
    private String status;

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long usuarioId;
}
