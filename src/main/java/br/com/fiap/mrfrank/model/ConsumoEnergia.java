package br.com.fiap.mrfrank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "CONSUMO_ENERGIA")
public class ConsumoEnergia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consumo_seq")
    @SequenceGenerator(name = "consumo_seq", sequenceName = "CONSUMO_SEQ", allocationSize = 1)
    @Column(name = "ID_CONSUMO", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_DISPOSITIVO", nullable = false)
    private Dispositivo dispositivo;

    @NotNull
    @Column(name = "DATA_HORA", nullable = false)
    private LocalDateTime dataHora;

    @NotNull
    @Column(name = "CONSUMO_ENERGIA_KWH", nullable = false)
    private String consumoEnergia;

    @Column(name = "STATUS")
    private String status;
}
