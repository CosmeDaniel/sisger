package br.com.sisger.visao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.sisger.controle.FacesUtil;
import br.com.sisger.dao.RecursoDAO;
import br.com.sisger.dao.StatusDAO;
import br.com.sisger.dao.SubCategoriaDAO;
import br.com.sisger.modelo.Recurso;
import br.com.sisger.modelo.Status;
import br.com.sisger.modelo.SubCategoria;

@ManagedBean
// Definindo a classe como um Mange Bean
@ViewScoped
// Definindo o tempo de vida �til dentro da sess�o
public class RecursoBean {
	// Criando um objeto e var�veis para receber dados
	private Recurso recursoCadastro;
	private List<Recurso> listaRecursos;
	private ArrayList<Recurso> listaRecursosFiltrados;
	private String acao;
	private Long codigo;
	
	private List<SubCategoria> listaSubCategorias;
	private List<Status> listaStatus;
	private List<Recurso> listaRecursosLiberados;
	
	//Executando Inje��o de Depend�ncia para ter acesso aos dados da outra Bean
	@ManagedProperty(value= "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	//Criando m�todos de acessos aos objetos criado, Gets e Sets
	public synchronized Recurso getRecursoCadastro() {
		if (recursoCadastro == null) {
			recursoCadastro = new Recurso();
		}
		return recursoCadastro;
	}

	public synchronized void setRecursoCadastro(Recurso recursoCadastro) {
		this.recursoCadastro = recursoCadastro;
	}

	public synchronized List<Recurso> getListaRecursos() {
		return listaRecursos;
	}

	public synchronized void setListaRecursos(List<Recurso> listaRecursos) {
		this.listaRecursos = listaRecursos;
	}

	public synchronized ArrayList<Recurso> getListaRecursosFiltrados() {
		return listaRecursosFiltrados;
	}

	public synchronized void setListaRecursosFiltrados(
			ArrayList<Recurso> listaRecursosFiltrados) {
		this.listaRecursosFiltrados = listaRecursosFiltrados;
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

	public synchronized List<SubCategoria> getListaSubCategorias() {
		return listaSubCategorias;
	}

	public synchronized void setListaSubCategorias(
			List<SubCategoria> listaSubCategorias) {
		this.listaSubCategorias = listaSubCategorias;
	}

	public synchronized List<Status> getListaStatus() {
		return listaStatus;
	}

	public synchronized void setListaStatus(List<Status> listaStatus) {
		this.listaStatus = listaStatus;
	}
	
	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}
	
	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}
	
	public List<Recurso> getListaRecursosLiberados() {
		return listaRecursosLiberados;
	}
	
	public void setListaRecursosLiberados(List<Recurso> listaRecursosLiberados) {
		this.listaRecursosLiberados = listaRecursosLiberados;
	}
	
	// Criando M�todo para Criar um novo objeto da classe
	public void novo(){
		recursoCadastro = new Recurso();
	}
	
	// Criando M�todo para excutar o m�todo Salvar contido na DAO
	public void salvar(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			RecursoDAO recursoDAO = new RecursoDAO();
			
			recursoCadastro.setUnidade(autenticacaoBean.getUsuarioAutenticado().getUnidade()); 
			//Executando executando m�todo salvar criado na Classe DAO
			recursoDAO.salvar(recursoCadastro);
			
			//Executando m�todo de exibi��o de mensagem na Classe FacesUtil e criando mensagem
			FacesUtil.msgSucesso("Recurso Salvo com Sucesso!");
			recursoCadastro = new Recurso();
			
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de Salvar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Recurso n�o pode ser Salvo, dados est� em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Salvar Recurso!" + ex.getMessage());
			}		
		}	
	}
	
	// Criando M�todo para excutar o m�todo Buscar por C�digo contido na DAO
	public void carregarCadastro(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			//Pegando valor passado da p�gina de cadastro
			String valor = FacesUtil.getParam("codRecurso");
			
			//Executando verifica��o se vari�vel cont�m valores
			if (valor != null) {
				//Criando um vari�vel tempor�ria codigo e convertendo o a var�vel valor
				String codigo = valor;
				
				RecursoDAO recursoDAO = new RecursoDAO();
				//Executando m�todo Buscar por C�digo criado na classe DAO e inserindo em vari�vel
				recursoCadastro = recursoDAO.buscarPorCodigo(codigo);
				
				System.out.println("Recurso passado Edi��o: " + recursoCadastro.getSubCategoria().getNomeSubCategoria());
				
			} else {
				recursoCadastro = new Recurso();
			}
			//Executando m�todo listar dentro da classe DAO e carregando um lista de Status e Subcategorias.
			StatusDAO statusDAO = new StatusDAO();
			listaStatus = statusDAO.buscarPorInterv(6L, 9L);
			
			SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
			listaSubCategorias = subCategoriaDAO.listar();
			
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
			} catch (RuntimeException ex) {
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Cadastro de Recursos!" + ex.getMessage());
		}
	}  	
	
	// Criando M�todo para excutar o m�todo Excluir contido na DAO
	public void Excluir(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			RecursoDAO recursoDAO = new RecursoDAO();
			recursoDAO.excluir(recursoCadastro);
			
			FacesUtil.msgSucesso("Recurso Exclu�do com Sucesso!");
			recursoCadastro = new Recurso();
			
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de exclus�o do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Recurso n�o pode ser Exclu�do! Recurso "+
				recursoCadastro.getIdentificacaoRecurso() +" est� em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Excluir Recurso!" + ex.getMessage());
			}		
		}
	}
	
	// Criando M�todo para excutar o m�todo Editar contido na DAO
	public void Editar(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			RecursoDAO recursoDAO = new RecursoDAO();
			recursoDAO.editar(recursoCadastro);
			
			FacesUtil.msgSucesso("Recurso Editado com Sucesso!");
			recursoCadastro = new Recurso();
			
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de edi��o do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Recurso n�o pode ser Editado, dados est� em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Editar Recurso!" + ex.getMessage());
			}		
		}	
	}
	//M�todo que realiza pesquisa de recurso
	public void carregarPesquisa(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			//Criando um novo RecursoDAO para ter acesso aos metodos para manipular os recursos
			RecursoDAO recursoDAO = new RecursoDAO();
			
			//Pegando Cocido da Unidade passado da p�gina de cadastro
			Long codigoUnidade = autenticacaoBean.getUsuarioAutenticado().getUnidade().getCodUnidade();
			
			//Executando verifica��o se vari�vel cont�m valores
			if (codigoUnidade != null) {
				//Verificando verifica��o de qual unidade est� logada no sistema.
				if( codigoUnidade == 1){
					//Carregando a lista caso verifica��o seja confirmada
					
					
					listaRecursos = recursoDAO.listar();
					//Buscando recuros liberados para listar na tela de reserva
					listaRecursosLiberados = recursoDAO.listarLiberados(6L);
					
				}else{
					//Executando m�todo Buscar por C�digo criado na classe DAO e inserindo em vari�vel
					listaRecursos = recursoDAO.recursosUnidade(codigoUnidade);
					//Buscando recuros liberados para listar na tela de reserva
					listaRecursosLiberados = recursoDAO.listarLiberadosUnidade(6L, codigoUnidade);
				}
				
			} else {
				recursoCadastro = new Recurso();
			}
			
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
			} catch (RuntimeException ex) {
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				ex.printStackTrace();
				FacesUtil.msgErro("Erro ao Carregar Pesquisa de Recursos!" + ex.getMessage());
		}
	}
}