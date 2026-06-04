package com.sistemabancario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static List<Conta> listaContas = new ArrayList<>();
    static List<Cliente> listaClientes = new ArrayList<>();
    public static void main(String[] args) {
        while (true) {
            System.out.println("Sistema bancário.");
            System.out.println("Digite um número para prosseguir:");
            System.out.print("1. Criar conta\n2. Entrar na conta\n0. Sair\n");
            Scanner input = new Scanner(System.in);
            int opcao = input.nextInt();
            switch (opcao) {
                case 1: 
                    criarConta();
                    break;
                
                case 2:
                    Conta conta = loginConta();
                    menuConta(conta);
                    break;
            
                case 0:
                    System.out.println("Saindo da aplicação");
                    return;

                default:
                    break;
            }
        }
    }

    static void criarConta() {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite seu nome");
        String nome = input.nextLine();
        System.out.println("Digite seu cpf");
        String cpf = input.nextLine();
        System.out.println("Digie seu e-mail");
        String email = input.nextLine();
        System.out.println("Digite uma senha para a conta");
        String senha = input.nextLine();

        Cliente newCliente = new Cliente(nome, cpf, email, senha);
        listaClientes.add(newCliente);
        Conta conta = new Conta(newCliente, 1);
        listaContas.add(conta);
    }

    static Conta loginConta() {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite seu CPF");
        String cpf = input.nextLine();
        System.out.println("Digite a senha");
        String senha = input.nextLine();

        Cliente user = validaCliente(cpf, senha);
        if (user == null) {
            System.out.println("cliente inválido");
            return null;
        }

        for (Conta conta : listaContas) {
            if (conta.cpf.equals(user.cpf)) {
                return conta;
            }
        }

        return null;
    }

    static void menuConta(Conta conta) {
        while (true) {
            System.out.println("Bem vindo " + conta.nome);
            System.out.println("Selecione uma opção");
            System.out.print("1. Ver saldo\n2. Depósito\n3. Saque\n4. Transferência\n");
            Scanner input = new Scanner(System.in);
            int opcao = input.nextInt();
            double valor;
            switch (opcao) {
                case 1:
                    System.out.println("Saldo atual: " + conta.getSaldo());
                    break;

                case 2:
                    System.out.println("Digite um valor a ser depositado");
                    valor = input.nextDouble();
                    conta.deposito(valor);
                    System.out.println("Depósito efetuado com sucesso");
                    break;

                case 3:
                    System.out.println("Digite um valor a ser sacado");
                    valor = input.nextDouble();
                    conta.saque(valor);
                    System.out.println("Saque efetuado com sucesso");
                    break;

                default:
                    break;
            }
        }
    }

    static Cliente validaCliente(String cpf, String senha) {
        for (Cliente cliente : listaClientes) {
            if (cpf.equals(cliente.cpf) && senha.equals(cliente.senha)) {
                return cliente;
            }
        }
        return null;
    }

}