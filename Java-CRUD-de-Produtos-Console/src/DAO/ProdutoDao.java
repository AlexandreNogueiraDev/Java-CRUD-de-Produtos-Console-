package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Produto;

public class ProdutoDao {
	
	private Connection connection;
	
		
	
	
	public void adicionar(Produto produto) throws SQLException {
		
		try {
			connection = new ConnectionFactory().getConnection();
			String sql = "INSERT INTO tbproduto(produto,valorProduto) values (?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, produto.getProduto());
			stmt.setDouble(2, produto.getValorProduto());
			
			stmt.execute();
			stmt.close();
			connection.close();
			System.out.println("Produto cadastrado com sucesso");
		}
		catch(SQLException e){
			System.out.println("Erro: "+e);
		}
		
		
		
	}
	
	public List<Produto> getLista() throws SQLException{
		try {
			connection = new ConnectionFactory().getConnection();
			List<Produto> produtos = new ArrayList<Produto>();
			
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM tbproduto");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Produto produto = new Produto();
				
				produto.setIdProduto(rs.getInt(1));
				produto.setProduto(rs.getString(2));
				produto.setValorProduto(rs.getDouble(3));
				
				produtos.add(produto);
			}
			rs.close();
			stmt.close();
			connection.close();
			return produtos;
		}
		catch(SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public void alterar(Produto produto) throws SQLException{
		String sql = "UPDATE tbproduto SET produto= (?), valorProduto = (?) WHERE idProduto = (?)";
		
		try {
			connection = new ConnectionFactory().getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, produto.getProduto());
			stmt.setDouble(2, produto.getValorProduto());
			stmt.setInt(3, produto.getIdProduto());
			
			
			stmt.execute();
			stmt.close();
			connection.close();
			System.out.println("Dados alterados com sucesso\n");
		}
		catch(SQLException e) {
			throw new RuntimeException();	
		}

	}
	
	public void excluir(Produto produto) throws SQLException{
		
		String sql = "DELETE FROM tbproduto WHERE tbproduto.idProduto = (?)";
		
		try {
			connection = new ConnectionFactory().getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1,produto.getIdProduto());
			stmt.execute();
			stmt.close();
			connection.close();
			System.out.println("Dados Excluidos com Sucesso");
		}
		catch(SQLException e) {
			throw new RuntimeException();	
		}
	}
}
