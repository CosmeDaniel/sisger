package br.com.sisger.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sisger.dao.CategoriaDAO;
import br.com.sisger.dao.SubCategoriaDAO;
import br.com.sisger.modelo.Categoria;
import br.com.sisger.modelo.SubCategoria;

public class SubCategoriaDAOTest {

	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste
	
	//Criando o método para salvar
	public void salvar(){
		SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();  //Criando um DAO para acesso a Função  
		SubCategoria subCategoria = new SubCategoria();  //Criando uma novo objeto para receber os dados
		
		CategoriaDAO categoriaDAO = new CategoriaDAO();  //Criando um DAO para acesso a Função  
		Categoria categoria = categoriaDAO.buscarPorCodigo(4L); //Pesquisando o Codigo e inserindo na variável
		subCategoria.setCategoria(categoria); //Inserindo valores de um outro objeto no objeto
		
		subCategoria.setNomeSubCategoria("AUDITÓRIOS"); //Inserindo valores no objeto

		subCategoriaDAO.salvar(subCategoria); //Executando o metodo salvar na variável 
		
	}

	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste
	public void listar(){
		SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
		List<SubCategoria> subCategorias = subCategoriaDAO.listar();
		
		for (SubCategoria subCategoria : subCategorias) {
			System.out.println(subCategoria);
		}
	}

	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste	
	public void buscarPorCodigo(){
		SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
		SubCategoria subCategoria = subCategoriaDAO.buscarPorCodigo(3L);
		
			System.out.println(subCategoria);
		
	}
	
	@Test //Anotação informando que é uma classe de teste
	//@Ignore //Anotação para informar que a clesse vai ser ignorada no teste	
	public void buscarPorNome(){
		SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
		SubCategoria subCategoria = subCategoriaDAO.buscarPorNome("Computador");
		
			System.out.println(subCategoria);
		
	}

	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste	
	public void excluir(){
		SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
		SubCategoria subCategoria = subCategoriaDAO.buscarPorCodigo(11L);
		
		subCategoriaDAO.excluir(subCategoria);
		
	}

	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste	
	public void editar(){
		SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
		SubCategoria subCategoria = new SubCategoria();
		subCategoria = subCategoriaDAO.buscarPorCodigo(10L);
		
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		Categoria categoria = new Categoria();
		categoria = categoriaDAO.buscarPorCodigo(4L);
		subCategoria.setCategoria(categoria);
		
		subCategoria.setNomeSubCategoria("AUDITÓRIOS");
		
		subCategoriaDAO.editar(subCategoria);
		
	}
}
