package br.com.fiap.mrfrank.controller.dto;

import lombok.Data;

@Data
public class DispositivoConsumoDTO {
    private String nomeDispositivo;
    private String tipoDispositivo;
    private String localizacao;
    private Double consumoEnergiaKwh;
    private String status;
    private Long usuarioId;
}
