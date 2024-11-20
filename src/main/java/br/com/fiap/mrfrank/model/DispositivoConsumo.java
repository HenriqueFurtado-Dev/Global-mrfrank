package br.com.fiap.mrfrank.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class DispositivoConsumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeDispositivo;

    private String tipoDispositivo;

    private String localizacao;

    private Double consumoEnergiaKwh;

    @Enumerated(EnumType.STRING)
    private StatusDispositivo status;

    private LocalDateTime dataHora = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}

