package com.sistemabancario;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    Cliente titular;
    int numero;
    double saldo;
    List<String> historico = new ArrayList<>();
    
    public Conta(Cliente cliente, int numero) {
        this.titular = cliente;
        this.numero = numero;
        this.saldo = 0.0;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void deposito(double valor) {
        this.saldo = this.saldo + valor;
    }

    public void saque(double valor) {
        this.saldo = this.saldo - valor;
    }

    public void transferir(Conta destino, double valor) {
        saque(valor);
        destino.saldo = destino.saldo + valor;
    }

    public List<String> verExtrato() {
        return this.historico;
    }

    public void registrarTransacao(String mensagem) {
        this.historico.add(mensagem);
    }

}
