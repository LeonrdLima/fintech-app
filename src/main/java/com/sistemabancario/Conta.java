package com.sistemabancario;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Conta {
    private Cliente titular;
    private String numero;
    private double saldo;
    private List<String> historico = new ArrayList<>();
    
    public Conta(Cliente cliente) {
        this.titular = cliente;
        this.numero = geraNumero();
        this.saldo = 0.0;
    }

    private String geraNumero() {
        Random gerador = new Random();
        int numero = 1 + gerador.nextInt(99999999);
        String numeroFormatado = String.format("%08d", numero);
        int digito = gerador.nextInt(10);
        return numeroFormatado + "-" + digito;
    }

    public Cliente getTitular() {
        return this.titular;
    }

    public String getNumero() {
        return this.numero;
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
