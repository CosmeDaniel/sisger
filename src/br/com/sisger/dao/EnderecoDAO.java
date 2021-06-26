package br.com.sisger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sisger.controle.HibernateUtil;
import br.com.sisger.modelo.Endereco;

public class EnderecoDAO {

	//Método para Salvar Objeto
	public void salvar(Endereco endereco){
		//Pegando uma Secao para fazer transação dos Dados		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Usando Transação para confirmar se Operação foi executada corretamente
		try { //Testando execução do Comando
			transacao = secao.beginTransaction(); //Criando uma transação	
			secao.save(endereco); //Executando o Comando de Salvamento dentro da variável sessão
			
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
	public List<Endereco> listar(){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Endereco> enderecos = null; //Criando uma lista para receber valores
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Endereco.listar");
			enderecos = consulta.list(); //Variável recebendo uma lista de resultado da consulta
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return enderecos; //Valor retornado pela função
	}
	
	public Endereco buscarPorCodigo(Long codEndereco){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Endereco endereco = null;
		try {
			Query consulta = secao.getNamedQuery("Endereco.buscarPorCodigo");
			consulta.setLong("codEndereco", codEndereco);
			endereco = (Endereco) consulta.uniqueResult();
				
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return endereco;
	}
	
	public void excluir(Endereco endereco){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = secao.beginTransaction();
			secao.delete(endereco);
			
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

	public void editar(Endereco endereco){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = secao.beginTransaction();
			secao.update(endereco);
			
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
