package br.com.sisger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sisger.controle.HibernateUtil;
import br.com.sisger.modelo.Reserva;

public class ReservaDAO {
	//M�todo para Salvar Objeto
	public void salvar(Reserva reserva){
		//Pegando uma Secao para fazer transa��o dos Dados	
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Usando Transa��o para confirmar se Opera��o foi executada corretamente
		try { //Testando execu��o do Comando
			transacao = secao.beginTransaction(); //Criando uma transa��o	
			secao.save(reserva); //Executando o Comando de Salvamento dentro da vari�vel sess�o
			
			transacao.commit(); //Confirmando a Transa��o
			
		} catch (RuntimeException ex) { //Capturando Erro caso ocorra
			if (transacao != null) { //Verificando se Transa��o foi executada
				transacao.rollback(); //Desfazendo Parte de transa��o executada.
			}
			throw ex; //Propagando o tratamento do Erro.
		}finally{ //Comando executado em ambas verifica��es
			secao.close(); //Fechando a Sess�o
		}
	}

	@SuppressWarnings("unchecked") //Corrigindo solicita��o por return de lista de estado generica
	public List<Reserva> listar() {
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Reserva> reservas = null; //Criando uma lista para receber valores
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Reserva.listar");
			reservas = consulta.list(); //Vari�vel recebendo uma lista de resultado da consulta
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return reservas; //Valor retornado pela fun��o	
	}
	
	public Reserva buscarPorCodigo(Long codReserva){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Reserva reserva = null;
		try {
			Query consulta = secao.getNamedQuery("Reserva.buscarPorCodigo");
			consulta.setLong("codReserva", codReserva);
			reserva = (Reserva) consulta.uniqueResult();
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();			
		}
		return reserva;
	}
	
	public Reserva buscarPorCodigoString(String codReserva){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Reserva reserva =  null;
		try {
			Query consulta = sessao.getNamedQuery("Reserva.buscarPorCodigo");
			consulta.setString("codReserva", codReserva);
			reserva = (Reserva) consulta.uniqueResult();
			
		} catch (RuntimeException ex) {
			throw ex;
		}
		return reserva;	
	}

	@SuppressWarnings("unchecked")
	public List<Reserva> buscarPorCodigoRecurso(String numSerieRecurso){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Reserva> recursosReserva = null;
		try {
			Query consulta = secao.getNamedQuery("Reserva.buscarPorCodigoRecurso");
			consulta.setString("numSerieRecurso", numSerieRecurso);
			recursosReserva =  consulta.list();
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();			
		}
		return recursosReserva;
	}
	
	@SuppressWarnings("unchecked")
	public List<Reserva> buscarReservaPorUsuario(Long codUsuario){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Reserva> reservaPorUsuario = null;
		try {
			Query consulta = sessao.getNamedQuery("Reserva.buscarReservaPorUsuario");
			consulta.setLong("codUsuario", codUsuario);
			reservaPorUsuario = consulta.list();
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			sessao.close();
		}
		return reservaPorUsuario;	
	}
	
	@SuppressWarnings("unchecked")
	public List<Reserva> buscarPorUnidade(Long codUnidade){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Reserva> reservasPorUnidade = null;
		try {
			Query consulta = secao.getNamedQuery("Reserva.buscarReservaPorUnidade");
			consulta.setLong("codUnidade", codUnidade);
			reservasPorUnidade = consulta.list();
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();			
		}
		return reservasPorUnidade;
	}	
	
	public void excluir(Reserva reserva){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = secao.beginTransaction();
			secao.delete(reserva);
			
			transacao.commit();
			
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		}finally{
			secao.close();
		}
	}

	public void editar(Reserva reserva){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = secao.beginTransaction();
			secao.update(reserva);
			
			transacao.commit();
			
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		}finally{
			secao.close();
		}
	}
}
