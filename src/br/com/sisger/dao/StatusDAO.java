package br.com.sisger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sisger.controle.HibernateUtil;
import br.com.sisger.modelo.Status;

public class StatusDAO {
	//Método para Salvar Objeto
	public void salvar(Status status){
		//Pegando uma Secao para fazer transação dos Dados
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Usando Transação para confirmar se Operação foi executada corretamente
		try { //Testando execução do Comando
			transacao = secao.beginTransaction(); //Criando uma transação
			secao.save(status); //Executando o Comando de Salvamento dentro da variável sessão
			transacao.commit(); //Confirmando a Transação
			
		} catch (RuntimeException ex) { //Capturando Erro caso ocorra
			if (transacao != null) { //Verificando se Transação foi executada
				transacao.rollback(); //Desfazendo Parte de transação executada.
			}
			throw ex; //Propagando o tratamento do Erro.
		}finally{ //Comando executado em ambas verificações
			secao.close(); //Fechando a Sessão
		}
		
	}

	@SuppressWarnings("unchecked") //Corrigindo solicitação de Cast por return de lista de estado generica
	public List<Status> listar(){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Status> status = null;  //Criando Lista para receber o resultado da consulta SQL
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Status.listar");
			status = consulta.list(); //Variável recebendo uma lista de resultado da consulta
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally {
			secao.close();
		}
		return status; //Retornando uma lista de resultados	
		
		
	}
	
	//Método usado para buscar o Status por código
	public Status buscarPorCodigo(Long codStatus){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Status status = null;
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Status.buscarPorCodigo");
			consulta.setLong("codStatus", codStatus); //Passando Valores para comparação da busca
						
			status = (Status) consulta.uniqueResult(); //Passando o resultado da busca para a variável.
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return status; //Retornando Status
	}
	
	//Método usado para buscar o Status por Intervalo de codigo
	@SuppressWarnings("unchecked")
	public List<Status> buscarPorInterv(Long codStatus, Long codStatusM){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Status> listaStatusIntervalo = null;
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Status.buscarPorInterv");
			consulta.setLong("codStatus", codStatus); //Passando Valores para comparação da busca
			consulta.setLong("codStatusM", codStatusM);
						
			listaStatusIntervalo = consulta.list(); //Passando o resultado da busca para a variável.
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return listaStatusIntervalo; //Retornando Lista de Status
	
	}
	
	public void excluir(Status status){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Criando uma Transação nula
		try {
			transacao = secao.beginTransaction(); //Iniciando uma Transação
			secao.delete(status); //Executando o Comando de Deletar dentro da variável sessão
			transacao.commit();
			
		} catch (RuntimeException ex) {
			if (transacao != null) { //Verificando se transaão foi iniciada
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
