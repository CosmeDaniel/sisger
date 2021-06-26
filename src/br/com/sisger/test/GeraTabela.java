package br.com.sisger.test;

import br.com.sisger.controle.HibernateUtil;

public class GeraTabela {

	//Classe para teste de Geração de tabelas no DB via Hibernate
	public static void main(String[] args) {
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
		
	}

}
