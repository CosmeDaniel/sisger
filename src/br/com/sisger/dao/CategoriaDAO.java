package br.com.sisger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sisger.controle.HibernateUtil;
import br.com.sisger.modelo.Categoria;

public class CategoriaDAO {
	//Método para Salvar Objeto
	public void salvar(Categoria categoria){
		//Pegando uma Secao para fazer transação dos Dados
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Usando Transação para confirmar se Operação foi executada corretamente
		
		try { //Testando execução do Comando
			transacao = secao.beginTransaction(); //Criando uma transação	
			secao.save(categoria); //Executando o Comando de Salvamento dentro da variável sessão
			
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
	public List<Categoria> listar(){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Categoria> categorias = null;  //Criando Lista para receber o resultado da consulta SQL
		
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Categoria.listar");
			categorias = consulta.list(); //Variável recebendo uma lista de resultado da consulta
			
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
			consulta.setLong("codCategoria", codCategoria); //Passando Valores para comparação da busca
			
			categoria = (Categoria) consulta.uniqueResult(); //Passando o resultado da busca para a variável.
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
			
		}
		return categoria; //Retornando Categoria
		
	}
	
	public void excluir(Categoria categoria){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Criando uma Transação nula
		try {
			transacao = secao.beginTransaction(); //Iniciando uma Transação
			secao.delete(categoria); //Executando o Comando de Deletar dentro da variável secao
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

	
