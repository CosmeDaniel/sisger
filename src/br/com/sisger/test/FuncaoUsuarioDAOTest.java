package br.com.sisger.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sisger.dao.FuncaoUsuarioDAO;
import br.com.sisger.modelo.FuncaoUsuario;

public class FuncaoUsuarioDAOTest {
	@Test //Anota��o informando que � uma classe de teste
	@Ignore //Anota��o para informar que a clesse vai ser ignorada no teste	
	public void salvar(){ //Criando o m�todo para salvar uma FuncaoUsuario
		FuncaoUsuario funcaoUsuario = new FuncaoUsuario(); //Criando uma nova FuncaoUsuario
		funcaoUsuario.setDescricaoFuncaoUsuario("TESTE"); //Adicionando o valor na vari�vel
		
		FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO(); //Criando uma FuncaoUsuarioDAO para acesso a Fun��o  
		funcaoUsuarioDAO.salvar(funcaoUsuario); //Executando M�todo Salvar contido na CategoriaDAO
	}

	@Test //Anota��o informando que � uma classe de teste
	@Ignore//Anota��o para informar que a clesse vai ser ignorada no teste	
	public void listar(){
		FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO(); //Criando um FuncaoUsuario para acesso a Fun��o
		List<FuncaoUsuario> funcaoUsuarios = funcaoUsuarioDAO.listar(); //Atribuindo a resultados a variavel funcaoUsuario
		
		System.out.println(funcaoUsuarios); //Imprimindo Lista de Fun��es
		
	}

	@Test
	@Ignore
	public void buscarPorCodigo(){
		FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO();
		//Passano o resultado da cunsulta para a variavel funcaoUsuario e informando quem valor a ser buscado
		FuncaoUsuario funcaoUsuario = funcaoUsuarioDAO.buscarPorCodigo(1L);
		
		System.out.println(funcaoUsuario);
		
	}
	
	@Test
	//@Ignore
	public void buscarPorCodMai(){
		FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO();
		//Passano o resultado da cunsulta para a variavel funcaoUsuario e informando quem valor a ser buscado
		List<FuncaoUsuario> listaFuncaoUsuario = funcaoUsuarioDAO.buscarPorCodMai(2L);
		
		for (FuncaoUsuario funcaoUsuario : listaFuncaoUsuario) {
			System.out.println(funcaoUsuario);
		}
		
	}
	
	@Test
	@Ignore
	public void excluir(){
		FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO();
		FuncaoUsuario funcaoUsuario = funcaoUsuarioDAO.buscarPorCodigo(9L);
		
		funcaoUsuarioDAO.excluir(funcaoUsuario);
	}
	
	@Test
	@Ignore
	public void editar(){
		FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO();
		FuncaoUsuario funcaoUsuario = funcaoUsuarioDAO.buscarPorCodigo(3L);
		funcaoUsuario.setDescricaoFuncaoUsuario("SUPORTE");
		
		funcaoUsuarioDAO.editar(funcaoUsuario);
	}

}
