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
// Definindo o tempo de vida útil dentro da sessão
public class RecursoBean {
	// Criando um objeto e varáveis para receber dados
	private Recurso recursoCadastro;
	private List<Recurso> listaRecursos;
	private ArrayList<Recurso> listaRecursosFiltrados;
	private String acao;
	private Long codigo;
	
	private List<SubCategoria> listaSubCategorias;
	private List<Status> listaStatus;
	private List<Recurso> listaRecursosLiberados;
	
	//Executando Injeção de Dependência para ter acesso aos dados da outra Bean
	@ManagedProperty(value= "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	//Criando métodos de acessos aos objetos criado, Gets e Sets
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
	
	// Criando Método para Criar um novo objeto da classe
	public void novo(){
		recursoCadastro = new Recurso();
	}
	
	// Criando Método para excutar o método Salvar contido na DAO
	public void salvar(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			RecursoDAO recursoDAO = new RecursoDAO();
			
			recursoCadastro.setUnidade(autenticacaoBean.getUsuarioAutenticado().getUnidade()); 
			//Executando executando método salvar criado na Classe DAO
			recursoDAO.salvar(recursoCadastro);
			
			//Executando método de exibição de mensagem na Classe FacesUtil e criando mensagem
			FacesUtil.msgSucesso("Recurso Salvo com Sucesso!");
			recursoCadastro = new Recurso();
			
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de Salvar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Recurso não pode ser Salvo, dados está em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Salvar Recurso!" + ex.getMessage());
			}		
		}	
	}
	
	// Criando Método para excutar o método Buscar por Código contido na DAO
	public void carregarCadastro(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			//Pegando valor passado da página de cadastro
			String valor = FacesUtil.getParam("codRecurso");
			
			//Executando verificação se variável contém valores
			if (valor != null) {
				//Criando um variável temporária codigo e convertendo o a varável valor
				String codigo = valor;
				
				RecursoDAO recursoDAO = new RecursoDAO();
				//Executando método Buscar por Código criado na classe DAO e inserindo em variável
				recursoCadastro = recursoDAO.buscarPorCodigo(codigo);
				
				System.out.println("Recurso passado Edição: " + recursoCadastro.getSubCategoria().getNomeSubCategoria());
				
			} else {
				recursoCadastro = new Recurso();
			}
			//Executando método listar dentro da classe DAO e carregando um lista de Status e Subcategorias.
			StatusDAO statusDAO = new StatusDAO();
			listaStatus = statusDAO.buscarPorInterv(6L, 9L);
			
			SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
			listaSubCategorias = subCategoriaDAO.listar();
			
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
			} catch (RuntimeException ex) {
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Cadastro de Recursos!" + ex.getMessage());
		}
	}  	
	
	// Criando Método para excutar o método Excluir contido na DAO
	public void Excluir(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			RecursoDAO recursoDAO = new RecursoDAO();
			recursoDAO.excluir(recursoCadastro);
			
			FacesUtil.msgSucesso("Recurso Excluído com Sucesso!");
			recursoCadastro = new Recurso();
			
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de exclusão do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Recurso não pode ser Excluído! Recurso "+
				recursoCadastro.getIdentificacaoRecurso() +" está em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Excluir Recurso!" + ex.getMessage());
			}		
		}
	}
	
	// Criando Método para excutar o método Editar contido na DAO
	public void Editar(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			RecursoDAO recursoDAO = new RecursoDAO();
			recursoDAO.editar(recursoCadastro);
			
			FacesUtil.msgSucesso("Recurso Editado com Sucesso!");
			recursoCadastro = new Recurso();
			
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de edição do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Recurso não pode ser Editado, dados está em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Editar Recurso!" + ex.getMessage());
			}		
		}	
	}
	//Método que realiza pesquisa de recurso
	public void carregarPesquisa(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			//Criando um novo RecursoDAO para ter acesso aos metodos para manipular os recursos
			RecursoDAO recursoDAO = new RecursoDAO();
			
			//Pegando Cocido da Unidade passado da página de cadastro
			Long codigoUnidade = autenticacaoBean.getUsuarioAutenticado().getUnidade().getCodUnidade();
			
			//Executando verificação se variável contém valores
			if (codigoUnidade != null) {
				//Verificando verificação de qual unidade está logada no sistema.
				if( codigoUnidade == 1){
					//Carregando a lista caso verificação seja confirmada
					
					
					listaRecursos = recursoDAO.listar();
					//Buscando recuros liberados para listar na tela de reserva
					listaRecursosLiberados = recursoDAO.listarLiberados(6L);
					
				}else{
					//Executando método Buscar por Código criado na classe DAO e inserindo em variável
					listaRecursos = recursoDAO.recursosUnidade(codigoUnidade);
					//Buscando recuros liberados para listar na tela de reserva
					listaRecursosLiberados = recursoDAO.listarLiberadosUnidade(6L, codigoUnidade);
				}
				
			} else {
				recursoCadastro = new Recurso();
			}
			
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
			} catch (RuntimeException ex) {
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				ex.printStackTrace();
				FacesUtil.msgErro("Erro ao Carregar Pesquisa de Recursos!" + ex.getMessage());
		}
	}
}