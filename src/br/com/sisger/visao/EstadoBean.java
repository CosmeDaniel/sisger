package br.com.sisger.visao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sisger.controle.FacesUtil;
import br.com.sisger.dao.EstadoDAO;
import br.com.sisger.modelo.Estado;

@ManagedBean // Definindo a classe como um Mange Bean
@ViewScoped // Definindo o tempo de vida �til dentro da sess�o
public class EstadoBean {
	// Criando um objeto e atributos para  receber dados
	private Estado estadoCadastro;
	private List<Estado> listaEstados;
	private List<Estado> listaEstadosFiltrados;
	private String acao;
	private Long codigo;
	
	private Estado selecaoEstado;
	
	//Criando m�todos de acessos aos objetos criado, Gets e Sets
	public synchronized Estado getEstadoCadastro() {
		//Verificando se obejto � nulo ao ser acessado, se nulo um novo objeto � inst�nciado
		if (estadoCadastro == null) {
			estadoCadastro = new Estado();
		}
		return estadoCadastro;
	}
	public synchronized void setEstadoCadastro(Estado estadoCadastro) {
		this.estadoCadastro = estadoCadastro;
	}
	public synchronized List<Estado> getListaEstados() {
		return listaEstados;
	}
	public synchronized void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}
	public synchronized List<Estado> getListaEstadosFiltrados() {
		return listaEstadosFiltrados;
	}
	public synchronized void setListaEstadosFiltrados(
			List<Estado> listaEstadosFiltrados) {
		this.listaEstadosFiltrados = listaEstadosFiltrados;
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
	
	public Estado getSelecaoEstado() {
		return selecaoEstado;
	}
	
	public void setSelecaoEstado(Estado selecaoEstado) {
		this.selecaoEstado = selecaoEstado;
		if (selecaoEstado != null) {
			//Recebendo C�digo da Unidade para consulta e exibi��o
			selecaoEstado.getCodEstado();
		}
	
	}
	
	// Criando M�todo para excutar o m�todo Salvar contido na DAO
	public void salvar(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			//Executando executando m�todo salvar criado na Classe DAO
			estadoDAO.salvar(estadoCadastro);
			
			//Executando m�todo de exibi��o de mensagem na Classe FacesUtil e criando mensagem
			FacesUtil.msgSucesso("Estado Salvo com Sucesso!");
			
			estadoCadastro = new Estado();
			
			//Caso haja algum erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de Salvar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Estado n�o pode ser Salvo, dados est� em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Salvar Estado." + ex.getMessage());
			}
		}
	}
	
	// Criando M�todo para excutar o m�todo Listar contido na DAO
	public void carregarPesquisa(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos		
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			//Executando executando m�todo listar criado na Classe DAO
			listaEstados = estadoDAO.listar();	
			
			//Caso haja algum erro ao executar Try O catch � executado emitindo uma menssagem de erro	
			} catch (RuntimeException ex) {
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Pesquisa" + ex.getMessage());
		}
	}
	
	// Criando M�todo para excutar o m�todo Buscar por C�digo contido na DAO
	public void carregarCadastro(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos		
		try {			
			//Pegando valor passado da p�gina de cadastro
			String valor = FacesUtil.getParam("codEstado");
						
			//Executando verifica��o se vari�vel cont�m valores
			if (valor != null) {
				//Criando um vari�vel tempor�ria codigo e convertendo o a var�vel valor
				Long codigo = Long.parseLong(valor);
				
				EstadoDAO estadoDAO = new EstadoDAO();
				//Executando m�todo Buscar por C�digo criado na classe DAO e inserindo em vari�vel
				estadoCadastro = estadoDAO.buscarPorCodigo(codigo);
				
			} else {
				estadoCadastro = new Estado();
			}
			
			//Caso haja algum erro ao executar Try O catch � executado emitindo uma menssagem de erro	
			} catch (RuntimeException ex) {
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Cadastro." + ex.getMessage());
		}
	}
	
	// Criando M�todo para excutar o m�todo Exclulir contido na DAO
	public void excluir(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos		
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.excluir(estadoCadastro);
			
			FacesUtil.msgSucesso("Estado Exclu�do com Sucesso!");
			
			estadoCadastro = new Estado();
			
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de exclus�o do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Estado n�o pode ser Exclu�do! Estado "+
				estadoCadastro.getUfEstado() +" est� em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Exclu�r Estado." + ex.getMessage());
			}
		}
	}
	
	// Criando M�todo para excutar o m�todo Editar contido na DAO
	public void editar(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos		
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.editar(estadoCadastro);
			
			FacesUtil.msgSucesso("Estado Editado com Sucesso!");
			
			estadoCadastro = new Estado();
			
			//Caso haja algum erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de Editar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Estado n�o pode ser Editado, est� em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Exclu�r Estado." + ex.getMessage());
			}
		}
	}
}
