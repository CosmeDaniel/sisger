package br.com.sisger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sisger.controle.HibernateUtil;
import br.com.sisger.modelo.FuncaoUsuario;

public class FuncaoUsuarioDAO {
	public void salvar(FuncaoUsuario funcaoUsuario){ //Método para Salvar Objeto
		//Pegando uma Secao para fazer transação dos Dados
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Usando Transação para confirmar se Operação foi executada corretament
		try { //Testando execução do Comando
			transacao = secao.beginTransaction(); //Criando uma transação	
			secao.save(funcaoUsuario); //Executando o Comando de Salvamento dentro da variável sessão
			
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
	public List<FuncaoUsuario> listar(){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<FuncaoUsuario> funcaoUsuarios = null;  //Criando Lista para receber o resultado da consulta SQL
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("FuncaoUsuario.listar");
			funcaoUsuarios = consulta.list(); //Variável recebendo uma lista de resultado da consulta
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return funcaoUsuarios; //Retornando uma lista de resultados	
		
	}
	
	//Método que busca uma Função do Usuário pelo código
	public FuncaoUsuario buscarPorCodigo(Long codFuncaoUsuario){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		FuncaoUsuario funcaoUsuario;
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consula = secao.getNamedQuery("FuncaoUsuario.buscarPorCodigo");
			consula.setLong("codFuncaoUsuario", codFuncaoUsuario); //Passando Valores para comparação da busca
			
			funcaoUsuario = (FuncaoUsuario) consula.uniqueResult(); //Passando o resultado da busca para a variável.
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return funcaoUsuario; //Retornando Função		
	}

	//Método que Exclui uma Função do Usuário
	public void excluir(FuncaoUsuario funcaoUsuario){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Criando uma Transação nula
		try {
			transacao = secao.beginTransaction(); //Iniciando uma Transação
			secao.delete(funcaoUsuario); //Executando o Comando de Deletar dentro da variável secao
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

	//Método que Edita uma Função do Usuário
	public void editar(FuncaoUsuario funcaoUsuario){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = secao.beginTransaction();
			secao.update(funcaoUsuario);
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
	
	//Método que busca uma Lista de Função do Usuário filtrando por Código
	@SuppressWarnings("unchecked")
	public List<FuncaoUsuario> buscarPorCodMai(Long codFuncaoUsuario){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<FuncaoUsuario> listaFuncaoUsuario;
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consula = secao.getNamedQuery("FuncaoUsuario.buscarPorCodMai");
			consula.setLong("codFuncaoUsuario", codFuncaoUsuario); //Passando Valores para comparação da busca
			
			listaFuncaoUsuario = consula.list(); //Passando o resultado da busca para a variável.
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return listaFuncaoUsuario; //Retornando Função		
	}
}
