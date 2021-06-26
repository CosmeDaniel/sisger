package br.com.sisger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sisger.controle.HibernateUtil;
import br.com.sisger.modelo.Cidade;

public class CidadeDAO {
	//Método para Salvar Objeto
	public void salvar(Cidade cidade){
		//Pegando uma Secao para fazer transação dos Dados
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Usando Transação para confirmar se Operação foi executada corretamente
		
		try { //Testando execução do Comando
			transacao = secao.beginTransaction(); //Criando uma transação	
			secao.save(cidade); //Executando o Comando de Salvamento dentro da variável sessão
			
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

	
	@SuppressWarnings("unchecked") //Corrigindo solicitação por return de lista de estado generica
	public List<Cidade> listar(){ 
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Cidade> cidades = null; //Criando uma lista para receber valores
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Cidade.listar");
			cidades = consulta.list(); //Variável recebendo uma lista de resultado da consulta
			
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
			cosulta.setLong("codCidade", codCidade); //Passando Valores para comparação da busca
			
			cidade = (Cidade) cosulta.uniqueResult(); //Passando o resultado da busca para a variável.
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
