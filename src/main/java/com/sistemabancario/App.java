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

        Conta conta = buscaConta(cpf);
        if (conta != null) {
            return conta;
        } else {
            System.out.println("cliente inválido");
            return null;
        }
    }

    static void menuConta(Conta conta) {
        while (true) {
            System.out.println("Bem vindo " + conta.titular.nome);
            System.out.println("Selecione uma opção");
            System.out.print("1. Ver extrato\n2. Depósito\n3. Saque\n4. Transferência\n5. Alterar dados cadastrais\n6. Excluir conta\n0. Sair\n");
            Scanner input = new Scanner(System.in);
            int opcao = input.nextInt();
            double valor;
            switch (opcao) {
                case 1:
                    System.out.println("Extrato da conta");
                    List<String> extrato = conta.verExtrato();
                    if (extrato.isEmpty()) {
                        System.out.println("Nenhuma movimentação na conta");
                    } else {
                        for (String transacao : extrato) {
                            System.out.println(transacao);
                        }
                    }
                    System.out.println("Saldo atual: R$" + conta.getSaldo());
                    break;

                case 2:
                    System.out.println("Digite um valor a ser depositado");
                    valor = input.nextDouble();
                    conta.deposito(valor);
                    conta.registrarTransacao("Depósito feito no valor de R$" + valor);
                    System.out.println("Depósito efetuado com sucesso");
                    break;

                case 3:
                    System.out.println("Digite um valor a ser sacado");
                    valor = input.nextDouble();
                    conta.saque(valor);
                    conta.registrarTransacao("Saque realizado no valor de R$" + valor);
                    System.out.println("Saque efetuado com sucesso");
                    break;

                case 4:
                    System.out.println("Digite o CPF do destinatário da conta que deseja transferir");
                    String cpf = input.next();
                    Conta destino = buscaConta(cpf);
                    if (destino != null) {
                        System.out.println("Digite um valor a ser transferido");
                        valor = input.nextDouble();
                        conta.transferir(destino, valor);
                        conta.registrarTransacao("Transferência feita para " + destino.titular.nome + " no valor de R$" + valor);
                        destino.registrarTransacao("Transferência recebida de " + conta.titular.nome + " no valor de R$" + valor);
                        System.out.println("Transferência realizada com sucesso");
                    } else {
                        System.out.println("CPF inválido");
                    }
                    break;

                case 5:
                    menuAlterarDados(conta);
                    break;

                case 6:
                    System.out.println("Tem certeza que quer apagar seus dados?");
                    System.out.print("1. Sim\n2. Não\n");
                    int opcaoDelete = input.nextInt();
                    if (opcaoDelete == 1) {
                        System.out.println("Digite sua senha");
                        String senha = input.next();
                        if (conta.titular.senha.equals(senha)) {
                            deletarConta(conta.titular.cpf);
                            System.out.println("Conta deletada com sucesso");
                            return;
                        } else {
                            System.out.println("Senha inválida");
                        }
                    } else if (opcaoDelete == 2) {
                        break;
                    } else {
                        System.out.println("Opção inválida");
                    }
                    break;

                case 0:
                    System.out.println("Saindo da conta");
                    return;

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

    static Conta buscaConta(String cpf) {
        for (Conta conta : listaContas) {
            if (conta.titular.cpf.equals(cpf)) {
                return conta;
            }
        }
        return null;
    }

    static void menuAlterarDados(Conta conta) {
        System.out.println("O que deseja alterar");
        System.out.print("1. E-mail\n2. Senha\n0. Voltar\n");
        Scanner input = new Scanner(System.in);
        int opcao = input.nextInt();
        input.nextLine();
        switch (opcao) {
            case 1:
                System.out.println("Digite o novo e-mail");
                String email = input.nextLine();
                conta.titular.alterarEmail(email);
                System.out.println("E-mail alterado com sucesso");
                break;

            case 2:
                while (true) {
                    System.out.println("Digite sua senha atual");
                    String senhaAtual = input.nextLine();
                    if (conta.titular.senha.equals(senhaAtual)) {
                        System.out.println("Digite nova senha");
                        String novaSenha = input.nextLine();
                        conta.titular.alterarSenha(novaSenha);
                        System.out.println("Senha alterada com sucesso");
                        break;
                    } else {
                        System.out.println("Senha incorreta");
                    }
                }
                break;
            
            case 0:
                return;

            default:
                break;
        }
    }

    static void deletarConta(String cpf) {
        listaContas.remove(buscaConta(cpf));
    }

}