package br.com.sisger.controle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//Criação de classe responsável por pré carregar as sessões do Hibernate
//Fazendo com que a aplicação melhore o desempenho
//Implementando uma interface para a classe ServletContex
public class FabricaSessao implements ServletContextListener{

	
	//Método chamado ao Finalizar o sevidor da aplicação, responsável por destruir todas as sessões
	@Override
	public void contextDestroyed(ServletContextEvent ses) {
		//Fechando as fabrica de sessão criada.
		HibernateUtil.getSessionFactory().close();
		
	}

	//Método chamado ao Iniciar o sevidor da aplicação, responsável por carregar todas as sessões
	@Override
	public void contextInitialized(ServletContextEvent ses) {
		//Solicitando ao Hibernate uma criação de sessão nova
		HibernateUtil.getSessionFactory().openSession();
		
	}
	
	
	
}
