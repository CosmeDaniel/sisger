package br.com.sisger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sisger.controle.HibernateUtil;
import br.com.sisger.modelo.Estado;

public class EstadoDAO {
	//Método para Salvar Objeto
	public void salvar(Estado estado){
		// Pegando uma Secao para fazer transação dos Dados
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; // Usando Transação para confirmar se Operação foi executada corretamente
		
		try { // Testando execução do Comando
			transacao = secao.beginTransaction(); //Criando uma transação		
			secao.save(estado); // Executando o Comando de Salvamento dentro da variável sessão
			transacao.commit(); //Confirmando a Transação

		} catch (RuntimeException ex) {	//Capturando Erro caso ocorra	 	
			if (transacao != null) { //Verificando se Transação foi executada
				transacao.rollback(); //Desfazendo Parte de transação executada.
				
			}
			throw ex; //Propagando o tratamento do Erro.
		}finally{ //Comando executado em ambas verificações
			secao.close(); //Fechando a Sessão
			
		}

	}
	
	@SuppressWarnings("unchecked") //Corrigindo solicitação de Cast por return de lista de estado generica
	public List<Estado> listar(){
		// Pegando uma Secao para fazer transação dos Dados
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Estado> estados = null; //Criando Lista para receber o resultado da consulta SQL
		try { // Testando execução do Comando
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Estado.listar");
			estados = consulta.list(); //Variável recebendo uma lista de resultado da consulta
			
		} catch (RuntimeException ex) { //Capturando Erro caso ocorra
			throw ex; //Propagando o tratamento do Erro.
				
			}finally{ //Comando executado em ambas verificações
				secao.close(); //Fechando a sessão
				
			}		
		return estados; //Retornando uma lista de resultados
	}
	
	public Estado buscarPorCodigo(Long codEstado){
		//Pegando uma secao para fazer a transação dos dodas
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Estado estado = null; //Criando uma variável estado para receber o resultado da consulta
		
		try { // Testando execução do Comando
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Estado.buscarPorCodigo");
			consulta.setLong("codEstado", codEstado); //Passando Valores para comparação da busca
					
			estado = (Estado) consulta.uniqueResult(); //Passando o resultado da busca para a variável.
			
		} catch (RuntimeException ex) { //Capturando Erro caso ocorra
			throw ex; //Propagando o tratamento do Erro.
		
		}finally{ //Comando executado em ambas verificações
			secao.close(); //Fechando a sessão
			
		}
		return estado; //Retornando estado
		
	}
		
	public void excluir(Estado estado){
		//Pegando uma secao para fazer a transação dos dodas
		Session secao = HibernateUtil.getSessionFactory().openSession();	
		Transaction transacao = null; // Criando uma Transação nula
		try { // Testando execução do Comando
			transacao = secao.beginTransaction(); //Criando uma transação
			secao.delete(estado); //Executando o Comando de Deletar dentro da variável sessão
			transacao.commit(); //Confirmando a Transação
			
		} catch (RuntimeException ex) { //Capturando Erro caso ocorra
			if (transacao != null) { //Verificando se transaão foi iniciada
				transacao.rollback(); //Desfazendo passos realizados na transação
				
			}
			throw ex; //Propagando o tratamento do Erro.		
		}finally{ //Comando executado em ambas verificações
			secao.close();	//Fechando a sessão		
		}

	}
	
	public void editar(Estado estado){
		//Pegando uma secao para fazer a transação dos dodas
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;	// Usando Transação para confirmar se Operação foi executada corretamente
		try { // Testando execução do Comando
			transacao = secao.beginTransaction(); //Criando uma transação.		
			secao.update(estado); //Executando o comando de edição
			transacao.commit(); //Confirmando a Transação
			
		} catch (RuntimeException ex) { //Capturando Erro caso ocorra
			if (transacao != null) { //Verificando se transaão foi iniciada
				transacao.rollback(); //Desfazendo passos realizados na transação
			}
			throw ex; //Propagando o tratamento do Erro.
		}finally{ //Comando executado em ambas verificações
			secao.close(); //Fechando a sessão
		}
	}
}		
