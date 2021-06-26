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
//Definindo o tempo de vida útil dentro da sessão
@ViewScoped

// Criando um objeto e varáveis para receber dados tipo Unidade
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
	
	//Injetando Bean de autenticação para acesso aos dados de quem está logado no sistema(Injeção de Dependência)
	@ManagedProperty(value = "#{autenticacaoBean}")
	AutenticacaoBean autenticacaoBean;

	// Criando métodos de acessos aos atributos do objetos criado, Gets e Sets
	public Unidade getSelecaoUnidade() {
		return selecaoUnidade;
	}

	public void setSelecaoUnidade(Unidade selecaoUnidade) {
		this.selecaoUnidade = selecaoUnidade;
		//Verificação variável seleção de unidade está vazia(Se sim valor é atribuído)
		if (selecaoUnidade != null) {
			// Recebendo Código da Unidade para consulta e exibição do endereço da unidade
			selecaoUnidade.getCodUnidade();
		}

	}

	public synchronized Unidade getUnidadeCadastro() {
		// Verificando se obejto é nulo ao ser acessado, se nulo um novo objeto é instânciado
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

	// Criando Método Salvar da classe
	public void salvar() {
		// Método obrigatório para garantir teste antes da execução dos comandos
		try {
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			// Executando executando método salvar criado na Classe UnidadeDAO
			unidadeDAO.salvar(unidadeCadastro);

			// Executando método de exibição de mensagem na Classe FacesUtil e criando mensagem
			FacesUtil.msgSucesso("Unidade Salva com Sucesso!");
			unidadeCadastro = new Unidade();
		
		//Caso haja algum erro ao executar Try O catch é executado emitindo uma menssagem de erro		
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de Salvar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Faculdade não pode ser Salvo, dados estão em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem;	
				FacesUtil.msgErro("Erro ao Salvar Unidade!" + ex.getMessage());
			}
		}
	}

	// Criando Método Carregar as Pesquisas da classe
	public void carregarPesquisaGeral() {
		try {
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			// Executando executando método listar criado na Classe UnidadeDAO
			listaUnidadesGeral = unidadeDAO.listar();
		
		//Caso haja algum erro ao executar Try O catch é executado emitindo uma menssagem de erro		
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();	
			FacesUtil.msgErro("Erro ao Listar Unidades Geral!" + ex.getMessage());
		}
	}

	// Criando Método para excutar o método Listar contido na DAO
	public void carregarPesquisa() {
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			//Pegando Unidade logada no sistema para verificar qual nível de acesso e carregar os campi
			Long CodUnidade = autenticacaoBean.getUsuarioAutenticado().getUnidade().getCodUnidade();
			Unidade campiUnidadeLogada = autenticacaoBean.getUsuarioAutenticado().getUnidade().getUnidade();
			//Atribuindo codigo a variável de verificação
			gestorLogado = CodUnidade;
			//Verificando se o campo do código da Undade está nulo e se o gestor logado possui o cógido 5(Gestora do Sistema)
			if (CodUnidade != null && gestorLogado != 1L) {
				//Estanciando uma nova unidadeDAO para acesso aos metodos contidos na DAO
				UnidadeDAO unidadeDAO = new UnidadeDAO();
				//Realizando a busca da Unidade e atribuindo a uma lista
				listaUnidades = unidadeDAO.buscarCampiUnidade(CodUnidade);
			
			//Caso validação não ocorra	
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
				//Atribuindo nulo para a variável de verificação de unidade getora
				gestorLogado = null;
				//Estanciando uma nova Unidade
				unidadeCadastro = new Unidade();
			}
		//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();	
			FacesUtil.msgErro("Erro ao Carregar Pesquisa de Unidades!" + ex.getMessage());
		}
	}

	// Criando Método Carregar os Cadastros da classe
	public void carregarCadastro() {
		try {
			//Pegando a ação do botão que é passada da página via URL via parametro
			acaoBotao = FacesUtil.getParam("acaoBotao");

			// Pegando valor passado da página de cadastro  via URL via parametro
			String valor = FacesUtil.getParam("codUnidade");

			// Executando verificação se variável contém valores
			if (valor != null) {
				// Criando um variável temporária codigo e convertendo a varável valor
				codigo = Long.parseLong(valor);

				UnidadeDAO unidadeDAO = new UnidadeDAO();
				// Executando método Buscar por Código criado na classe UnidadeDAO e inserindo em variável
				unidadeCadastro = unidadeDAO.buscarPorCodigo(codigo);

			} else {
				unidadeCadastro = new Unidade();

			}
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			// Executando método listar dentro da classe EnderecoDAO e carregando um lista de endereços
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

			verifExclusao = 1; // Variável de cortrole de Exclusão(Exclusão ocorrendo recebe valor 1)
			FacesUtil.msgSucesso("Unidade Excluida com Sucesso!");

			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de exclusão do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Faculdade não pode ser Excluída! Faculdade "+
				unidadeCadastro.getRazaoSocialUnidade() +" está em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem;	
				FacesUtil.msgErro("Erro ao Excluir Unidade!" + ex.getMessage());
			}
		}

	}
	
	//Método que executa a edição das Unidades
	public void editar() {
		try {
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			
			//Verificando se código na Unidade está nullo no momento da Exclusão
			if (unidadeCadastro.getCodUnidade() == null) {
				//Se for confirmado é setado código na unidade contido na variável código
				unidadeCadastro.setCodUnidade(codigo);
			}
			//Executando método editar da Unidade
			unidadeDAO.editar(unidadeCadastro);

			FacesUtil.msgSucesso("Unidade Editada com Sucesso!");
			unidadeCadastro = new Unidade();

		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de Editar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Faculdade não pode ser Editado, dados estão em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem;	
				FacesUtil.msgErro("Erro ao Editar Unidade!" + ex.getMessage());
			}
		}
	}
}
