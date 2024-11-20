package br.com.fiap.mrfrank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "USUARIO_SEQ", allocationSize = 1)
    @Column(name = "ID_USUARIO", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "NOME", nullable = false)
    private String nome;

    @Email
    @NotBlank
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_CONTA", nullable = false)
    private TipoConta tipoConta;

    @CreationTimestamp
    @Column(name = "DATA_REGISTRO", updatable = false)
    private LocalDateTime dataRegistro;
}
