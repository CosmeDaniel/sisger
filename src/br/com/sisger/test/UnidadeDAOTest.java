package br.com.sisger.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sisger.dao.EnderecoDAO;
import br.com.sisger.dao.UnidadeDAO;
import br.com.sisger.modelo.Endereco;
import br.com.sisger.modelo.Unidade;

public class UnidadeDAOTest {
	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste
	
	//Criando o método para salvar	
	public void salvar(){
		EnderecoDAO enderecoDAO = new EnderecoDAO();  //Criando um DAO para acesso a Função
		Endereco endereco = enderecoDAO.buscarPorCodigo(3L); //Pesquisando o Codigo e inserindo na variável
		
		Unidade unidade = new Unidade();  //Criando uma novo objeto para receber os dados
		unidade.setRazaoSocialUnidade("Universidade Federal de MG"); //Inserindo valores no objeto
		unidade.setCnpjUnidade("333333333333333333"); //Inserindo valores no objeto
		unidade.setInscricaoEstadualUnidade("44444455553333332"); //Inserindo valores no objeto
		unidade.setContatoUnidade("Cosme Daniel Moreira"); //Inserindo valores no objeto
		unidade.setEndereco(endereco); //Inserindo valores de um outro objeto no objeto
		
		UnidadeDAO unidadeDAO = new UnidadeDAO(); //Criando uma DAO para acesso a Função 
		unidadeDAO.salvar(unidade); //Executando o metodo salvar na variável 
	}

	@Test
	//@Ignore	
	public void listar(){
		UnidadeDAO unidadeDAO = new UnidadeDAO();
		List<Unidade> unidades = unidadeDAO.listar();
		
		for(Unidade unidade : unidades){
		System.out.println(unidade);
		
		}
	}
	
	@Test
	@Ignore	
	public void buscarPorCodigo(){
		UnidadeDAO unidadeDAO = new UnidadeDAO();
		Unidade unidade = unidadeDAO.buscarPorCodigo(12L);
		
		System.out.println(unidade);
		
	}
	
	@Test
	@Ignore
	public void excluir(){
		UnidadeDAO unidadeDAO = new UnidadeDAO();
		Unidade unidade = unidadeDAO.buscarPorCodigo(3L);
		
		unidadeDAO.excluir(unidade);	
	}

	@Test
	@Ignore
	public void editar(){	
		UnidadeDAO unidadeDAO = new UnidadeDAO();
		Unidade unidade = unidadeDAO.buscarPorCodigo(4L);

		EnderecoDAO enderecoDAO = new EnderecoDAO();
		Endereco endereco = enderecoDAO.buscarPorCodigo(4L);
		unidade.setEndereco(endereco);

		unidade.setRazaoSocialUnidade("Universidade Federal de Ouro Preto"); //Inserindo valores no objeto
		unidade.setCnpjUnidade("555555333333333"); //Inserindo valores no objeto
		unidade.setInscricaoEstadualUnidade("88844455553333332"); //Inserindo valores no objeto
		unidade.setContatoUnidade("Andrei Guimarães Rosa"); //Inserindo valores no objeto
	
		unidadeDAO.editar(unidade);		
	}
	
	@Test
	@Ignore
	public void buscarCampiFaculdade(){
		UnidadeDAO unidadeDAO = new UnidadeDAO();
		List<Unidade> campiUnidades = unidadeDAO.buscarCampiUnidade(11L);
		
		for (Unidade unidade : campiUnidades) {
			System.out.println(unidade);
		}
	}
}
