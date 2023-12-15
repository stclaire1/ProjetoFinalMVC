package br.edu.iftm.biblioteca.biblioteca.model;

import java.util.List;

import io.micrometer.common.lang.NonNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginModel {

    @NonNull
    private String nome_usuario;

    @NonNull
    private String cpf_usuario;
    @NonNull
    private String email;
    @NonNull
    private String telefone;

    @NonNull
    private String senha;

    private List<Role> roles;

    public LoginModel(String nome_usuario, String senha) {
        this.nome_usuario = nome_usuario;
        this.senha = senha;
    }
}
