package br.com.sisger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sisger.controle.HibernateUtil;
import br.com.sisger.modelo.FuncaoUsuario;

public class FuncaoUsuarioDAO {
	public void salvar(FuncaoUsuario funcaoUsuario){ //M�todo para Salvar Objeto
		//Pegando uma Secao para fazer transa��o dos Dados
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Usando Transa��o para confirmar se Opera��o foi executada corretament
		try { //Testando execu��o do Comando
			transacao = secao.beginTransaction(); //Criando uma transa��o	
			secao.save(funcaoUsuario); //Executando o Comando de Salvamento dentro da vari�vel sess�o
			
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
	public List<FuncaoUsuario> listar(){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<FuncaoUsuario> funcaoUsuarios = null;  //Criando Lista para receber o resultado da consulta SQL
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("FuncaoUsuario.listar");
			funcaoUsuarios = consulta.list(); //Vari�vel recebendo uma lista de resultado da consulta
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return funcaoUsuarios; //Retornando uma lista de resultados	
		
	}
	
	//M�todo que busca uma Fun��o do Usu�rio pelo c�digo
	public FuncaoUsuario buscarPorCodigo(Long codFuncaoUsuario){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		FuncaoUsuario funcaoUsuario;
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consula = secao.getNamedQuery("FuncaoUsuario.buscarPorCodigo");
			consula.setLong("codFuncaoUsuario", codFuncaoUsuario); //Passando Valores para compara��o da busca
			
			funcaoUsuario = (FuncaoUsuario) consula.uniqueResult(); //Passando o resultado da busca para a vari�vel.
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return funcaoUsuario; //Retornando Fun��o		
	}

	//M�todo que Exclui uma Fun��o do Usu�rio
	public void excluir(FuncaoUsuario funcaoUsuario){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Criando uma Transa��o nula
		try {
			transacao = secao.beginTransaction(); //Iniciando uma Transa��o
			secao.delete(funcaoUsuario); //Executando o Comando de Deletar dentro da vari�vel secao
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

	//M�todo que Edita uma Fun��o do Usu�rio
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
	
	//M�todo que busca uma Lista de Fun��o do Usu�rio filtrando por C�digo
	@SuppressWarnings("unchecked")
	public List<FuncaoUsuario> buscarPorCodMai(Long codFuncaoUsuario){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<FuncaoUsuario> listaFuncaoUsuario;
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consula = secao.getNamedQuery("FuncaoUsuario.buscarPorCodMai");
			consula.setLong("codFuncaoUsuario", codFuncaoUsuario); //Passando Valores para compara��o da busca
			
			listaFuncaoUsuario = consula.list(); //Passando o resultado da busca para a vari�vel.
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return listaFuncaoUsuario; //Retornando Fun��o		
	}
}
