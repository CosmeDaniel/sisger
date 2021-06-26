package br.com.sisger.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sisger.dao.StatusDAO;
import br.com.sisger.modelo.Status;

public class StatusDAOTest {
	@Test //Anota��o informando que � uma classe de teste
	@Ignore //Anota��o para informar que a clesse vai ser ignorada no teste
	//Criando o m�todo para salvar um Categoria
	public void salvar(){
		Status status = new Status(); //Criando um novo Status
		status.setNomeStatus("TESTE"); //Adicionando o valor na vari�vel
		
		StatusDAO statusDAO = new StatusDAO(); //Criando um StatusDAO para acesso a Fun��o 
		statusDAO.salvar(status); //Executando M�todo Salvar contido no StatusDAO
		
	}

	@Test //Anota��o informando que � uma classe de teste
	@Ignore//Anota��o para informar que a clesse vai ser ignorada no teste		
	public void listar(){
		StatusDAO statusDAO = new StatusDAO(); //Criando um Status para acesso a Fun��o
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
