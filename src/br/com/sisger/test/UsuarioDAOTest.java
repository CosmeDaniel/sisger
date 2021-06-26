package br.com.sisger.test;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sisger.dao.FuncaoUsuarioDAO;
import br.com.sisger.dao.StatusDAO;
import br.com.sisger.dao.UnidadeDAO;
import br.com.sisger.dao.UsuarioDAO;
import br.com.sisger.modelo.FuncaoUsuario;
import br.com.sisger.modelo.Status;
import br.com.sisger.modelo.Unidade;
import br.com.sisger.modelo.Usuario;

public class UsuarioDAOTest {

	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste
	
	//Criando o método para salvar	
	public void salvar(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();  //Criando um DAO para acesso a Função 
		Usuario usuario = new Usuario();  //Criando uma novo objeto para receber os dados
				
		StatusDAO statusDAO = new StatusDAO();  //Criando um DAO para acesso a Função 
		Status status = statusDAO.buscarPorCodigo(1L); //Pesquisando o Codigo e inserindo na variável
		usuario.setStatus(status); //Inserindo valores de um outro objeto no objeto
		
		UnidadeDAO unidadeDAO = new UnidadeDAO();  //Criando um DAO para acesso a Função 
		Unidade unidade = unidadeDAO.buscarPorCodigo(4L); //Pesquisando o Codigo e inserindo na variável
		usuario.setUnidade(unidade); //Inserindo valores de um outro objeto no objeto
		
		FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO();  //Criando um DAO para acesso a Função 
		FuncaoUsuario funcaoUsuario = funcaoUsuarioDAO.buscarPorCodigo(3L); //Pesquisando o Codigo e inserindo na variável
		usuario.setFuncaoUsuario(funcaoUsuario); //Inserindo valores de um outro objeto no objeto
		
		usuario.setNomeUsuario("rererereree"); //Inserindo valores no objeto
		usuario.setCpfUsuario("54545454455"); //Inserindo valores no objeto
		usuario.setDataNascimentoUsuario(new Date()); //Inserindo valores no objeto
		usuario.setEmailUsuario("fadfadadaa@gmail.com"); //Inserindo valores no objeto
		usuario.setTelefoneUsuario("4354334343434"); //Inserindo valores no objeto		
		usuario.setLoginUsuario("qqrqrqrreerer"); //Inserindo valores no objeto
		usuario.setSenhaUsuario("12345678"); //Inserindo valores no objeto
		
		usuarioDAO.salvar(usuario); //Executando o metodo salvar na variável 
	}

	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste
	public void listar(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> usuarios = usuarioDAO.listar();
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
	}

	@Test //Anotação informando que é uma classe de teste
	//@Ignore //Anotação para informar que a clesse vai ser ignorada no teste
	public void buscarPorCodigo(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorCodigo(6L);
		
		System.out.println(usuario);	
	}
	
	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste
	public void buscarPorCodigoString(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorCodigoString("30");
		
		System.out.println(usuario);	
	}
	
	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste
	public void buscarPorEmail(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorEmailString("cosmedmoreira@gmail.com");
		
		System.out.println(usuario);	
	}

	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste
	public void excluir(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorCodigo(6L);
		usuarioDAO.excluir(usuario);
	}

	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste
	public void editar(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();  //Criando um DAO para acesso a Função 
		Usuario usuario = new Usuario();  //Criando uma novo objeto para receber os dados
				
		StatusDAO statusDAO = new StatusDAO();  //Criando um DAO para acesso a Função 
		Status status = statusDAO.buscarPorCodigo(4L); //Pesquisando o Codigo e inserindo na variável
		usuario.setStatus(status); //Inserindo valores de um outro objeto no objeto
		
		UnidadeDAO unidadeDAO = new UnidadeDAO();  //Criando um DAO para acesso a Função 
		Unidade unidade = unidadeDAO.buscarPorCodigo(2L); //Pesquisando o Codigo e inserindo na variável
		usuario.setUnidade(unidade); //Inserindo valores de um outro objeto no objeto
		
		FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO();  //Criando um DAO para acesso a Função 
		FuncaoUsuario funcaoUsuario = funcaoUsuarioDAO.buscarPorCodigo(5L); //Pesquisando o Codigo e inserindo na variável
		usuario.setFuncaoUsuario(funcaoUsuario); //Inserindo valores de um outro objeto no objeto
		
		usuario =  usuarioDAO.buscarPorCodigo(3L);
		usuario.setNomeUsuario("rererereree"); //Inserindo valores no objeto
		usuario.setCpfUsuario("77777777"); //Inserindo valores no objeto
		usuario.setDataNascimentoUsuario(new Date()); //Inserindo valores no objeto
		usuario.setEmailUsuario("DDDDDDDDD@gmail.com"); //Inserindo valores no objeto
		usuario.setTelefoneUsuario("88888888"); //Inserindo valores no objeto		
		usuario.setLoginUsuario("TTTTTTTTTT"); //Inserindo valores no objeto
		usuario.setSenhaUsuario("12345678"); //Inserindo valores no objeto
		
		usuarioDAO.editar(usuario); //Executando o metodo salvar na variável 
		
	}
	
	@Test //Anotação informando que é uma classe de teste
	@Ignore //Anotação para informar que a clesse vai ser ignorada no teste
	public void autenticar(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar("danielcosme", "12345690");
		
		System.out.println("Usuario Logado:" + usuario);	
	}
}
