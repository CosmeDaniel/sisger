<h1 align="center">
  SISGER – Sistema de Gestão de recursos
</h1>

<h3 align="center">
  Sistema desenvolvido em JAVA EE, Base de dados MySQL e Primefaces.
  Sistema faz a gestão de reservas de recursos para empresas.
  Trabalho de conclusão do curso de Análise e Desenvolvimento de Sistemas.
  Faculdade Pitágoras: Autor: Cosme Daniel Moreira - 5º Período.
</h3>

<p align="center">
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/cosmedaniel/sisger">

  <img alt="GitHub license" src="https://img.shields.io/github/license/cosmedaniel/sisger">

  <img alt="GitHub stars" src="https://img.shields.io/github/stars/cosmedaniel/sisger?style=social">
</p>

<p align="center">
  <a href="#funcionalidades">Funcionalidades</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#heavy_check_mark-recursos-necessários">Recursos necessários</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#arrow_down_small-clonando-o-repositório">Clonando o repositório</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#beginner-configurando-a-aplicação">Configurando a aplicação</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
</p>

### Definições de desenvolvimento de sistema.
  Tecnologias Aplicadas.
  Plataforma: Web
  Linguagem de desenvolvimento: Java EE, CSS, SQL, XHTML.
  Banco de Dados: My-Sql.
  IDE´S: Eclipse, PrimeFace.
  Segurança: Sistema de validação para aquisição de aplicação.

### Funcionalidades

  Sistema terá a finalidade de gerenciar todos os Recursos da Faculdade “impressoras, computadores, monitores etc...”, em estoque. Será instalada na empresa ou externamente,       controlando a reserva de todos os recursos, permitindo serem cadastradas todas as Faculdades e seus Campi; tendo total controle de todos os recursos reservados.
	Sistema dará aos usuários condições de solicitarem reservas de recursos.
  Acessos dos clientes serão via Internet ao sistema, sendo estes através de Login e Senha, estes fornecidos pela faculdade gestora do sistema, todos os acessos e solicitações     terão que gerar um histórico para controle, podendo este ser emitido por relatório.
	Sistema será capaz de gerar relatórios de todos os recursos, sistema também deve ser capaz de permitir a movimentação dos mesmos a qualquer momento, controlando também o         solicitante da ação que foi realizada, assim como a pessoa que a executou.
  Sistema deverá ser capaz de receber novo tipo de recursos que poderão ser cadastrados somente pela faculdade gestora do sistema e não pelos seus campi. 


### :heavy_check_mark: Recursos necessários

  - Java - JRE.1.8
  - Banco de Dados – MySQL 5.6 ou  superior.
  - Browser – Mozilla (Preferencial), Internet Explore, Chrome ou Opera.
  - Servidor Web.

### :arrow_down_small: Clonando o repositório
1. Pelo terminal, acesse o diretório em que deseja ter o repositório clonado e execute o comando a seguir.
```bash
# clonando o repositório
git clone https://github.com/cosmedaniel/sisger
```

### :beginner: Configurando a aplicação
```bash
  # 1 – Base de dados:
  # 1.1	– Acesse o MySQL crie uma base de dados com o nome que escolher.
  # 1.2	– Acesse a pasta do sistema.
    Sisger\src
  # 1.3	– Abra o arquivo hibernate.cfg.xml em qualquer editor de texto.
  # 1.4	– Altere as seguintes linhas
	  <!-- Configurações de Conexão -->
	  <property name="connection.driver_class">com.mysql.jdbc.Driver</property>
	  <property name="connection.url">jdbc:mysql://localhost:3306/NOME BASE DE 			DADOS</property>
	  <property name="connection.username">USUÁRIO DO BANCO</property>
	  <property name="connection.password">SENHA DO BANCO</property> 

	  <!-- Criar as Tabelas -->
	  <property name="hbm2ddl.auto">validate</property> Altere VALIDATE Para CREATE.
    
  # 1.5	– Apóes a primeira execução do sistema, desfazer alteração de CREATE para VALIDATE. Pois tabelas necessárias já estarão criadas na base de dados.
```
```bash
  # 2	- Carregar backup base de dados.
  # 2.1	– Acesse a paste bd_Sisger e carregue os backup para as tabelas geradas pelo hibernate.cfg.xml.
```
```bash
  # 3	- Primeiro acesso:
  # 3.1	– Acesse o sistema com: Usuário: gestor, Senha: 12345678.
```

### :wrench: Tecnologias | Ferramentas | Recursos

Esse projeto foi desenvolvido utilizando os seguintes recursos:

  -  [Java - JRE.1.8](https://java.com/pt-BR/download/);
  -  [Banco de Dados – MySQL 5.6 ou  superior](https://downloads.mysql.com/archives/community/?version=5.6.23);
  -  [Browser – Mozilla (Preferencial), Internet Explore, Chrome ou Opera](https://www.mozilla.org/pt-BR/firefox/new/);
  -  [Servidor Web];
---

Feito por Cosme Daniel :blue_heart: Contato: https://www.linkedin.com/in/cosmedmoreira/ :blush:
