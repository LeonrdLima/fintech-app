package com.sistemabancario;

public class Cliente {
    private String nome;
    private String cpf;
    private String email;
    private String senha;

    public Cliente(String nome, String cpf, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getSenha() {
        return this.senha;
    }

    public String getEmail() {
        return this.email;
    }

    public void alterarEmail(String email) {
        this.email = email;
    }

    public void alterarSenha(String senha) {
        this.senha = senha;
    }
}
