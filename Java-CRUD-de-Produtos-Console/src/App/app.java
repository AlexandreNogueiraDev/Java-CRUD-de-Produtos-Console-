package exemploDao;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import DAO.ProdutoDao;
import Model.Produto;

public class app {
	public static void main(String[]args) throws SQLException {
		
		Scanner ler = new Scanner(System.in);
		int esc, id;
		double prod;
		String nome;
		
		ProdutoDao produtoDao = new ProdutoDao();
		Produto produto = new Produto();
		
		while(true) {
			
		
		System.out.println("\nAperte 1 para Adicionar um Produto\nAperte 2 para Consultar um Produto\nAperte 3 para Alterar um Produto\nAperte 4 para Deletar um Produto\nAperte 5 para Fechar o Programa");
		esc = ler.nextInt(); 
		
			if(esc == 1) {
				System.out.println("Qual o Nome do Produto: ");
				nome = ler.next();
				System.out.println("Qual o Valor do Produto: ");
				prod = ler.nextDouble();
				
				produto.setProduto(nome);
				produto.setValorProduto(prod);
				
				
				produtoDao.adicionar(produto);
			}
			if(esc == 2) {
				List<Produto> produtos = new ProdutoDao().getLista();
				
				for(Produto pro: produtos) {
				System.out.println("Id: "+pro.getIdProduto()+" Nome: "+pro.getProduto()+" Valor: "+pro.getValorProduto());
				}
			}	
			
			if(esc == 3) {
				
				System.out.println("Qual o Codigo do Produto que quer Alterar: ");
				id = ler.nextInt();
				System.out.println("Qual o Novo Nome do Produto: ");
				nome = ler.next();
				System.out.println("Qual o Novo Valor do Produto: ");
				prod = ler.nextDouble();
				
				produto.setProduto(nome);
				produto.setValorProduto(prod);
				produto.setIdProduto(id);
				
				produtoDao.alterar(produto);
		
			}
			if(esc == 4) {
				System.out.println("Qual o Codigo do Produto que quer Excluir: ");
				id = ler.nextInt();
				
				produto.setIdProduto(id);
				
				produtoDao.excluir(produto);
			}
			if(esc == 5) {
				break;
			}
		}
		
	}
}
