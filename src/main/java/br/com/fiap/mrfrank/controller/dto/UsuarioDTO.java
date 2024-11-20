package br.com.fiap.mrfrank.controller.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private String nome;
    private String email;
    private String tipoConta;
}
