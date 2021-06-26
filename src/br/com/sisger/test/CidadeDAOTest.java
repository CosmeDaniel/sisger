package br.com.sisger.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sisger.dao.CidadeDAO;
import br.com.sisger.dao.EstadoDAO;
import br.com.sisger.modelo.Cidade;
import br.com.sisger.modelo.Estado;

public class CidadeDAOTest {

	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste
	
	//Criando o método para salvar
	public void salvar(){
		EstadoDAO estadoDAO = new EstadoDAO();  //Criando um DAO para acesso a Função  
		Estado estado = estadoDAO.buscarPorCodigo(23L); //Pesquisando o Codigo e inserindo na variável
		
		Cidade cidade = new Cidade(); //Criando uma novo objeto para receber os dados
		cidade.setNomeCidade("TESTE CIDADE"); //Inserindo valores no objeto
		cidade.setEstado(estado); //Inserindo valores de um outro objeto no objeto
		
		CidadeDAO cidadeDAO = new CidadeDAO(); //Criando uma DAO para acesso a Função 
		cidadeDAO.salvar(cidade); //Executando o metodo salvar na variável 
	}
	
	@Test
	@Ignore
	public void listar(){
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> cidades = cidadeDAO.listar();
		
		System.out.println(cidades);
		
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscarPorCodigo(5L);
		
		System.out.println(cidade);
		
	}
	
	@Test
	//@Ignore
	public void buscarPorCodigoEstado(){
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> cidades = cidadeDAO.buscarPorCodigoEstado(5L);
		
		for (Cidade cidade : cidades) {
			System.out.println(cidade);
		}
		
	}
	

	@Test
	@Ignore
	public void excluir(){
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscarPorCodigo(5567L);
		
		cidadeDAO.excluir(cidade);
		
	}
	
	@Test
	@Ignore
	public void editar(){
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscarPorCodigo(5565L);
		cidade.setNomeCidade("Brasília");
		
		EstadoDAO estadoDAO = new EstadoDAO();		
		Estado estado = estadoDAO.buscarPorCodigo(27L);
		cidade.setEstado(estado);
		
		cidadeDAO.editar(cidade);
		
	}
}
