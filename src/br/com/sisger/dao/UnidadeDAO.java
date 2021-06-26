package br.com.sisger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sisger.controle.HibernateUtil;
import br.com.sisger.modelo.Unidade;

public class UnidadeDAO {
	//Método para Salvar Objeto
	public void salvar(Unidade unidade){
		//Pegando uma Secao para fazer transação dos Dados	
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Usando Transação para confirmar se Operação foi executada corretamente
		try { //Testando execução do Comando
			transacao = secao.beginTransaction(); //Criando uma transação
			secao.save(unidade); //Executando o Comando de Salvamento dentro da variável sessão
			
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
	public List<Unidade> listar(){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Unidade> unidades = null; //Criando uma lista para receber valores
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Unidade.listar");
			unidades = consulta.list(); //Variável recebendo uma lista de resultado da consulta	
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return unidades; //Valor retornado pela função
	}
	
	public Unidade buscarPorCodigo(Long codUnidade){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Unidade unidade = null;
		try {
			Query consulta = secao.getNamedQuery("Unidade.buscarPorCodigo");
			consulta.setLong("codUnidade", codUnidade);
			
			unidade = (Unidade) consulta.uniqueResult();
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return unidade;
	}

	public void excluir(Unidade unidade){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = secao.beginTransaction();
			secao.delete(unidade);
						
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
		}finally{
			secao.close();
		}
		
	}

	public void editar(Unidade unidade){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = secao.beginTransaction();
			secao.update(unidade);
			
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
	public List<Unidade> buscarCampiUnidade(Long codCampiUnidade){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Unidade> campiUnidade = null;
		try {
			Query consulta = sessao.getNamedQuery("Unidade.CampiPorCodigo");
			consulta.setLong("codCampiUnidade", codCampiUnidade) ;
			campiUnidade = consulta.list();
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			sessao.close();
		}
		return campiUnidade;	
	}
}
