
package persistencia_semdao;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Persistencia_SemDAO {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Deletar");
            System.out.println("4 - Listar");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpa o ENTER

            try {
                switch (opcao) {
                    case 1:
                        Produto p1 = new Produto();
                        System.out.print("Descricao: ");
                        p1.setDescricao(sc.nextLine());
                        System.out.print("Preco: ");
                        p1.setPreco(sc.nextDouble());
                        sc.nextLine(); // limpa o ENTER
                        p1.cadastrar();
                        System.out.println("Produto cadastrado com sucesso!");
                        break;

                    case 2:
                        Produto p2 = new Produto();
                        System.out.print("ID do produto: ");
                        p2.setId(sc.nextInt());
                        sc.nextLine(); // limpa o ENTER
                        System.out.print("Nova descricao: ");
                        p2.setDescricao(sc.nextLine());
                        System.out.print("Novo preco: ");
                        p2.setPreco(sc.nextDouble());
                        sc.nextLine(); // limpa o ENTER
                        p2.atualizar();
                        System.out.println("Produto atualizado com sucesso!");
                        break;

                    case 3:
                        Produto p3 = new Produto();
                        System.out.print("ID do produto a deletar: ");
                        p3.setId(sc.nextInt());
                        sc.nextLine(); // limpa o ENTER
                        p3.deletar();
                        System.out.println("Produto deletado com sucesso!");
                        break;

                    case 4:
                        List<Produto> produtos = Produto.listar();
                        for (Produto p : produtos) {
                            System.out.printf("ID: %d | Descricao: %s | Preco: %.2f%n",
                                    p.getId(), p.getDescricao(), p.getPreco());
                        }
                        break;

                    case 0:
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (SQLException e) {
                System.out.println("Erro ao acessar o banco: " + e.getMessage());
            }
        }

        sc.close();
    }
}