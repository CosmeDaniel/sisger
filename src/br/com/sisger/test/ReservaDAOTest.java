package br.com.sisger.test;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sisger.dao.RecursoDAO;
import br.com.sisger.dao.ReservaDAO;
import br.com.sisger.dao.StatusDAO;
import br.com.sisger.dao.UsuarioDAO;
import br.com.sisger.modelo.Recurso;
import br.com.sisger.modelo.Reserva;
import br.com.sisger.modelo.Status;
import br.com.sisger.modelo.Usuario;

public class ReservaDAOTest {

	@Test //Anota��o informando que � uma classe de teste
	@Ignore //Anota��o para informar que a clesse vai ser ignorada no teste
	
	//Criando o m�todo para salvar
	public void salvar(){
		ReservaDAO reservaDAO = new ReservaDAO();  //Criando um DAO para acesso a Fun��o  
		Reserva reserva = new Reserva();  //Criando uma novo objeto para receber os dados
		
		RecursoDAO recursoDAO = new RecursoDAO();  //Criando um DAO para acesso a Fun��o  
		Recurso recurso = recursoDAO.buscarPorCodigo("RKB6789XZY2"); //Pesquisando o Codigo e inserindo na vari�vel
		reserva.setRecurso(recurso); //Inserindo valores de um outro objeto no objeto
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();  //Criando um DAO para acesso a Fun��o  
		Usuario usuario = usuarioDAO.buscarPorCodigo(1L); //Pesquisando o Codigo e inserindo na vari�vel
		reserva.setUsuario(usuario); //Inserindo valores de um outro objeto no objeto
		
		StatusDAO statusDAO = new StatusDAO();  //Criando um DAO para acesso a Fun��o  
		Status status = statusDAO.buscarPorCodigo(7L); //Pesquisando o Codigo e inserindo na vari�vel
		reserva.setStatus(status); //Inserindo valores de um outro objeto no objeto
		
		reserva.setDataReserva(new Date()); //Inserindo valores no objeto
		reserva.setDataInicialReserva(new Date()); //Inserindo valores no objeto		
		reserva.setDataFinalReserva(new Date()); //Inserindo valores no objeto
		reserva.setPatrimonioRecursoReserva(recurso.getPatrimonioRecurso());
		
		reservaDAO.salvar(reserva); //Executando o metodo salvar na vari�vel 
	}
	
	@Test //Anota��o informando que � uma classe de teste
	@Ignore//@Ignore //Anota��o para informar que a clesse vai ser ignorada no teste
	public void listar(){
		ReservaDAO reservaDAO = new ReservaDAO();
		List<Reserva> reservas = reservaDAO.listar();
		for (Reserva reserva : reservas) {
			
			System.out.println(reserva);
		}
	}
	
	@Test //Anota��o informando que � uma classe de teste
	@Ignore //Anota��o para informar que a clesse vai ser ignorada no teste	
	public void buscarPorCodigo(){
		ReservaDAO reservaDAO = new ReservaDAO();
		Reserva reserva = reservaDAO.buscarPorCodigo(2L);
		
		System.out.println(reserva);
		
	}

	@Test //Anota��o informando que � uma classe de teste
	@Ignore //Anota��o para informar que a clesse vai ser ignorada no teste
	public void excluir(){
		ReservaDAO reservaDAO = new ReservaDAO();
		Reserva reserva = reservaDAO.buscarPorCodigo(6L);
		
		reservaDAO.excluir(reserva);
	}

	@Test //Anota��o informando que � uma classe de teste
	@Ignore //Anota��o para informar que a clesse vai ser ignorada no teste
	public void editar(){
		ReservaDAO reservaDAO = new ReservaDAO();  //Criando um DAO para acesso a Fun��o  
		Reserva reserva = new Reserva();  //Criando uma novo objeto para receber os dados
		reserva = reservaDAO.buscarPorCodigo(7L);
		
		RecursoDAO recursoDAO = new RecursoDAO();  //Criando um DAO para acesso a Fun��o  
		Recurso recurso = recursoDAO.buscarPorCodigo("KTZT45LM10"); //Pesquisando o Codigo e inserindo na vari�vel
		reserva.setRecurso(recurso); //Inserindo valores de um outro objeto no objeto
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();  //Criando um DAO para acesso a Fun��o  
		Usuario usuario = usuarioDAO.buscarPorCodigo(2L); //Pesquisando o Codigo e inserindo na vari�vel
		reserva.setUsuario(usuario); //Inserindo valores de um outro objeto no objeto
		
		StatusDAO statusDAO = new StatusDAO();  //Criando um DAO para acesso a Fun��o  
		Status status = statusDAO.buscarPorCodigo(5L); //Pesquisando o Codigo e inserindo na vari�vel
		reserva.setStatus(status); //Inserindo valores de um outro objeto no objeto
		
		reserva.setDataReserva(new Date()); //Inserindo valores no objeto
		reserva.setDataInicialReserva(new Date()); //Inserindo valores no objeto		
		reserva.setDataFinalReserva(new Date()); //Inserindo valores no objeto
		reserva.setPatrimonioRecursoReserva(recurso.getPatrimonioRecurso());
		
		reservaDAO.editar(reserva); //Executando o metodo Editar na vari�vel 
	}
	
	@Test
	@Ignore
	public void buscarReservaPorUsuario(){
		ReservaDAO reservaDAO = new ReservaDAO();
		List<Reserva> reservasUsuario = reservaDAO.buscarReservaPorUsuario(27L);
		
		for (Reserva reserva : reservasUsuario) {
			System.out.println(reserva);
		}
	}
	
	@Test
	@Ignore
	public void buscarReservaPorRecurso(){
		ReservaDAO reservaDAO = new ReservaDAO();
		List<Reserva> recrusosReserva = reservaDAO.buscarPorCodigoRecurso("NT2490RTC01");
		
		for (Reserva reserva : recrusosReserva) {
			System.out.println(reserva);
		}
	}
	
	@Test //Anota��o informando que � uma classe de teste
	//@Ignore //Anota��o para informar que a clesse vai ser ignorada no teste	
	public void buscarPorUnidade(){
		ReservaDAO reservaDAO = new ReservaDAO();
		List<Reserva> reservas = reservaDAO.buscarPorUnidade(2L);
		
		for (Reserva reserva : reservas) {
			System.out.println(reserva);
		}
	}
}
