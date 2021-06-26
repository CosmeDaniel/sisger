package br.com.sisger.test;


import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sisger.dao.CidadeDAO;
import br.com.sisger.dao.EnderecoDAO;
import br.com.sisger.modelo.Cidade;
import br.com.sisger.modelo.Endereco;

public class EnderecoDAOTest {
	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste
	
	//Criando o método para salvar
	public void salvar(){
		CidadeDAO cidadeDAO = new CidadeDAO();  //Criando um DAO para acesso a Função  
		Cidade cidade = cidadeDAO.buscarPorCodigo(26L); //Pesquisando o Codigo e inserindo na variável
		
		Endereco endereco = new Endereco();  //Criando uma novo objeto para receber os dados
		endereco.setRuaEndereco("Francisco Bicalho"); //Inserindo valores no objeto
		endereco.setNumeroEndereco(285); //Inserindo valores no objeto
		endereco.setComplementoEndereco("Loja A"); //Inserindo valores no objeto
		endereco.setBairroEndereco("Padre Eustáquio"); //Inserindo valores no objeto
		endereco.setCepEndereco("31213-000"); //Inserindo valores no objeto
		endereco.setCidade(cidade); //Inserindo valores de um outro objeto no objeto
		
		EnderecoDAO enderecoDAO = new EnderecoDAO(); //Criando uma DAO para acesso a Função 
		enderecoDAO.salvar(endereco); //Executando o metodo salvar na variável 
		
	}

	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste
	public void listar(){
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		List<Endereco> enderecos = enderecoDAO.listar();
		
		System.out.println(enderecos);	
	}
	
	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste	
	public void buscarPorCodigo(){
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		Endereco endereco = enderecoDAO.buscarPorCodigo(1L);
		
		System.out.println(endereco);
	}
	
	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste

	public void excluir(){
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		Endereco endereco = enderecoDAO.buscarPorCodigo(21L);
		
		enderecoDAO.excluir(endereco);	
	}

	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste	
	public void editar(){	
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		Endereco endereco = enderecoDAO.buscarPorCodigo(3L);
		endereco.setRuaEndereco("Pará de Minas"); //Inserindo valores no objeto
		endereco.setNumeroEndereco(168); //Inserindo valores no objeto
		endereco.setComplementoEndereco("Fundos"); //Inserindo valores no objeto
		endereco.setBairroEndereco("Carlos Prates"); //Inserindo valores no objeto
		endereco.setCepEndereco("32213-001"); //Inserindo valores no objeto
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscarPorCodigo(17L);
		endereco.setCidade(cidade);
		
		enderecoDAO.editar(endereco);
	}
	@Test
	@Ignore
	public void buscarEnde(){
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		List<Endereco> enderecos = null;
		Long codCidade = 13L;
		
		enderecos = enderecoDAO.listar();
		
		for (Endereco endereco : enderecos) {
			
			if (endereco.getCodEndereco().equals(codCidade)) {
				
				System.out.println(endereco);
			}
		}
		
	}	
	
}
