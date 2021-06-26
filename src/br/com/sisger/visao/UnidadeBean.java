package br.com.sisger.visao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.sisger.controle.FacesUtil;
import br.com.sisger.dao.EnderecoDAO;
import br.com.sisger.dao.UnidadeDAO;
import br.com.sisger.modelo.Endereco;
import br.com.sisger.modelo.Unidade;

//Definindo a classe como um Mange Bean
@ManagedBean
//Definindo o tempo de vida �til dentro da sess�o
@ViewScoped

// Criando um objeto e var�veis para receber dados tipo Unidade
public class UnidadeBean {
	private Unidade unidadeCadastro;
	List<Unidade> listaUnidades;
	List<Unidade> listaUnidadesGeral;
	List<Unidade> listaUnidadesFiltradas;
	private String acao;
	private Long codigo;
	private String acaoBotao;
	private String acaoBotaoEditar = null;
	private String acaoBotaoExcluir = "excluir";
	private Long gestorLogado;

	List<Endereco> listaEnderecos;
	List<Unidade> listaCampiUnidade;

	private Unidade selecaoUnidade;
	private int verifExclusao = 0;
	
	//Injetando Bean de autentica��o para acesso aos dados de quem est� logado no sistema(Inje��o de Depend�ncia)
	@ManagedProperty(value = "#{autenticacaoBean}")
	AutenticacaoBean autenticacaoBean;

	// Criando m�todos de acessos aos atributos do objetos criado, Gets e Sets
	public Unidade getSelecaoUnidade() {
		return selecaoUnidade;
	}

	public void setSelecaoUnidade(Unidade selecaoUnidade) {
		this.selecaoUnidade = selecaoUnidade;
		//Verifica��o vari�vel sele��o de unidade est� vazia(Se sim valor � atribu�do)
		if (selecaoUnidade != null) {
			// Recebendo C�digo da Unidade para consulta e exibi��o do endere�o da unidade
			selecaoUnidade.getCodUnidade();
		}

	}

	public synchronized Unidade getUnidadeCadastro() {
		// Verificando se obejto � nulo ao ser acessado, se nulo um novo objeto � inst�nciado
		if (unidadeCadastro == null) {
			unidadeCadastro = new Unidade();
		}
		return unidadeCadastro;
	}

	public synchronized void setUnidadeCadastro(Unidade unidadeCadastro) {
		this.unidadeCadastro = unidadeCadastro;
	}

	public synchronized List<Unidade> getListaUnidades() {
		return listaUnidades;
	}

	public synchronized void setListaUnidades(List<Unidade> listaUnidades) {
		this.listaUnidades = listaUnidades;
	}

	public synchronized List<Unidade> getListaUnidadesFiltradas() {

		return listaUnidadesFiltradas;
	}

	public synchronized void setListaUnidadesFiltradas(
			List<Unidade> listaUnidadesFiltradas) {
		this.listaUnidadesFiltradas = listaUnidadesFiltradas;
	}

	public synchronized String getAcao() {
		return acao;
	}

	public synchronized void setAcao(String acao) {
		this.acao = acao;
	}

	public synchronized Long getCodigo() {
		return codigo;
	}

	public synchronized void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public synchronized List<Endereco> getListaEnderecos() {
		return listaEnderecos;
	}

	public synchronized void setListaEnderecos(List<Endereco> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}

	public int getVerifExclusao() {
		return verifExclusao;
	}

	public void setVerifExclusao(int verifExclusao) {
		this.verifExclusao = verifExclusao;
	}

	public String getAcaoBotaoEditar() {
		return acaoBotaoEditar;
	}

	public void setAcaoBotaoEditar(String acaoBotaoEditar) {
		this.acaoBotaoEditar = acaoBotaoEditar;
	}

	public String getAcaoBotaoExcluir() {
		return acaoBotaoExcluir;
	}

	public void setAcaoBotaoExcluir(String acaoBotaoExcluir) {
		this.acaoBotaoExcluir = acaoBotaoExcluir;
	}

	public String getAcaoBotao() {
		return acaoBotao;
	}

	public void setAcaoBotao(String acaoBotao) {
		this.acaoBotao = acaoBotao;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public Long getGestorLogado() {
		return gestorLogado;
	}

	public void setGestorLogado(Long gestorLogado) {
		this.gestorLogado = gestorLogado;
	}

	public List<Unidade> getListaUnidadesGeral() {
		return listaUnidadesGeral;
	}

	public void setListaUnidadesGeral(List<Unidade> listaUnidadesGeral) {
		this.listaUnidadesGeral = listaUnidadesGeral;
	}

	public List<Unidade> getListaCampiUnidade() {
		return listaCampiUnidade;
	}

	public void setListaCampiUnidade(List<Unidade> listaCampiUnidade) {
		this.listaCampiUnidade = listaCampiUnidade;
	}

	// Criando M�todo Salvar da classe
	public void salvar() {
		// M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			// Executando executando m�todo salvar criado na Classe UnidadeDAO
			unidadeDAO.salvar(unidadeCadastro);

			// Executando m�todo de exibi��o de mensagem na Classe FacesUtil e criando mensagem
			FacesUtil.msgSucesso("Unidade Salva com Sucesso!");
			unidadeCadastro = new Unidade();
		
		//Caso haja algum erro ao executar Try O catch � executado emitindo uma menssagem de erro		
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de Salvar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Faculdade n�o pode ser Salvo, dados est�o em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem;	
				FacesUtil.msgErro("Erro ao Salvar Unidade!" + ex.getMessage());
			}
		}
	}

	// Criando M�todo Carregar as Pesquisas da classe
	public void carregarPesquisaGeral() {
		try {
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			// Executando executando m�todo listar criado na Classe UnidadeDAO
			listaUnidadesGeral = unidadeDAO.listar();
		
		//Caso haja algum erro ao executar Try O catch � executado emitindo uma menssagem de erro		
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();	
			FacesUtil.msgErro("Erro ao Listar Unidades Geral!" + ex.getMessage());
		}
	}

	// Criando M�todo para excutar o m�todo Listar contido na DAO
	public void carregarPesquisa() {
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			//Pegando Unidade logada no sistema para verificar qual n�vel de acesso e carregar os campi
			Long CodUnidade = autenticacaoBean.getUsuarioAutenticado().getUnidade().getCodUnidade();
			Unidade campiUnidadeLogada = autenticacaoBean.getUsuarioAutenticado().getUnidade().getUnidade();
			//Atribuindo codigo a vari�vel de verifica��o
			gestorLogado = CodUnidade;
			//Verificando se o campo do c�digo da Undade est� nulo e se o gestor logado possui o c�gido 5(Gestora do Sistema)
			if (CodUnidade != null && gestorLogado != 1L) {
				//Estanciando uma nova unidadeDAO para acesso aos metodos contidos na DAO
				UnidadeDAO unidadeDAO = new UnidadeDAO();
				//Realizando a busca da Unidade e atribuindo a uma lista
				listaUnidades = unidadeDAO.buscarCampiUnidade(CodUnidade);
			
			//Caso valida��o n�o ocorra	
			}if(campiUnidadeLogada == null){
				//Estanciando uma nova unidadeDAO para acesso aos metodos contidos na DAO
				UnidadeDAO unidadeDAO = new UnidadeDAO();
				//Realizando a busca da Unidade e atribuindo a uma lista
				listaUnidades = unidadeDAO.buscarCampiUnidade(CodUnidade);
				
			}if (gestorLogado == 1L){
				//Estanciando uma nova unidadeDAO para acesso aos metodos contidos na DAO
				UnidadeDAO unidadeDAO = new UnidadeDAO();
				//Criando uma lista com todos as Unidades do sistema
				listaUnidades = unidadeDAO.listar();
				//Atribuindo nulo para a vari�vel de verifica��o de unidade getora
				gestorLogado = null;
				//Estanciando uma nova Unidade
				unidadeCadastro = new Unidade();
			}
		//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();	
			FacesUtil.msgErro("Erro ao Carregar Pesquisa de Unidades!" + ex.getMessage());
		}
	}

	// Criando M�todo Carregar os Cadastros da classe
	public void carregarCadastro() {
		try {
			//Pegando a a��o do bot�o que � passada da p�gina via URL via parametro
			acaoBotao = FacesUtil.getParam("acaoBotao");

			// Pegando valor passado da p�gina de cadastro  via URL via parametro
			String valor = FacesUtil.getParam("codUnidade");

			// Executando verifica��o se vari�vel cont�m valores
			if (valor != null) {
				// Criando um vari�vel tempor�ria codigo e convertendo a var�vel valor
				codigo = Long.parseLong(valor);

				UnidadeDAO unidadeDAO = new UnidadeDAO();
				// Executando m�todo Buscar por C�digo criado na classe UnidadeDAO e inserindo em vari�vel
				unidadeCadastro = unidadeDAO.buscarPorCodigo(codigo);

			} else {
				unidadeCadastro = new Unidade();

			}
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			// Executando m�todo listar dentro da classe EnderecoDAO e carregando um lista de endere�os
			listaEnderecos = enderecoDAO.listar();

		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();	
			FacesUtil.msgErro("Erro ao tentar Carregar Cadastro da Unidade.");
		}

	}

	public void excluir() {
		try {
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			unidadeDAO.excluir(unidadeCadastro);

			verifExclusao = 1; // Vari�vel de cortrole de Exclus�o(Exclus�o ocorrendo recebe valor 1)
			FacesUtil.msgSucesso("Unidade Excluida com Sucesso!");

			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de exclus�o do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Faculdade n�o pode ser Exclu�da! Faculdade "+
				unidadeCadastro.getRazaoSocialUnidade() +" est� em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem;	
				FacesUtil.msgErro("Erro ao Excluir Unidade!" + ex.getMessage());
			}
		}

	}
	
	//M�todo que executa a edi��o das Unidades
	public void editar() {
		try {
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			
			//Verificando se c�digo na Unidade est� nullo no momento da Exclus�o
			if (unidadeCadastro.getCodUnidade() == null) {
				//Se for confirmado � setado c�digo na unidade contido na vari�vel c�digo
				unidadeCadastro.setCodUnidade(codigo);
			}
			//Executando m�todo editar da Unidade
			unidadeDAO.editar(unidadeCadastro);

			FacesUtil.msgSucesso("Unidade Editada com Sucesso!");
			unidadeCadastro = new Unidade();

		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de Editar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Faculdade n�o pode ser Editado, dados est�o em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem;	
				FacesUtil.msgErro("Erro ao Editar Unidade!" + ex.getMessage());
			}
		}
	}
}
