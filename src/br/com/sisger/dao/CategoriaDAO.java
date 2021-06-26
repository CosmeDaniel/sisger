package br.com.sisger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sisger.controle.HibernateUtil;
import br.com.sisger.modelo.Categoria;

public class CategoriaDAO {
	//M�todo para Salvar Objeto
	public void salvar(Categoria categoria){
		//Pegando uma Secao para fazer transa��o dos Dados
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Usando Transa��o para confirmar se Opera��o foi executada corretamente
		
		try { //Testando execu��o do Comando
			transacao = secao.beginTransaction(); //Criando uma transa��o	
			secao.save(categoria); //Executando o Comando de Salvamento dentro da vari�vel sess�o
			
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
	public List<Categoria> listar(){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Categoria> categorias = null;  //Criando Lista para receber o resultado da consulta SQL
		
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Categoria.listar");
			categorias = consulta.list(); //Vari�vel recebendo uma lista de resultado da consulta
			
		} catch (RuntimeException ex) {
			throw ex;
			
		}finally{
			secao.close();
			
		}
		return categorias; //Retornando uma lista de resultados	
	}
	
	public Categoria buscarPorCodigo(Long codCategoria){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Categoria categoria = null;
		
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Categoria.buscarPorCodigo");
			consulta.setLong("codCategoria", codCategoria); //Passando Valores para compara��o da busca
			
			categoria = (Categoria) consulta.uniqueResult(); //Passando o resultado da busca para a vari�vel.
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
			
		}
		return categoria; //Retornando Categoria
		
	}
	
	public void excluir(Categoria categoria){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Criando uma Transa��o nula
		try {
			transacao = secao.beginTransaction(); //Iniciando uma Transa��o
			secao.delete(categoria); //Executando o Comando de Deletar dentro da vari�vel secao
			transacao.commit();
			
		} catch (RuntimeException ex) {
			if (transacao != null) { //Verificando se transacao foi iniciada
				transacao.rollback();
			}
			throw ex;
			
		}finally{
			secao.close();
		}	
	}

	public void editar(Categoria categoria){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = secao.beginTransaction();
			secao.update(categoria);
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

	
