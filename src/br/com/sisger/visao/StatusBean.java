package br.com.sisger.visao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sisger.controle.FacesUtil;
import br.com.sisger.dao.StatusDAO;
import br.com.sisger.modelo.Status;

//Definindo a classe como um Mange Bean
@ManagedBean
//Definindo o tempo de vida �til dentro da sess�o
@ViewScoped

// Criando um objeto e var�veis para  receber dados
public class StatusBean {
	
	private Status statusCadastro;
	private List<Status> listaStatus;
	private ArrayList<Status> listaStatusFiltrados;
	private String acao;
	private Long codigo;
	
	//Criando m�todos de acessos aos objetos criado, Gets e Sets
	public synchronized Status getStatusCadastro() {
		//Verificando se obejto � nulo ao ser acessado, se nulo um novo objeto � inst�nciado
		if (statusCadastro == null) {
			statusCadastro = new Status();
		}
		
		return statusCadastro;
	}
	public synchronized void setStatusCadastro(Status statusCadastro) {
		this.statusCadastro = statusCadastro;
	}
	public synchronized List<Status> getListaStatus() {
		return listaStatus;
	}
	public synchronized void setListaStatus(List<Status> listaStatus) {
		this.listaStatus = listaStatus;
	}
	public synchronized ArrayList<Status> getListaStatusFiltrados() {
		return listaStatusFiltrados;
	}
	public synchronized void setListaStatusFiltrados(
			ArrayList<Status> listaStatusFiltrados) {
		this.listaStatusFiltrados = listaStatusFiltrados;
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
	
	// Criando M�todo para excutar o m�todo Salvar contido na DAO
	public void salvar(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			StatusDAO statusDAO = new StatusDAO();
			//Executando executando m�todo salvar criado na Classe DAO
			statusDAO.salvar(statusCadastro);
			
			//Emitindo mensagem de sucesso caso n�o ocorra erro.
			FacesUtil.msgSucesso("Status Cadastrado com Sucesso!");
			statusCadastro = new Status();
			
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de salvar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Status n�o pode ser Salvao, dados est�o em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Salvar Status!" + ex.getMessage());
			}
		}
	}
	
	// Criando M�todo para excutar o m�todo Listar contido na DAO
	public void carregarPesquisa(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			StatusDAO statusDAO = new StatusDAO();
			//Executando m�todo listar criado na Classe DAO
			listaStatus = statusDAO.listar();
			
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Pesquisa de Status!" + ex.getMessage());
		}		
	}
	
	// Criando M�todo para excutar o m�todo Buscar por C�digo contido na DAO
	public void carregarCadastro(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			//Pegando valor passado da p�gina de cadastro
			String valor = FacesUtil.getParam("codStatus");
			
			//Executando verifica��o se vari�vel cont�m valores
			if (valor != null) {
				//Criando um vari�vel tempor�ria codigo e convertendo o a var�vel valor
				Long codigo = Long.parseLong(valor);
				
				StatusDAO statusDAO = new StatusDAO();
				//Executando m�todo Buscar por C�digo criado na classe DAO e inserindo em vari�vel
				statusCadastro = statusDAO.buscarPorCodigo(codigo);
			
			} else {
				statusCadastro = new Status();
			}
		//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Cadastro de Status!" + ex.getMessage());
		}
	}
	
	// Criando M�todo para excutar o m�todo Exclulir contido na DAO
	public void excluir(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			StatusDAO statusDAO = new StatusDAO();
			statusDAO.excluir(statusCadastro);
			
			FacesUtil.msgSucesso("Status Exclu�do com Sucesso!");
			statusCadastro = new Status();
			
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de exclus�o do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Status n�o pode ser Exclu�do! Status "+
				statusCadastro.getNomeStatus() +" est� em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Excluir Status!" + ex.getMessage());
			}
		}	
	}
	
	// Criando M�todo para excutar o m�todo Editar contido na DAO
	public void editar(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			StatusDAO statusDAO = new StatusDAO();
			statusDAO.editar(statusCadastro);
			
			FacesUtil.msgSucesso("Status Editado com Sucesso!");
			statusCadastro = new Status();
			
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de Editar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Status n�o pode ser Editado, dados est� em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Editar Status!" + ex.getMessage());
			}
		}
	}
}