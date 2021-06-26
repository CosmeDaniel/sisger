package br.com.sisger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sisger.controle.HibernateUtil;
import br.com.sisger.modelo.Usuario;

public class UsuarioDAO {
	//M�todo para Salvar Objeto
	public void salvar(Usuario usuario){
		//Pegando uma Secao para fazer transa��o dos Dados
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Usando Transa��o para confirmar se Opera��o foi executada corretamente
		try { //Testando execu��o do Comando
			transacao = secao.beginTransaction(); //Criando uma transa��o	
			secao.save(usuario); //Executando o Comando de Salvamento dentro da vari�vel sess�o
			
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
	public List<Usuario> listar(){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Usuario> usuarios = null; //Criando uma lista para receber valores
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Usuario.listar");
			usuarios = consulta.list(); //Vari�vel recebendo uma lista de resultado da consulta
						
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return usuarios; //Valor retornado pela fun��o	
	}

	public Usuario buscarPorCodigo(Long codUsuario){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Usuario usuario = null;
		try {
			Query cosulta = secao.getNamedQuery("Usuario.buscarPorCodigo");
			cosulta.setLong("codUsuario", codUsuario);
			
			usuario = (Usuario) cosulta.uniqueResult();
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return usuario;
	}

	public Usuario buscarPorCodigoString(String codUsuario){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Usuario usuario = null;
		try {
			Query cosulta = secao.getNamedQuery("Usuario.buscarPorCodigo");
			cosulta.setString("codUsuario", codUsuario);
			
			usuario = (Usuario) cosulta.uniqueResult();
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return usuario;
	}
	
	public Usuario buscarPorEmailString(String emailUsuario){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Usuario usuario = null;
		try {
			Query cosulta = secao.getNamedQuery("Usuario.buscarPorEmail");
			cosulta.setString("emailUsuario", emailUsuario);
			
			usuario = (Usuario) cosulta.uniqueResult();
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return usuario;
	}
	
	public void excluir(Usuario usuario){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = secao.beginTransaction();
			secao.delete(usuario);
			
			transacao.commit();
			
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.commit();
			}
			throw ex;
		}finally{
			secao.close();
		}
		
	}

	public void editar(Usuario usuario){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = secao.beginTransaction();
			secao.update(usuario);
			
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
	//M�todo que verifica Login e Senha dos Usu�rio para logar no sistema(Login e Senha passado por Par�metro)
	public Usuario autenticar(String loginUsuario, String senhaUsuario){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Usuario usuario = null;
		try {
			Query consulta = sessao.getNamedQuery("Usuario.autenticar");
			consulta.setString("loginUsuario", loginUsuario);
			consulta.setString("senhaUsuario", senhaUsuario);
			
			usuario = (Usuario) consulta.uniqueResult();
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			sessao.close();
		}
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> usuariosUnidade(Long codUnidade){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Usuario> usuariosUnidade = null;
		try {
			Query consulta = sessao.getNamedQuery("Usuario.buscarPorUnidade");
			consulta.setLong("codUnidade", codUnidade);
			usuariosUnidade = consulta.list();
			
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			sessao.close();
		}
		return usuariosUnidade;		
	}

}
