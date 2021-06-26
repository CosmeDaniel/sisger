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
//Definindo o tempo de vida útil dentro da sessão
@ViewScoped

// Criando um objeto e varáveis para  receber dados
public class StatusBean {
	
	private Status statusCadastro;
	private List<Status> listaStatus;
	private ArrayList<Status> listaStatusFiltrados;
	private String acao;
	private Long codigo;
	
	//Criando métodos de acessos aos objetos criado, Gets e Sets
	public synchronized Status getStatusCadastro() {
		//Verificando se obejto é nulo ao ser acessado, se nulo um novo objeto é instânciado
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
	
	// Criando Método para excutar o método Salvar contido na DAO
	public void salvar(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			StatusDAO statusDAO = new StatusDAO();
			//Executando executando método salvar criado na Classe DAO
			statusDAO.salvar(statusCadastro);
			
			//Emitindo mensagem de sucesso caso não ocorra erro.
			FacesUtil.msgSucesso("Status Cadastrado com Sucesso!");
			statusCadastro = new Status();
			
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de salvar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Status não pode ser Salvao, dados estão em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Salvar Status!" + ex.getMessage());
			}
		}
	}
	
	// Criando Método para excutar o método Listar contido na DAO
	public void carregarPesquisa(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			StatusDAO statusDAO = new StatusDAO();
			//Executando método listar criado na Classe DAO
			listaStatus = statusDAO.listar();
			
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Pesquisa de Status!" + ex.getMessage());
		}		
	}
	
	// Criando Método para excutar o método Buscar por Código contido na DAO
	public void carregarCadastro(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			//Pegando valor passado da página de cadastro
			String valor = FacesUtil.getParam("codStatus");
			
			//Executando verificação se variável contém valores
			if (valor != null) {
				//Criando um variável temporária codigo e convertendo o a varável valor
				Long codigo = Long.parseLong(valor);
				
				StatusDAO statusDAO = new StatusDAO();
				//Executando método Buscar por Código criado na classe DAO e inserindo em variável
				statusCadastro = statusDAO.buscarPorCodigo(codigo);
			
			} else {
				statusCadastro = new Status();
			}
		//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Cadastro de Status!" + ex.getMessage());
		}
	}
	
	// Criando Método para excutar o método Exclulir contido na DAO
	public void excluir(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			StatusDAO statusDAO = new StatusDAO();
			statusDAO.excluir(statusCadastro);
			
			FacesUtil.msgSucesso("Status Excluído com Sucesso!");
			statusCadastro = new Status();
			
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de exclusão do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Status não pode ser Excluído! Status "+
				statusCadastro.getNomeStatus() +" está em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Excluir Status!" + ex.getMessage());
			}
		}	
	}
	
	// Criando Método para excutar o método Editar contido na DAO
	public void editar(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			StatusDAO statusDAO = new StatusDAO();
			statusDAO.editar(statusCadastro);
			
			FacesUtil.msgSucesso("Status Editado com Sucesso!");
			statusCadastro = new Status();
			
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de Editar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Status não pode ser Editado, dados está em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Editar Status!" + ex.getMessage());
			}
		}
	}
}