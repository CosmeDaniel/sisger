package br.com.sisger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sisger.controle.HibernateUtil;
import br.com.sisger.modelo.SubCategoria;

public class SubCategoriaDAO {
	//M�todo para Salvar Objeto
	public void salvar(SubCategoria subCategoria){
		//Pegando uma Secao para fazer transa��o dos Dados		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Usando Transa��o para confirmar se Opera��o foi executada corretamente
		try { //Testando execu��o do Comando
			transacao = secao.beginTransaction(); //Criando uma transa��o	
			secao.save(subCategoria); //Executando o Comando de Salvamento dentro da vari�vel sess�o
			
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
	public List<SubCategoria> listar(){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<SubCategoria>subCategorias = null; //Criando uma lista para receber valores
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("SubCategoria.listar");
			subCategorias = consulta.list(); //Vari�vel recebendo uma lista de resultado da consulta
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return subCategorias; //Valor retornado pela fun��o
	
	}
	
	public SubCategoria buscarPorCodigo(Long codSubCategoria){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		SubCategoria subCategoria = null;
		try {
			Query consulta = secao.getNamedQuery("SubCategoria.buscarPorCodigo");
			consulta.setLong("codSubCategoria", codSubCategoria);
			subCategoria = (SubCategoria) consulta.uniqueResult();
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return subCategoria;		
	}

	public SubCategoria buscarPorNome(String nomeSubCategoria){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		SubCategoria subCategoria = null;
		try {
			Query consulta = secao.getNamedQuery("SubCategoria.buscarPorNome");
			consulta.setString("nomeSubCategoria", nomeSubCategoria);
			subCategoria = (SubCategoria) consulta.uniqueResult();
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return subCategoria;		
	}
	
	public void excluir(SubCategoria subCategoria){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = secao.beginTransaction();
			secao.delete(subCategoria);
			
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
	
	public void editar(SubCategoria subCategoria){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = secao.beginTransaction();
			secao.update(subCategoria);
			
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
