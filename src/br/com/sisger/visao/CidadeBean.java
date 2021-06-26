package br.com.sisger.visao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sisger.controle.FacesUtil;
import br.com.sisger.dao.CidadeDAO;
import br.com.sisger.dao.EstadoDAO;
import br.com.sisger.modelo.Cidade;
import br.com.sisger.modelo.Estado;

@ManagedBean // Definindo a classe como um Mange Bean
@ViewScoped // Definindo o tempo de vida �til dentro da sess�o
public class CidadeBean {
	// Criando um objeto e atributos para  receber dados
	private Cidade cidadeCadastro;
	private List<Cidade> listaCidades;
	private List<Cidade> listaCidadesFiltradas;
	private String acao;
	private Long codigo;
	
	private List<Estado> listaEstados;
	
	private Cidade selecaoCidade;
	
	//Criando m�todos de acessos aos objetos criado, Gets e Sets
	public synchronized Cidade getCidadeCadastro() {
		//Verificando se obejto � nulo ao ser acessado, se nulo um novo objeto � inst�nciado
		if (cidadeCadastro == null) {
			cidadeCadastro = new Cidade();
		}
		return cidadeCadastro;
	}
	
	public synchronized void setCidadeCadastro(Cidade cidadeCadastro) {
		this.cidadeCadastro = cidadeCadastro;
	}

	public synchronized List<Cidade> getListaCidades() {
		return listaCidades;
	}

	public synchronized void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public synchronized List<Cidade> getListaCidadesFiltradas() {
		return listaCidadesFiltradas;
	}

	public synchronized void setListaCidadesFiltradas(
			List<Cidade> listaCidadesFiltradas) {
		this.listaCidadesFiltradas = listaCidadesFiltradas;
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

	public synchronized List<Estado> getListaEstados() {
		return listaEstados;
	}

	public synchronized void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}
	
	public Cidade getSelecaoCidade() {
		return selecaoCidade;
	}
	
	public void setSelecaoCidade(Cidade selecaoCidade) {
		this.selecaoCidade = selecaoCidade;
		if (selecaoCidade != null) {
			selecaoCidade.getCodCidade();
		}
	}
	
	// Criando M�todo para excutar o m�todo Salvar contido na DAO
	public void salvar(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			//Criando uma nova CidadeDAO para acesso aos metodos contidos na DAO
			CidadeDAO cidadeDAO = new CidadeDAO();
			//Executando executando m�todo salvar criado na Classe DAO
			cidadeDAO.salvar(cidadeCadastro);
			
			//Executando m�todo de exibi��o de mensagem na Classe FacesUtil e criando mensagem
			FacesUtil.msgSucesso("Cidade Salva com Sucesso!");
			
			cidadeCadastro = new Cidade();
			
			//Caso haja algum erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de Salvar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Cidade n�o pode ser Salva dados j� cadastrados!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Editar Cidade." + ex.getMessage());
			}
		}
	}
	
	// Criando M�todo para excutar o m�todo Listar contido na DAO
	public void carregarPesquisa(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			//Criando uma nova CidadeDAO para acesso aos metodos contidos na DAO
			CidadeDAO cidadeDAO = new CidadeDAO();
			
			//Executando executando m�todo listar criado na Classe DAO
			listaCidades = cidadeDAO.listar();
			
			//Caso haja algum erro ao executar Try O catch � executado emitindo uma menssagem de erro	
			} catch (RuntimeException ex) {
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Lista Pesquisa de Cidades." + ex.getMessage());
		}
	}
	
	// Criando M�todo para excutar o m�todo Buscar por C�digo contido na DAO
	public void carregarCadastro(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			//Pegando valor passado da p�gina de cadastro
			String valor = FacesUtil.getParam("codCidade");
			
			//Executando verifica��o se vari�vel cont�m valores
			if (valor != null) {
				//Criando um vari�vel tempor�ria codigo e convertendo o a var�vel valor
				Long codigo = Long.parseLong(valor);
				//Criando uma nova CidadeDAO para acesso aos metodos contidos na DAO
				CidadeDAO cidadeDAO = new CidadeDAO();
				//Executando m�todo Buscar por C�digo criado na classe DAO e inserindo em vari�vel
				cidadeCadastro = cidadeDAO.buscarPorCodigo(codigo);
				
			}else{
				cidadeCadastro = new Cidade();
			}
			//Criando um novo EstadoDAO para acesso aos metodos contidos na DAO
			EstadoDAO estadoDAO = new EstadoDAO();
			//Executando m�todo listar dentro da classe DAO e carregando um lista de estados
			listaEstados = estadoDAO.listar();
			
			//Caso haja algum erro ao executar Try O catch � executado emitindo uma menssagem de erro	
			} catch (RuntimeException ex) {
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Cadastro das Cidades." + ex.getMessage());
		}
	}
	
	// Criando M�todo para excutar o m�todo Excluir contido na DAO
	public void excluir(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			//Criando uma nova CidadeDAO para acesso aos metodos contidos na DAO
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.excluir(cidadeCadastro);
			
			FacesUtil.msgSucesso("Cidade Excluida com Sucesso.");
			
			cidadeCadastro = new Cidade();
			
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de exclus�o do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Cidade n�o pode ser Exclu�da! Cidade "+
				cidadeCadastro.getNomeCidade() +" est� em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Excluir Cidade." + ex.getMessage());
			}
		}
	}
	
	// Criando M�todo para excutar o m�todo Editar contido na DAO
	public void editar(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			//Criando uma nova CidadeDAO para acesso aos metodos contidos na DAO
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.editar(cidadeCadastro);
			
			FacesUtil.msgSucesso("Cidade Editada com Sucesso!");
			
			cidadeCadastro = new Cidade();
			
			//Caso haja algum erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de Editar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Cidade n�o pode ser Editada! Cidade "+
				cidadeCadastro.getNomeCidade() +" est� em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Editar Cidade." + ex.getMessage());
			}
		}
	}
}
