package br.com.sisger.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sisger.dao.EstadoDAO;
import br.com.sisger.modelo.Estado;

public class EstadoDAOTest {
	@Test //Anota��o informando que � uma classe de teste
	@Ignore//Anota��o para informar que a clesse vai ser ignorada no teste
	public void salvar(){ //Criando o m�todo para salvar um Estado
		Estado estado1 = new Estado(); //Criando um novo estado 

		estado1.setUfEstado("TS"); //Adicionando o valor na vari�vel estado
		
		EstadoDAO estadoDAO = new EstadoDAO(); //Criando um Estado para acesso a Fun��o
		estadoDAO.salvar(estado1); //Executando M�todo Salvar contido no EstadoDAO
	}
	
	@Test
	@Ignore
	public void listar(){
		EstadoDAO estadoDAO = new EstadoDAO(); //Criando um Estado para acesso a Fun��o
		List<Estado> estados = estadoDAO.listar(); //Atribuindo a resultados a variavel estados
			
		System.out.println(estados); //Imprimindo Lista de Estados

		
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		EstadoDAO estadoDAO = new EstadoDAO(); 
		
		//Passano o resultado da cunsulta para a variavel estado e informando quem valor a ser buscado
		Estado estado = estadoDAO.buscarPorCodigo(27L);
		
		System.out.println(estado);
	}
	
	@Test
	@Ignore
	public void excluir(){	
		EstadoDAO estadoDAO = new EstadoDAO();
		
		Estado estado = estadoDAO.buscarPorCodigo(27L);
		estadoDAO.excluir(estado);			
		
	}
	
	@Test
	@Ignore
	public void editar(){
		EstadoDAO estadoDAO = new EstadoDAO();
		
		Estado estado = estadoDAO.buscarPorCodigo(27L);
		estado.setUfEstado("DF");
		
		estadoDAO.editar(estado);
	}
}
