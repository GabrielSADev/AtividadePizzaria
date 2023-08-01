package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Cliente> clientes = new ArrayList<>();
        Pedido pedido = new Pedido();
        Scanner scanner = new Scanner(System.in);
        int  loop = 0;
        int pedidosTotal = 0,pedidosAtivos = 0,pedidosEncerrados = 0;

        while (loop != 1) {


        System.out.println("\n======= Bem vindo a Pizzaria UniAmérica!! =======");
        System.out.println("1 - Cadastrar Cliente \uD83D\uDE08 \n2 - Buscar e(ou) editar dados de cliente");
        System.out.println("3 - Realizar pedido!!! \n4- Gerar Relatório \n5- Sair \uD83D\uDE22 \n6- Dados sobre pedidos");

            int opc = scanner.nextInt();

            switch (opc) {

                case 1:
                    System.out.println("=== DIGITE O NÚMERO DE PESSOAS A SEREM CADASTRADAS ===");
                    int cad = scanner.nextInt();

                    for (int i = 0; i < cad; i++) {
                        List<Endereco> enderecos = new ArrayList<>();

                        System.out.println("Digite um nome: ");
                        String nome = scanner.next();
                        System.out.println("Digite a quantidade de endereços para esse usuário: ");
                        int qtdEndereco = scanner.nextInt();
                        for (int j = 0; j < qtdEndereco; j++) {
                            System.out.println("Digite o nome da rua e logo em seguida o número da casa:");
                            enderecos.add(new Endereco(scanner.next(), scanner.nextInt()));
                        }
                        clientes.add(new Cliente(nome, enderecos));

                    }
                    for (int i = 0; i < clientes.size(); i++) {
                        System.out.println("===================== \n");
                        System.out.println("Nome do Cliente: " + clientes.get(i).getNome());
                    }

                    break;
                //////////////////////////////////////

                case 2:
                    System.out.println("\n=== BUSCAR PESSOA PELO NOME ===");
                    System.out.println("Digite o nome da pessoa que deseja buscar: ");
                    String nomeBusca = scanner.next();

                    Cliente clienteEncontrado = null;

                    for (Cliente cliente : clientes) {
                        if (cliente.getNome().equalsIgnoreCase(nomeBusca)) {
                            clienteEncontrado = cliente;
                            System.out.println("===================== \n");
                            System.out.println("Nome do cliente: " + cliente.getNome());
                            for (Endereco endereco : cliente.getEnderecos()) {
                                System.out.println("Nome da Rua: " + endereco.getRua());
                                System.out.println("Número do endereço: " + endereco.getNumero());
                            }
                            break;
                        }
                    }
                    if (clienteEncontrado != null) {
                        System.out.println("\nDeseja alterar dados do(a) cliente?");
                        System.out.println("1- Sim     2- Não");

                        int altOpc = scanner.nextInt();

                        if (altOpc == 1) {
                            System.out.println("O que você deseja alterar?");
                            System.out.println("1- Nome do cliente     2- Endereço");

                            int alterarOpc = scanner.nextInt();

                            switch (alterarOpc) {
                                case 1:
                                    System.out.println("Digite o novo nome do cliente: ");
                                    String novoNome = scanner.next();
                                    clienteEncontrado.setNome(novoNome);
                                    break;
                                case 2:
                                    System.out.println("Digite o número do endereço a ser editado (por ordem de cad): ");
                                    int numEndereco = scanner.nextInt();
                                    if (numEndereco > 0 && numEndereco <= clienteEncontrado.getEnderecos().size()) {
                                        System.out.println("Digite o nome da rua: ");
                                        String novaRua = scanner.next();
                                        System.out.println("Digite o número da casa: ");
                                        int novoNumero = scanner.nextInt();
                                        clienteEncontrado.getEnderecos().get(numEndereco - 1).setRua(novaRua);
                                        clienteEncontrado.getEnderecos().get(numEndereco - 1).setNumero(novoNumero);
                                    } else {
                                        System.out.println("Número de endereço inválido.");
                                    }
                                    break;
                                default:
                                    System.out.println("Opção inválida.");
                                    break;
                            }
                        }
                        else {
                            System.out.println("De volta ao menu!!");
                        }
                    }
                    break;
                    //////////////////////////////////////
                case 3:
                    System.out.println("Selecione o cliente que vai fazer o pedido!!");

                    String nomePedido = scanner.next();

                    Cliente clienteSelecionado = null;

                    for (Cliente cliente : clientes) {


                        if (cliente.getNome().equalsIgnoreCase(nomePedido)) {
                            clienteSelecionado = cliente;
                            break;
                        }
                    }
                    if (clienteSelecionado != null){
                        List<Pedido> pedidos = new ArrayList<>();

                        System.out.println("Selecione a Quantidade de pizzas: ");
                        int qtPizza = scanner.nextInt();
                        pedido.setQtPizza(qtPizza);

                        for (int y = 0; y < qtPizza; y++){
                            System.out.println("Digite o sabor da Pizza: ");
                            String saborPizza = scanner.next();
                            pedidosTotal += 1;
                            pedidosAtivos += 1;

                            pedidos.add(new Pedido(saborPizza, qtPizza));
                        }
                        clienteSelecionado.setPedidos(pedidos);

                        System.out.println("Número de pizzas no pedido: " + pedido.getQtPizza());


                        System.out.println("DEU CERTO PARA: " + clienteSelecionado.getNome());
                    }
                    else {
                        System.out.println(

                                "Cliente não encontrado. Abortar pedido =(");
                    }

                    break;
                    //////////////////////////////////////
                case 4:
                    System.out.println("Digite o nome do cliente com o pedido em aberto: ");
                    String nomeRelat = scanner.next();
                    Cliente clienteSelRet = null;

                    for (Cliente cliente : clientes) {
                        if (cliente.getNome().equalsIgnoreCase(nomeRelat)) {
                            clienteSelRet = cliente;
                            break;
                        }
                    }


                    if (clienteSelRet != null) {
                        System.out.println("Pedido(s) em aberto para " + clienteSelRet.getNome() + ": ");
                        List<Pedido> pedidos = clienteSelRet.getPedidos();
                        if (pedidos != null) {
                            for (int i = 0; i < pedidos.size(); i++) {
                                Pedido pedidoCliente = pedidos.get(i);
                                System.out.println("Pedido " + (i + 1) + ": " + pedidoCliente.getNomePizza()
                                        + " - Quantidade: " + pedidoCliente.getQtPizza());
                            }
                            System.out.println("\nDeseja encerrar algum pedido?");
                            System.out.println("Digite o número do pedido que deseja encerrar ou 0 para cancelar: ");
                            int pedidoEncerrar = scanner.nextInt();

                            if (pedidoEncerrar > 0 && pedidoEncerrar <= pedidos.size()) {
                                Pedido pedidoRemover = pedidos.get(pedidoEncerrar - 1);
                                clienteSelRet.removePedido(pedidoRemover);
                                System.out.println("Pedido encerrado para " + clienteSelRet.getNome());
                                pedidosEncerrados += 1;
                                pedidosAtivos -= 1;
                            } else if (pedidoEncerrar == 0) {
                                System.out.println("Nenhum pedido encerrado!!");
                            } else {
                                System.out.println("Número de pedido inválido =(");
                            }
                        } else {
                            System.out.println("Nenhum pedido em aberto para " + clienteSelRet.getNome());
                        }
                    }
                    break;
                //////////////////////////////////////
                case 5:
                    System.out.println("Até logo, volte sempre!!!");
                    loop = 1;
                    break;
                //////////////////////////////////////
                case 6:
                    System.out.println("Total de pedidos:" + pedidosTotal);
                    System.out.println("Pedidos ativos:" + pedidosAtivos);
                    System.out.println("Pedidos encerrados:" + pedidosEncerrados);
                    break;
                //////////////////////////////////////
                default:
                    System.out.println("Número não Encontrado \uD83D\uDE22!!");
                    break;
            }
        }
    }
}