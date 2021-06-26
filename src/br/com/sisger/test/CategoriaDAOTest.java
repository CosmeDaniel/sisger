package br.com.sisger.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sisger.dao.CategoriaDAO;
import br.com.sisger.modelo.Categoria;

public class CategoriaDAOTest {
	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste
	
	 //Criando o método para salvar um Categoria	
	public void salvar(){
		Categoria categoria = new Categoria(); //Criando uma nova Categoria
		categoria.setNomeCategoria("TESTE"); //Adicionando o valor na variável
		
		CategoriaDAO categoriaDAO = new CategoriaDAO(); //Criando uma CategoriaDAO para acesso a Função  
		categoriaDAO.salvar(categoria); //Executando Método Salvar contido na CategoriaDAO
		
	}
	
	@Test //Anotação informando que é uma classe de teste
	@Ignore//Anotação para informar que a clesse vai ser ignorada no teste	
	public void listar(){
		CategoriaDAO categoriaDAO = new CategoriaDAO(); //Criando um Estado para acesso a Função
		List<Categoria> categorias =  categoriaDAO.listar(); //Atribuindo a resultados a variavel estados
		
		System.out.println(categorias); //Imprimindo Lista de Estados
		
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		//Passano o resultado da cunsulta para a variavel categoria e informando quem valor a ser buscado
		Categoria categoria = categoriaDAO.buscarPorCodigo(4L);
		
		System.out.println(categoria);
		
	}
	
	@Test
	@Ignore
	public void excluir(){
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		Categoria categoria = categoriaDAO.buscarPorCodigo(7L);
		
		categoriaDAO.excluir(categoria);
	}


	@Test
	@Ignore
	public void editar(){
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		
		Categoria categoria = categoriaDAO.buscarPorCodigo(5L);
		categoria.setNomeCategoria("VÍDEO");
		
		categoriaDAO.editar(categoria);
		
	}

}
