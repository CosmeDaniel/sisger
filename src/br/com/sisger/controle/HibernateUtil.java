package br.com.sisger.controle;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
           //Cria uma Sess�o a partir do hibernate.cfg.xml
        	Configuration configuration = new Configuration();
           //Buscando as configur��es do arquivo hibernate.cfg.xml
        	configuration.configure();
        	
           //Criando o registro de servi�o com o Hibernate
           ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            		.applySettings(configuration.getProperties()).build();
           
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        	return sessionFactory;
        }
        catch (Throwable ex) {
        	//Exibe uma Mensagem de erro.
            System.err.println("Falha ao tentar criar uma SessionFactory" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
