package br.com.fiap.mrfrank.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class PerfilConsumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double mediaConsumoKwh;

    private Double picoConsumoKwh;

    private String horarioPico; // Ex.: "18h-23h"

    private String diasMaisAtivos; // Ex.: "Segunda, Terça, Sexta"

    private String recomendacoes; // Ex.: "Reduzir consumo no horário de pico"

    @OneToOne
    @JoinColumn(name = "dispositivo_id", nullable = false)
    private DispositivoConsumo dispositivo;
}
