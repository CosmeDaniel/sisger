package br.com.sisger.controle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//Cria��o de classe respons�vel por pr� carregar as sess�es do Hibernate
//Fazendo com que a aplica��o melhore o desempenho
//Implementando uma interface para a classe ServletContex
public class FabricaSessao implements ServletContextListener{

	
	//M�todo chamado ao Finalizar o sevidor da aplica��o, respons�vel por destruir todas as sess�es
	@Override
	public void contextDestroyed(ServletContextEvent ses) {
		//Fechando as fabrica de sess�o criada.
		HibernateUtil.getSessionFactory().close();
		
	}

	//M�todo chamado ao Iniciar o sevidor da aplica��o, respons�vel por carregar todas as sess�es
	@Override
	public void contextInitialized(ServletContextEvent ses) {
		//Solicitando ao Hibernate uma cria��o de sess�o nova
		HibernateUtil.getSessionFactory().openSession();
		
	}
	
	
	
}
