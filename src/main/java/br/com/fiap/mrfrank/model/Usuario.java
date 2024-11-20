package br.com.fiap.mrfrank.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    private LocalDate dataRegistro = LocalDate.now();
}

enum TipoConta {
    RESIDENCIAL, EMPRESARIAL
}
