package br.com.sisger.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sisger.dao.RecursoDAO;
import br.com.sisger.dao.StatusDAO;
import br.com.sisger.dao.SubCategoriaDAO;
import br.com.sisger.dao.UnidadeDAO;
import br.com.sisger.modelo.Recurso;
import br.com.sisger.modelo.Status;
import br.com.sisger.modelo.SubCategoria;
import br.com.sisger.modelo.Unidade;

public class RecursoDAOTest {
	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste
	
	//Criando o método para salvar	
	public void salvar(){
		RecursoDAO recursoDAO = new RecursoDAO();  //Criando um DAO para acesso a Função
		Recurso recurso = new Recurso();  //Criando uma novo objeto para receber os dados
		
		UnidadeDAO unidadeDAO = new UnidadeDAO();  //Criando um DAO para acesso a Função
		Unidade unidade = unidadeDAO.buscarPorCodigo(1L); //Pesquisando o Codigo e inserindo na variável
		recurso.setUnidade(unidade); //Inserindo valores de um outro objeto no objeto
		
		SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();  //Criando um DAO para acesso a Função
		SubCategoria subCategoria = subCategoriaDAO.buscarPorCodigo(1L); //Pesquisando o Codigo e inserindo na variável
		recurso.setSubCategoria(subCategoria); //Inserindo valores de um outro objeto no objeto
		
		StatusDAO statusDAO = new StatusDAO();  //Criando um DAO para acesso a Função
		Status status = statusDAO.buscarPorCodigo(1L); //Pesquisando o Codigo e inserindo na variável
		recurso.setStatus(status); //Inserindo valores de um outro objeto no objeto

		recurso.setNumSerieRecurso("ZKT45RLM8"); //Inserindo valores no objeto
		recurso.setPatrimonioRecurso("1987142"); //Inserindo valores no objeto
		recurso.setIdentificacaoRecurso("POSITIVO PX500"); //Inserindo valores no objeto
		recurso.setModeloRecurso("ZETA2"); //Inserindo valores no objeto

		recursoDAO.salvar(recurso); //Executando o metodo salvar na variável 
		
	}

	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste
	public void listar(){
		RecursoDAO recursoDAO = new RecursoDAO();
		List<Recurso> recursos = recursoDAO.listar();
		
		for (Recurso recurso : recursos) {
			System.out.println(recurso);
		}
		
	}
	
	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste	
	public void buscarPorCodigo(){
		RecursoDAO recursoDAO = new RecursoDAO();
		Recurso recurso = recursoDAO.buscarPorCodigo("NS1456STP80");
		
		System.out.println(recurso);
		
		
	}

	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste	
	public void excluir(){
		RecursoDAO recursoDAO = new RecursoDAO();
		Recurso recurso = recursoDAO.buscarPorCodigo("1");
		
		recursoDAO.excluir(recurso);
	}

	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste	
	public void editar(){
		RecursoDAO recursoDAO = new RecursoDAO();  //Criando um DAO para acesso a Função
		Recurso recurso = new Recurso();  //Criando uma novo objeto para receber os dados
		recurso = recursoDAO.buscarPorCodigo("KTZT45LM10"); //Pesquisando o Codigo e inserindo na variável
		
		UnidadeDAO unidadeDAO = new UnidadeDAO();  //Criando um DAO para acesso a Função
		Unidade unidade = unidadeDAO.buscarPorCodigo(1L); //Pesquisando o Codigo e inserindo na variável
		recurso.setUnidade(unidade); //Inserindo valores de um outro objeto no objeto
		
		SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();  //Criando um DAO para acesso a Função
		SubCategoria subCategoria = subCategoriaDAO.buscarPorCodigo(1L); //Pesquisando o Codigo e inserindo na variável
		recurso.setSubCategoria(subCategoria); //Inserindo valores de um outro objeto no objeto
		
		StatusDAO statusDAO = new StatusDAO();  //Criando um DAO para acesso a Função
		Status status = statusDAO.buscarPorCodigo(3L); //Pesquisando o Codigo e inserindo na variável
		recurso.setStatus(status); //Inserindo valores de um outro objeto no objeto
		
		recurso.setNumSerieRecurso("KTZT45LM10"); //Inserindo valores no objeto
		recurso.setPatrimonioRecurso("1987143"); //Inserindo valores no objeto
		recurso.setIdentificacaoRecurso("POSITIVO PX600"); //Inserindo valores no objeto
		recurso.setModeloRecurso("ZETA3"); //Inserindo valores no objeto
		
		recursoDAO.editar(recurso);
	}
	
	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste	
	public void buscarRecursoUnidadePorCodigo(){
		RecursoDAO recursoDAO = new RecursoDAO();
		List<Recurso> recursos = recursoDAO.recursosUnidade(15L);
		
		for (Recurso recurso : recursos) {
			System.out.println(recurso);
					
		}	
	}
	
	@Test
	@Ignore
	public void listarLiberados(){
		RecursoDAO recursoDAO = new RecursoDAO();
		List<Recurso> recusoLiberados = recursoDAO.listarLiberados(3L);
		
		for (Recurso recurso : recusoLiberados) {
			System.out.println(recurso);
		}	
	}
	
	@Test
	//@Ignore
	public void listarLiberadosUnidade(){
		RecursoDAO recursoDAO = new RecursoDAO();
		List<Recurso> recusoLiberados = recursoDAO.listarLiberadosUnidade(3L, 11L);
		
		for (Recurso recurso : recusoLiberados) {
			System.out.println(recurso);
		}	
	}
}
