package br.com.sisger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sisger.controle.HibernateUtil;
import br.com.sisger.modelo.SubCategoria;

public class SubCategoriaDAO {
	//Método para Salvar Objeto
	public void salvar(SubCategoria subCategoria){
		//Pegando uma Secao para fazer transação dos Dados		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Usando Transação para confirmar se Operação foi executada corretamente
		try { //Testando execução do Comando
			transacao = secao.beginTransaction(); //Criando uma transação	
			secao.save(subCategoria); //Executando o Comando de Salvamento dentro da variável sessão
			
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
	public List<SubCategoria> listar(){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<SubCategoria>subCategorias = null; //Criando uma lista para receber valores
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("SubCategoria.listar");
			subCategorias = consulta.list(); //Variável recebendo uma lista de resultado da consulta
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return subCategorias; //Valor retornado pela função
	
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
