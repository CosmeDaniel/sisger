package br.com.sisger.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sisger.dao.StatusDAO;
import br.com.sisger.modelo.Status;

public class StatusDAOTest {
	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste
	//Criando o método para salvar um Categoria
	public void salvar(){
		Status status = new Status(); //Criando um novo Status
		status.setNomeStatus("TESTE"); //Adicionando o valor na variável
		
		StatusDAO statusDAO = new StatusDAO(); //Criando um StatusDAO para acesso a Função 
		statusDAO.salvar(status); //Executando Método Salvar contido no StatusDAO
		
	}

	@Test //Anotação informando que é uma classe de teste
	@Ignore//Anotação para informar que a clesse vai ser ignorada no teste		
	public void listar(){
		StatusDAO statusDAO = new StatusDAO(); //Criando um Status para acesso a Função
		List<Status> status = statusDAO.listar(); //Atribuindo a resultados a variavel status
		
		System.out.println(status); //Imprimindo Lista de Status
		
	}

	@Test
	@Ignore
	public void buscarPorCodigo(){
		StatusDAO statusDAO = new StatusDAO();
		Status status = statusDAO.buscarPorCodigo(3L);
		
		System.out.println(status);
		
	}
	
	@Test
	//@Ignore
	public void buscarPorInterv(){
		StatusDAO statusDAO = new StatusDAO();
		List<Status> listaStatusIntervalo = statusDAO.buscarPorInterv(3L, 5L);
		
		for (Status status : listaStatusIntervalo) {
			System.out.println(status);
		}	
	}

	@Test
	@Ignore
	public void excluir(){
		StatusDAO statusDAO = new StatusDAO();
		Status status = statusDAO.buscarPorCodigo(6L);
	
		statusDAO.excluir(status);
		
	}
	
	@Test
	@Ignore
	public void editar(){
		StatusDAO statusDAO = new StatusDAO();
		Status status = statusDAO.buscarPorCodigo(3L);
		
		status.setNomeStatus("ATIVO");
		statusDAO.editar(status);
		
	}	
}
