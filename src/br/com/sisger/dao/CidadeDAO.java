package br.com.sisger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sisger.controle.HibernateUtil;
import br.com.sisger.modelo.Cidade;

public class CidadeDAO {
	//M�todo para Salvar Objeto
	public void salvar(Cidade cidade){
		//Pegando uma Secao para fazer transa��o dos Dados
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Usando Transa��o para confirmar se Opera��o foi executada corretamente
		
		try { //Testando execu��o do Comando
			transacao = secao.beginTransaction(); //Criando uma transa��o	
			secao.save(cidade); //Executando o Comando de Salvamento dentro da vari�vel sess�o
			
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
	public List<Cidade> listar(){ 
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Cidade> cidades = null; //Criando uma lista para receber valores
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Cidade.listar");
			cidades = consulta.list(); //Vari�vel recebendo uma lista de resultado da consulta
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return cidades;
	}
	
	public Cidade buscarPorCodigo(Long codCidade){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Cidade cidade = null;
		try {
			Query cosulta = secao.getNamedQuery("Cidade.buscarPorCodigo");
			cosulta.setLong("codCidade", codCidade); //Passando Valores para compara��o da busca
			
			cidade = (Cidade) cosulta.uniqueResult(); //Passando o resultado da busca para a vari�vel.
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return cidade;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cidade> buscarPorCodigoEstado(Long codEstado){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Cidade> cidades = null;
		try {
			Query consulta = sessao.getNamedQuery("Cidade.buscarPorCodigoEstado");
			consulta.setLong("codEstado", codEstado);
			
			cidades = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			sessao.close();			
		}
		return cidades;
	}

	public void excluir(Cidade cidade){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = secao.beginTransaction();
			secao.delete(cidade);
			
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

	public void editar(Cidade cidade){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = secao.beginTransaction();
			secao.update(cidade);
			
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
