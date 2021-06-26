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
@ViewScoped // Definindo o tempo de vida útil dentro da sessão
public class EnderecoBean {
	// Criando um objeto e varáveis para  receber dados	
	private Endereco enderecoCadastro;
	private List<Endereco> listaEnderecos;
	private List<Endereco> listaEnderecosFiltrados;
	private String acao;
	private Long codigo;
	
	private List<Cidade> listaCidades;
	private List<Estado> listaEstados;
	
	//Inserindo acesso aos dados contidos na classe UnidadeBean(Injeção de Dependência)
	@ManagedProperty(value = "#{unidadeBean}")
	private UnidadeBean unidadeBean;
	
	@ManagedProperty(value = "#{autenticacaoBean}")
	AutenticacaoBean autenticacaoBean;
 
	//Criando métodos de acessos aos atributos do objeto criado, Gets e Sets
	public synchronized Endereco getEnderecoCadastro() {
		//Verificando se obejto é nulo ao ser acessado, se nulo um novo objeto é instânciado
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
			//Se listaCidades estiver vazia um novo CidadeDAO será criada para carregar uma lista
			CidadeDAO cidadeDAO = new CidadeDAO();
			//Lista de cidade é carregada com dados do metodo listar em CidadeDAO
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
		
	// Criando Método para excutar o método Salvar contido na DAO
	public void salvar(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			//Criando uma novo EnderecoDAO para acesso aos metodos contidos na DAO
			EnderecoDAO enderecoDAO = new EnderecoDAO();

			//Inserindo dados do Endereço cadastrado no Unidade(Através de Injeção de Depedência)
			unidadeBean.getUnidadeCadastro().setEndereco(enderecoCadastro);
			
			//Executando executando método salvar criado na Classe DAO
			enderecoDAO.salvar(enderecoCadastro);
			//Estaciando um novo endereço
			enderecoCadastro = new Endereco();			
			
		//Caso haja algum erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de Salvar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Endereço não pode ser Salvo, dados já cadastrados!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Salvar Endereço." + ex.getMessage());
			}
		}
	}
	
	// Criando Método para excutar o método Listar contido na DAO
	public void carregarPesquisa(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			//Criando uma novo EnderecoDAO para acesso aos metodos contidos na DAO
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			//Executando executando método listar criado na Classe DAO
			listaEnderecos = enderecoDAO.listar();
			
			//Criando um novo EstadoDAO para carregar uma lista de estado para preencher o endereço
			EstadoDAO estadoDAO = new EstadoDAO();
			//Executando método listar dentro da classe DAO e carregando um lista de Estados
			listaEstados = estadoDAO.listar();
		
		//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();			
			FacesUtil.msgErro("Erro ao Carregar Pesquisa das Unidades." + ex.getMessage());
		}		
	}
	
	// Criando Método para excutar o método Buscar por Código contido na DAO
	public void carregarCadastro(){
		try {
			//Pegando Codigo do Endereço passado da página de cadastro e inserindo em uma variável
			String codEnderecoBuscar = FacesUtil.getParam("codEndereco");
			
			//Executando verificação se variável contém valores
			if (codEnderecoBuscar != null) {
				//inserindo o codigo do endereço em variável e convertendo o valor para Long
				codigo = Long.parseLong(codEnderecoBuscar);
				
				EnderecoDAO enderecoDAO = new EnderecoDAO();
				//Executando método Buscar por Código criado na classe DAO e inserindo em objeto
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
	
	// Criando Método para excutar o método Excluir contido na DAO
	public void excluir(){
		try {
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			enderecoDAO.excluir(enderecoCadastro);
			
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuindo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de exclusão do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Endereço não pode ser Excluído! Endereço Está em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Excluir Endereço." + ex.getMessage());
			}
		}
	}
	
	// Criando Método para excutar o método Editar contido na DAO
	public void editar(){
		try {
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			//Verificando se código do enderço se econtra nulo em chamada de edição
			if (enderecoCadastro.getCodEndereco() == null) {
				//Caso código seja nulo codigo do endereço e setado no cadastro
				enderecoCadastro.setCodEndereco(codigo);				
			}
			//Setando o endereço do cadastro no atributo endereço que existe na Unidade
			unidadeBean.getUnidadeCadastro().setEndereco(enderecoCadastro);
			enderecoDAO.editar(enderecoCadastro);
		
			enderecoCadastro = new Endereco();	
			
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de Editar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Endereço não pode ser Editado, dados está em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Editar Endereço." + ex.getMessage());
			}
		}
	}
	
	//Executar comando Editar da Unidade e do Endereço ao mesmo Tempo.(Método foi criado para não existir um endereço sem Unidade)
	public void executaEditarEndUnid(){
		//Chamando método editar do endereço
		editar();
		//Chamando método editar da unidade
		unidadeBean.editar();
		
	}
	
	//Executar comando Excuir da Unidade e do Endereço ao mesmo Tempo.
	public void executaExcluirEndUnid(){
		//Executando Exclusão da Unidade primeiro(Através de Injeção de Dependência)
		unidadeBean.excluir();
		//Verificando se variável de controle teve valor alterado
		if (unidadeBean.getVerifExclusao() == 1) {
			excluir();//Se valor da variál foi alterada para 1 Exclusão do Endereço é realizada.
			
		} 				
	}
	
	//Executar comando Salvar da Unidade e do Endereço ao mesmo Tempo.(Método foi criado para não existir um endereço sem Unidade)
	public void executaSalvarEndUnid(){
		//Chamando método salvar do endereço
		salvar();
		//Executando método salvar da unidade
		unidadeBean.salvar();
	}
	
	//Método para salvar o endereço dos Campi das unidades
	public void executaSalvarEndCamp() {
		// Método obrigatório para garantir teste antes da execução dos comandos
		try {
			salvar();
			//Pegando Unidade que está logada no sistema para salvar campi da mesma
			unidadeBean.getUnidadeCadastro().setUnidade(autenticacaoBean.getUsuarioAutenticado().getUnidade());
			// Executando executando método salvar criado na Classe UnidadeDAO
			unidadeBean.salvar();

		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de Salvar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Endereço do Campi não pode ser Salvo, dados já cadastrados!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Salvar Endereço do Campi." + ex.getMessage());
			}
		}
	}
	
	//Método que vai listar as cidades de acordo com o estado selecionado
	public void listaCidadesPorEstado(){	
		try {
			//Pegando ocódigo do estado selecionado passado a pagina
			Long estado = enderecoCadastro.getCidade().getEstado().getCodEstado();
			//Criando uma nova CidadeDao para e buscar uma lista de cidades
			CidadeDAO cidadeDAO = new CidadeDAO();
			//Carregando uma lista de cidade buscada pelo código do estado para a listaCidades
			listaCidades = cidadeDAO.buscarPorCodigoEstado(estado);
			
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();			
			FacesUtil.msgErro("Erroao listar Cidade por Estados!" + ex.getMessage());
		}
	}
}
