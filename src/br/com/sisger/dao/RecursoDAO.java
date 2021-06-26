package br.com.sisger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sisger.controle.HibernateUtil;
import br.com.sisger.modelo.Recurso;

public class RecursoDAO {
	//M�todo para Salvar Objeto
	public void salvar(Recurso recurso){
		//Pegando uma Secao para fazer transa��o dos Dados	
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Usando Transa��o para confirmar se Opera��o foi executada corretamente
		try { //Testando execu��o do Comando
			transacao = secao.beginTransaction(); //Criando uma transa��o
			secao.save(recurso); //Executando o Comando de Salvamento dentro da vari�vel sess�o
			
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
	public List<Recurso> listar(){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Recurso>recursos = null; //Criando uma lista para receber valores
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Recurso.listar");
			recursos = consulta.list(); //Vari�vel recebendo uma lista de resultado da consulta
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return recursos; //Valor retornado pela fun��o
	}
	
	@SuppressWarnings("unchecked") //Corrigindo solicita��o por return de lista de estado generica
	public List<Recurso> listarLiberados(Long codStatus) {
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Recurso> recursos = null; //Criando uma lista para receber valores
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Recurso.listarLiberados");
			consulta.setLong("codStatus", codStatus);
			recursos = consulta.list(); //Vari�vel recebendo uma lista de resultado da consulta
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return recursos; //Valor retornado pela fun��o	
	}
	
	@SuppressWarnings("unchecked") //Corrigindo solicita��o por return de lista de estado generica
	public List<Recurso> listarLiberadosUnidade(Long codStatus, Long codUnidade) {
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Recurso> recursos = null; //Criando uma lista para receber valores
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Recurso.listarLiberadosUnidade");
			consulta.setLong("codStatus", 6L);
			consulta.setLong("codUnidade", codUnidade);
			recursos = consulta.list(); //Vari�vel recebendo uma lista de resultado da consulta
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return recursos; //Valor retornado pela fun��o	
	}
	
	public Recurso buscarPorCodigo(String numSerieRecurso){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Recurso recurso = null;
		try {
		Query consulta = secao.getNamedQuery("Recurso.buscarPorCodigo");
		consulta.setString("numSerieRecurso", numSerieRecurso);
		recurso = (Recurso) consulta.uniqueResult();
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		
		return recurso;
	}

	public void excluir(Recurso recurso){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = secao.beginTransaction();
			secao.delete(recurso);
			
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
	
	public void editar(Recurso recurso) {
		Session secao  = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = secao.beginTransaction();
			secao.update(recurso);
			
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
	
	@SuppressWarnings("unchecked")
	public List<Recurso> recursosUnidade(Long codUnidade){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Recurso> recursosUnidade = null;
		try {
			Query consulta = sessao.getNamedQuery("Recurso.buscarPorUnidade");
			consulta.setLong("codUnidade", codUnidade);
			recursosUnidade = consulta.list();
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			sessao.close();
		}
		return recursosUnidade;		
	}
}
