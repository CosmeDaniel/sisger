package br.com.sisger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sisger.controle.HibernateUtil;
import br.com.sisger.modelo.Status;

public class StatusDAO {
	//M�todo para Salvar Objeto
	public void salvar(Status status){
		//Pegando uma Secao para fazer transa��o dos Dados
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Usando Transa��o para confirmar se Opera��o foi executada corretamente
		try { //Testando execu��o do Comando
			transacao = secao.beginTransaction(); //Criando uma transa��o
			secao.save(status); //Executando o Comando de Salvamento dentro da vari�vel sess�o
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

	@SuppressWarnings("unchecked") //Corrigindo solicita��o de Cast por return de lista de estado generica
	public List<Status> listar(){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Status> status = null;  //Criando Lista para receber o resultado da consulta SQL
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Status.listar");
			status = consulta.list(); //Vari�vel recebendo uma lista de resultado da consulta
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally {
			secao.close();
		}
		return status; //Retornando uma lista de resultados	
		
		
	}
	
	//M�todo usado para buscar o Status por c�digo
	public Status buscarPorCodigo(Long codStatus){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Status status = null;
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Status.buscarPorCodigo");
			consulta.setLong("codStatus", codStatus); //Passando Valores para compara��o da busca
						
			status = (Status) consulta.uniqueResult(); //Passando o resultado da busca para a vari�vel.
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return status; //Retornando Status
	}
	
	//M�todo usado para buscar o Status por Intervalo de codigo
	@SuppressWarnings("unchecked")
	public List<Status> buscarPorInterv(Long codStatus, Long codStatusM){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Status> listaStatusIntervalo = null;
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Status.buscarPorInterv");
			consulta.setLong("codStatus", codStatus); //Passando Valores para compara��o da busca
			consulta.setLong("codStatusM", codStatusM);
						
			listaStatusIntervalo = consulta.list(); //Passando o resultado da busca para a vari�vel.
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return listaStatusIntervalo; //Retornando Lista de Status
	
	}
	
	public void excluir(Status status){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Criando uma Transa��o nula
		try {
			transacao = secao.beginTransaction(); //Iniciando uma Transa��o
			secao.delete(status); //Executando o Comando de Deletar dentro da vari�vel sess�o
			transacao.commit();
			
		} catch (RuntimeException ex) {
			if (transacao != null) { //Verificando se transa�o foi iniciada
				transacao.rollback();
			}
			throw ex;
		}finally{
			secao.close();
		}
		
		
	}

	public void editar(Status status){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = secao.beginTransaction();
			secao.update(status);
			
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
