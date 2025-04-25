package persistencia_semdao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Produto {

    private int id;
    private String descricao;
    private double preco;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    // Conexão com o banco
    public Connection getConexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String URL = "jdbc:mysql://localhost:3306/aula_ioo";
            String USER = "root";
            String PASSWORD = "";
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
        }
    }

    // Cadastrar produto
    public void cadastrar() throws SQLException {
        Connection con = getConexao();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO produto (descricao, preco) VALUES (?, ?)");
        stmt.setString(1, descricao);
        stmt.setDouble(2, preco);
        stmt.executeUpdate();
        con.close();
    }

    // Atualizar produto
    public void atualizar() throws SQLException {
        Connection con = getConexao();
        PreparedStatement stmt = con.prepareStatement("UPDATE produto SET descricao = ?, preco = ? WHERE id = ?");
        stmt.setString(1, descricao);
        stmt.setDouble(2, preco);
        stmt.setInt(3, id);
        stmt.executeUpdate();
        con.close();
    }

    // Deletar produto
    public void deletar() throws SQLException {
        Connection con = getConexao();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM produto WHERE id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
        con.close();
    }

    // Listar produtos
    public static List<Produto> listar() throws SQLException {
        List<Produto> lista = new ArrayList<>();
        Connection con = new Produto().getConexao();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM produto");

        while (rs.next()) {
            Produto p = new Produto();
            p.setId(rs.getInt("id"));
            p.setDescricao(rs.getString("descricao"));
            p.setPreco(rs.getDouble("preco"));
            lista.add(p);
        }
        con.close();
        return lista;
    }
}