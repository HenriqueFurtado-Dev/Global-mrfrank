package br.com.fiap.mrfrank.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class LogEvento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoEvento; // Exemplo: "Alerta", "Automação", etc.

    private String descricao; // Detalhes sobre o evento

    private LocalDateTime dataHora = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "dispositivo_id", nullable = false)
    private DispositivoConsumo dispositivo;
}
