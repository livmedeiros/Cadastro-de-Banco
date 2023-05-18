import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        ArrayList<Pessoa> clientes = new ArrayList<>();
        ArrayList<Banco> contas = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("Escolha uma opção: ");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Cadastrar conta");
            System.out.println("3 - Consultar saldo");
            System.out.println("4 - Exibir todas as contas cadastradas");
            System.out.println("5 - Sacar dinheiro");
            System.out.println("6 - Depositar dinheiro");
            System.out.println("7 - Sair");
            System.out.println("--------------------------------------------------------");

            System.out.print("-> ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> {
                    System.out.println("* CADASTRO DO CLIENTE *");
                    cadastrarCliente(clientes);
                }
                case 2 -> {
                    System.out.println("* CADASTRO DA CONTA *");
                    cadastrarConta(contas, clientes);
                }
                case 3 -> {
                    System.out.println("* CONSULTA DE SALDO *");
                    consultarSaldo(contas);
                }
                case 4 -> {
                    System.out.println("* CONTAS CADASTRADAS *");
                    exibirContas(contas);
                }
                case 5 -> {
                    System.out.println("* SAQUE *");
                    sacarDinheiro(contas);
                }
                case 6 -> {
                    System.out.println("* DEPÓSITO *");
                    depositarDinheiro(contas);
                }

                case 7 -> System.out.println("Obrigado por utilizar o nosso sistema!");

                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                    System.out.println("--------------------------------------------------------");
                }
            }

        } while (opcao != 7);

    }

    public static void cadastrarCliente(ArrayList<Pessoa> clientes){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome do cliente: ");
        String nome = sc.next();
        System.out.print("Digite o cpf: ");
        String cpf = sc.next();
        clientes.add(new Pessoa(nome, cpf));
        System.out.println("Cliente cadastrado com sucesso!");
        System.out.println("--------------------------------------------------------");
    }

    public static void cadastrarConta(ArrayList<Banco> contas, ArrayList<Pessoa> clientes){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o cpf do titular da conta: ");
        String cliente = sc.next();
        System.out.print("Digite o número da conta: ");
        String numConta = sc.next();
        System.out.print("Digite o saldo disponível na conta: ");
        double saldo = sc.nextDouble();
        Pessoa pessoa = buscarClientePorCpf(clientes, cliente);
        if (pessoa == null) {
            System.out.println("Cliente não encontrado! CPF inválido.");
            System.out.println("--------------------------------------------------------");
        } else {
            contas.add(new Banco(pessoa, numConta, saldo));
            System.out.println("Conta cadastrada com sucesso!");
            System.out.println("--------------------------------------------------------");
        }
    }

    public static void consultarSaldo(ArrayList<Banco> contas){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o número da conta: ");
        String numConta = sc.next();
        Banco conta = buscarContaPorNumero(contas, numConta);
        if (conta == null) {
            System.out.println("Conta não encontrada!");
            System.out.println("--------------------------------------------------------");
        } else {
            System.out.println("Saldo da conta " + conta.getNumConta() + ": R$ " + conta.getSaldo());
            System.out.println("--------------------------------------------------------");
        }
    }

    public static void exibirContas(ArrayList<Banco> contas){
        System.out.println("Contas cadastradas: ");
        System.out.println();

        for (Banco conta : contas) {
            System.out.println("Nome: " + conta.getCliente().getNome() +"\n" + "CPF do titular: "
                    + conta.getCliente().getCpf() + "\n" +"Número da conta: " + conta.getNumConta()
                    + "\n" + "Saldo disponível: R$ " +conta.getSaldo());
            System.out.println();
        }

        System.out.println("--------------------------------------------------------");
    }

    public static void sacarDinheiro(ArrayList<Banco> contas){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o número da conta: ");
        String numConta = sc.next();
        Banco conta = buscarContaPorNumero(contas, numConta);
        if (conta == null) {
            System.out.println("Conta não encontrada!");
            System.out.println("--------------------------------------------------------");
        } else {
            System.out.println("Seu saldo atual é de: R$" +conta.getSaldo());
            System.out.print("Digite o valor do saque: ");
            double valor = sc.nextDouble();
            if (conta.sacar(valor)) {
                System.out.println("Saque realizado com sucesso!");
                System.out.println("Seu saldo agora é de: R$" +conta.getSaldo());
                System.out.println("--------------------------------------------------------");
            } else {
                System.out.println("Saldo insuficiente!");
                System.out.println("--------------------------------------------------------");
            }
        }
    }

    public static void depositarDinheiro(ArrayList<Banco> contas){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o número da conta: ");
        String numConta = sc.next();
        Banco conta = buscarContaPorNumero(contas, numConta);
        if (conta == null) {
            System.out.println("Conta não encontrada!");
            System.out.println("--------------------------------------------------------");
        } else {
            System.out.println("Seu saldo atual é de: R$" +conta.getSaldo());
            System.out.print("Digite o valor do depósito: ");
            double valor = sc.nextDouble();
            conta.depositar(valor);
            System.out.println("Depósito realizado com sucesso!");
            System.out.println("Seu saldo agora é de: R$" +conta.getSaldo());
            System.out.println("--------------------------------------------------------");
        }
    }

    public static Pessoa buscarClientePorCpf(ArrayList<Pessoa> clientes, String cpf) {
        for (Pessoa cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public static Banco buscarContaPorNumero(ArrayList<Banco> contas, String numConta) {
        for (Banco conta : contas) {
            if (conta.getNumConta().equals(numConta)) {
                return conta;
            }
        }
        return null;
    }

}
