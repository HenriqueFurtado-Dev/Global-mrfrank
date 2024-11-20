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

    private String horarioPico;

    private String diasMaisAtivos;

    private String recomendacoes;

    @OneToOne
    @JoinColumn(name = "dispositivo_id")
    private DispositivoConsumo dispositivo;
}
