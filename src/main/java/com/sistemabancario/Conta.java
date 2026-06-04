package com.sistemabancario;

public class Conta {
    String nome;
    String cpf;
    int numero;
    double saldo;
    
    public Conta(Cliente cliente, int numero) {
        this.nome = cliente.nome;
        this.cpf = cliente.cpf;
        this.numero = numero;
        this.saldo = 0.0;
    }

    public Conta() {
        this.nome = "";
        this.cpf = "";
        this.numero = 0;
        this.saldo = 0.0;
    }

    public double getSaldo() {
        return this.saldo;
    }

    

}
