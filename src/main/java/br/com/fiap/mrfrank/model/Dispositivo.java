package br.com.fiap.mrfrank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "DISPOSITIVOS")
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dispositivo_seq")
    @SequenceGenerator(name = "dispositivo_seq", sequenceName = "DISPOSITIVO_SEQ", allocationSize = 1)
    @Column(name = "ID_DISPOSITIVO", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "NOME_DISPOSITIVO", nullable = false)
    private String nomeDispositivo;

    @NotBlank
    @Column(name = "TIPO_DISPOSITIVO", nullable = false)
    private String tipoDispositivo;

    @Column(name = "LOCALIZACAO")
    private String localizacao;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;
}
