package br.com.sisger.visao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.sisger.controle.FacesUtil;
import br.com.sisger.dao.CidadeDAO;
import br.com.sisger.dao.EnderecoDAO;
import br.com.sisger.dao.EstadoDAO;
import br.com.sisger.modelo.Cidade;
import br.com.sisger.modelo.Endereco;
import br.com.sisger.modelo.Estado;

@ManagedBean // Definindo a classe como um Mange Bean
@ViewScoped // Definindo o tempo de vida �til dentro da sess�o
public class EnderecoBean {
	// Criando um objeto e var�veis para  receber dados	
	private Endereco enderecoCadastro;
	private List<Endereco> listaEnderecos;
	private List<Endereco> listaEnderecosFiltrados;
	private String acao;
	private Long codigo;
	
	private List<Cidade> listaCidades;
	private List<Estado> listaEstados;
	
	//Inserindo acesso aos dados contidos na classe UnidadeBean(Inje��o de Depend�ncia)
	@ManagedProperty(value = "#{unidadeBean}")
	private UnidadeBean unidadeBean;
	
	@ManagedProperty(value = "#{autenticacaoBean}")
	AutenticacaoBean autenticacaoBean;
 
	//Criando m�todos de acessos aos atributos do objeto criado, Gets e Sets
	public synchronized Endereco getEnderecoCadastro() {
		//Verificando se obejto � nulo ao ser acessado, se nulo um novo objeto � inst�nciado
		if (enderecoCadastro == null) {
			enderecoCadastro = new Endereco();
		}
		return enderecoCadastro;
	}

	public synchronized void setEnderecoCadastro(Endereco enderecoCadastro) {
		this.enderecoCadastro = enderecoCadastro;
	}

	public synchronized List<Endereco> getListaEnderecos() {
		return listaEnderecos;
	}

	public synchronized void setListaEnderecos(List<Endereco> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}

	public synchronized List<Endereco> getListaEnderecosFiltrados() {
		return listaEnderecosFiltrados;
	}

	public synchronized void setListaEnderecosFiltrados(
			List<Endereco> listaEnderecosFiltrados) {
		this.listaEnderecosFiltrados = listaEnderecosFiltrados;
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

	public synchronized List<Cidade> getListaCidades() {
		//Verificando se lista de cidade esta vazia toda vez que for acessada
		if (listaCidades == null) {
			//Se listaCidades estiver vazia um novo CidadeDAO ser� criada para carregar uma lista
			CidadeDAO cidadeDAO = new CidadeDAO();
			//Lista de cidade � carregada com dados do metodo listar em CidadeDAO
			listaCidades = cidadeDAO.listar();
		}
			return listaCidades;
	}

	public synchronized void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public List<Estado> getListaEstados() {
		return listaEstados;
	}
	public void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}  
	
	public UnidadeBean getUnidadeBean() {
		return unidadeBean;
	}
	
	public void setUnidadeBean(UnidadeBean unidadeBean) {
		this.unidadeBean = unidadeBean;
	}
	
	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}
		
	// Criando M�todo para excutar o m�todo Salvar contido na DAO
	public void salvar(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			//Criando uma novo EnderecoDAO para acesso aos metodos contidos na DAO
			EnderecoDAO enderecoDAO = new EnderecoDAO();

			//Inserindo dados do Endere�o cadastrado no Unidade(Atrav�s de Inje��o de Deped�ncia)
			unidadeBean.getUnidadeCadastro().setEndereco(enderecoCadastro);
			
			//Executando executando m�todo salvar criado na Classe DAO
			enderecoDAO.salvar(enderecoCadastro);
			//Estaciando um novo endere�o
			enderecoCadastro = new Endereco();			
			
		//Caso haja algum erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de Salvar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Endere�o n�o pode ser Salvo, dados j� cadastrados!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Salvar Endere�o." + ex.getMessage());
			}
		}
	}
	
	// Criando M�todo para excutar o m�todo Listar contido na DAO
	public void carregarPesquisa(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			//Criando uma novo EnderecoDAO para acesso aos metodos contidos na DAO
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			//Executando executando m�todo listar criado na Classe DAO
			listaEnderecos = enderecoDAO.listar();
			
			//Criando um novo EstadoDAO para carregar uma lista de estado para preencher o endere�o
			EstadoDAO estadoDAO = new EstadoDAO();
			//Executando m�todo listar dentro da classe DAO e carregando um lista de Estados
			listaEstados = estadoDAO.listar();
		
		//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();			
			FacesUtil.msgErro("Erro ao Carregar Pesquisa das Unidades." + ex.getMessage());
		}		
	}
	
	// Criando M�todo para excutar o m�todo Buscar por C�digo contido na DAO
	public void carregarCadastro(){
		try {
			//Pegando Codigo do Endere�o passado da p�gina de cadastro e inserindo em uma vari�vel
			String codEnderecoBuscar = FacesUtil.getParam("codEndereco");
			
			//Executando verifica��o se vari�vel cont�m valores
			if (codEnderecoBuscar != null) {
				//inserindo o codigo do endere�o em vari�vel e convertendo o valor para Long
				codigo = Long.parseLong(codEnderecoBuscar);
				
				EnderecoDAO enderecoDAO = new EnderecoDAO();
				//Executando m�todo Buscar por C�digo criado na classe DAO e inserindo em objeto
				enderecoCadastro = enderecoDAO.buscarPorCodigo(codigo);

			} else {
				enderecoCadastro = new Endereco();
			}
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();		
			FacesUtil.msgErro("Erro ao tentar Carregar Cadastro da Unidade.");
		}
	}
	
	// Criando M�todo para excutar o m�todo Excluir contido na DAO
	public void excluir(){
		try {
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			enderecoDAO.excluir(enderecoCadastro);
			
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuindo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de exclus�o do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Endere�o n�o pode ser Exclu�do! Endere�o Est� em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Excluir Endere�o." + ex.getMessage());
			}
		}
	}
	
	// Criando M�todo para excutar o m�todo Editar contido na DAO
	public void editar(){
		try {
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			//Verificando se c�digo do ender�o se econtra nulo em chamada de edi��o
			if (enderecoCadastro.getCodEndereco() == null) {
				//Caso c�digo seja nulo codigo do endere�o e setado no cadastro
				enderecoCadastro.setCodEndereco(codigo);				
			}
			//Setando o endere�o do cadastro no atributo endere�o que existe na Unidade
			unidadeBean.getUnidadeCadastro().setEndereco(enderecoCadastro);
			enderecoDAO.editar(enderecoCadastro);
		
			enderecoCadastro = new Endereco();	
			
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de Editar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Endere�o n�o pode ser Editado, dados est� em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Editar Endere�o." + ex.getMessage());
			}
		}
	}
	
	//Executar comando Editar da Unidade e do Endere�o ao mesmo Tempo.(M�todo foi criado para n�o existir um endere�o sem Unidade)
	public void executaEditarEndUnid(){
		//Chamando m�todo editar do endere�o
		editar();
		//Chamando m�todo editar da unidade
		unidadeBean.editar();
		
	}
	
	//Executar comando Excuir da Unidade e do Endere�o ao mesmo Tempo.
	public void executaExcluirEndUnid(){
		//Executando Exclus�o da Unidade primeiro(Atrav�s de Inje��o de Depend�ncia)
		unidadeBean.excluir();
		//Verificando se vari�vel de controle teve valor alterado
		if (unidadeBean.getVerifExclusao() == 1) {
			excluir();//Se valor da vari�l foi alterada para 1 Exclus�o do Endere�o � realizada.
			
		} 				
	}
	
	//Executar comando Salvar da Unidade e do Endere�o ao mesmo Tempo.(M�todo foi criado para n�o existir um endere�o sem Unidade)
	public void executaSalvarEndUnid(){
		//Chamando m�todo salvar do endere�o
		salvar();
		//Executando m�todo salvar da unidade
		unidadeBean.salvar();
	}
	
	//M�todo para salvar o endere�o dos Campi das unidades
	public void executaSalvarEndCamp() {
		// M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			salvar();
			//Pegando Unidade que est� logada no sistema para salvar campi da mesma
			unidadeBean.getUnidadeCadastro().setUnidade(autenticacaoBean.getUsuarioAutenticado().getUnidade());
			// Executando executando m�todo salvar criado na Classe UnidadeDAO
			unidadeBean.salvar();

		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de Salvar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Endere�o do Campi n�o pode ser Salvo, dados j� cadastrados!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Salvar Endere�o do Campi." + ex.getMessage());
			}
		}
	}
	
	//M�todo que vai listar as cidades de acordo com o estado selecionado
	public void listaCidadesPorEstado(){	
		try {
			//Pegando oc�digo do estado selecionado passado a pagina
			Long estado = enderecoCadastro.getCidade().getEstado().getCodEstado();
			//Criando uma nova CidadeDao para e buscar uma lista de cidades
			CidadeDAO cidadeDAO = new CidadeDAO();
			//Carregando uma lista de cidade buscada pelo c�digo do estado para a listaCidades
			listaCidades = cidadeDAO.buscarPorCodigoEstado(estado);
			
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();			
			FacesUtil.msgErro("Erroao listar Cidade por Estados!" + ex.getMessage());
		}
	}
}
