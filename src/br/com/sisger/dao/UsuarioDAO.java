package br.com.sisger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sisger.controle.HibernateUtil;
import br.com.sisger.modelo.Usuario;

public class UsuarioDAO {
	//Método para Salvar Objeto
	public void salvar(Usuario usuario){
		//Pegando uma Secao para fazer transação dos Dados
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; //Usando Transação para confirmar se Operação foi executada corretamente
		try { //Testando execução do Comando
			transacao = secao.beginTransaction(); //Criando uma transação	
			secao.save(usuario); //Executando o Comando de Salvamento dentro da variável sessão
			
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
	public List<Usuario> listar(){
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Usuario> usuarios = null; //Criando uma lista para receber valores
		try {
			//Criando uma Query SQL para chamar a consulta HQL(NamedQuery)
			Query consulta = secao.getNamedQuery("Usuario.listar");
			usuarios = consulta.list(); //Variável recebendo uma lista de resultado da consulta
						
		} catch (RuntimeException ex) {
			throw ex;
		}finally{
			secao.close();
		}
		return usuarios; //Valor retornado pela função	
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
	//Método que verifica Login e Senha dos Usuário para logar no sistema(Login e Senha passado por Parâmetro)
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
