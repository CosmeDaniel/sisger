# sisger
Sistema desenvolvido em JAVA EE, Base de dados MySQL e Primefaces.
Sistema faz a gestão de reservas de recursos para empresas.
Trabalho de conclusão do curso de Análise e Desenvolvimento de Sistemas.
Faculdade Pitágoras:
Autor: Cosme Daniel Moreira RA: 4203789898 - 5º Período.
RECURSOS NECESSÁRIOS:
Java - JRE.1.8
Banco de Dados – MySQL 5.6 ou  superior.
Browser – Mozilla (Preferencial), Internet Explore, Chrome ou Opera.
Servidor Web.
CONFIGURAÇÕES:
1 – Base de dados:
1.1	– Acesse o MySQL crie uma base de dados com o nome que escolher.
1.2	– Acesse a pasta do sistema.
Sisger\src
1.3	– Abra o arquivo hibernate.cfg.xml em qualquer editor de texto.
1.4	– Altere as seguintes linhas
	<!-- Configurações de Conexão -->
	<property name="connection.driver_class">com.mysql.jdbc.Driver</property>
	<property name="connection.url">jdbc:mysql://localhost:3306/NOME BASE DE 			DADOS</property>
	<property name="connection.username">USUÁRIO DO BANCO</property>
	<property name="connection.password">SENHA DO BANCO</property> 

	<!-- Criar as Tabelas -->
	<property name="hbm2ddl.auto">validate</property> Altere VALIDATE Para CREATE.
1.5	– Apóes a primeira execução do sistema, desfazer alteração de CREATE para VALIDATE. Pois tabelas necessárias já estarão criadas na base de dados.

2	- Carregar backup base de dados.
2.1	– Acesse a paste bd_Sisger e carregue os backup para as tabelas geradas pelo hibernate.cfg.xml.

3	- Primeiro acesso:
3.1	– Acesse o sistema com: Usuário: gestor, Senha: 12345678.	

