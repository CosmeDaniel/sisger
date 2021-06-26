package br.com.sisger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sisger.controle.HibernateUtil;
import br.com.sisger.modelo.Estado;

public class EstadoDAO {
	//M�todo para Salvar Objeto
	public void salvar(Estado estado){
		// Pegando uma Secao para fazer transa��o dos Dados
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; // Usando Transa��o para confirmar se Opera��o foi executada corretamente
		
		try { // Testando execu��o do Comando
			transacao = secao.beginTransaction(); //Criando uma transa��o		
			secao.save(estado); // Executando o Comando de Salvamento dentro da vari�vel sess�o
			transacao.commit(); //Confirmando a Transa��o

		} catch (RuntimeException ex) {	//Capturando Erro caso ocorra	 	
			if (transacao != null) { //Verificando se Transa��o foi executada
				transacao.rollback(); //Desfazendo Parte de transa��o executada.
				
			}
			throw ex; //Propagando o tratamento do Erro.
		}finally{ //Comando executado em ambas verifica��es
			secao.close(); //Fechando a Sess�o
			
		}

	}
	
	@SuppressWarnings("unchecked") //Corrigindo solicita��o de Cast por return de lista de estado generica
	public List<Estado> listar(){
		// Pegando uma Secao para fazer transa��o dos Dados
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Estado> estados = null; //Criando Lista para receber o resultado da consulta SQL
		try { // Testando execu��o do Comando
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Estado.listar");
			estados = consulta.list(); //Vari�vel recebendo uma lista de resultado da consulta
			
		} catch (RuntimeException ex) { //Capturando Erro caso ocorra
			throw ex; //Propagando o tratamento do Erro.
				
			}finally{ //Comando executado em ambas verifica��es
				secao.close(); //Fechando a sess�o
				
			}		
		return estados; //Retornando uma lista de resultados
	}
	
	public Estado buscarPorCodigo(Long codEstado){
		//Pegando uma secao para fazer a transa��o dos dodas
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Estado estado = null; //Criando uma vari�vel estado para receber o resultado da consulta
		
		try { // Testando execu��o do Comando
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Estado.buscarPorCodigo");
			consulta.setLong("codEstado", codEstado); //Passando Valores para compara��o da busca
					
			estado = (Estado) consulta.uniqueResult(); //Passando o resultado da busca para a vari�vel.
			
		} catch (RuntimeException ex) { //Capturando Erro caso ocorra
			throw ex; //Propagando o tratamento do Erro.
		
		}finally{ //Comando executado em ambas verifica��es
			secao.close(); //Fechando a sess�o
			
		}
		return estado; //Retornando estado
		
	}
		
	public void excluir(Estado estado){
		//Pegando uma secao para fazer a transa��o dos dodas
		Session secao = HibernateUtil.getSessionFactory().openSession();	
		Transaction transacao = null; // Criando uma Transa��o nula
		try { // Testando execu��o do Comando
			transacao = secao.beginTransaction(); //Criando uma transa��o
			secao.delete(estado); //Executando o Comando de Deletar dentro da vari�vel sess�o
			transacao.commit(); //Confirmando a Transa��o
			
		} catch (RuntimeException ex) { //Capturando Erro caso ocorra
			if (transacao != null) { //Verificando se transa�o foi iniciada
				transacao.rollback(); //Desfazendo passos realizados na transa��o
				
			}
			throw ex; //Propagando o tratamento do Erro.		
		}finally{ //Comando executado em ambas verifica��es
			secao.close();	//Fechando a sess�o		
		}

	}
	
	public void editar(Estado estado){
		//Pegando uma secao para fazer a transa��o dos dodas
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;	// Usando Transa��o para confirmar se Opera��o foi executada corretamente
		try { // Testando execu��o do Comando
			transacao = secao.beginTransaction(); //Criando uma transa��o.		
			secao.update(estado); //Executando o comando de edi��o
			transacao.commit(); //Confirmando a Transa��o
			
		} catch (RuntimeException ex) { //Capturando Erro caso ocorra
			if (transacao != null) { //Verificando se transa�o foi iniciada
				transacao.rollback(); //Desfazendo passos realizados na transa��o
			}
			throw ex; //Propagando o tratamento do Erro.
		}finally{ //Comando executado em ambas verifica��es
			secao.close(); //Fechando a sess�o
		}
	}
}		
