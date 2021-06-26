package br.com.sisger.visao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sisger.controle.FacesUtil;
import br.com.sisger.dao.EstadoDAO;
import br.com.sisger.modelo.Estado;

@ManagedBean // Definindo a classe como um Mange Bean
@ViewScoped // Definindo o tempo de vida útil dentro da sessão
public class EstadoBean {
	// Criando um objeto e atributos para  receber dados
	private Estado estadoCadastro;
	private List<Estado> listaEstados;
	private List<Estado> listaEstadosFiltrados;
	private String acao;
	private Long codigo;
	
	private Estado selecaoEstado;
	
	//Criando métodos de acessos aos objetos criado, Gets e Sets
	public synchronized Estado getEstadoCadastro() {
		//Verificando se obejto é nulo ao ser acessado, se nulo um novo objeto é instânciado
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
			//Recebendo Código da Unidade para consulta e exibição
			selecaoEstado.getCodEstado();
		}
	
	}
	
	// Criando Método para excutar o método Salvar contido na DAO
	public void salvar(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			//Executando executando método salvar criado na Classe DAO
			estadoDAO.salvar(estadoCadastro);
			
			//Executando método de exibição de mensagem na Classe FacesUtil e criando mensagem
			FacesUtil.msgSucesso("Estado Salvo com Sucesso!");
			
			estadoCadastro = new Estado();
			
			//Caso haja algum erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de Salvar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Estado não pode ser Salvo, dados está em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Salvar Estado." + ex.getMessage());
			}
		}
	}
	
	// Criando Método para excutar o método Listar contido na DAO
	public void carregarPesquisa(){
		//Método obrigatório para garantir teste antes da execução dos comandos		
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			//Executando executando método listar criado na Classe DAO
			listaEstados = estadoDAO.listar();	
			
			//Caso haja algum erro ao executar Try O catch é executado emitindo uma menssagem de erro	
			} catch (RuntimeException ex) {
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Pesquisa" + ex.getMessage());
		}
	}
	
	// Criando Método para excutar o método Buscar por Código contido na DAO
	public void carregarCadastro(){
		//Método obrigatório para garantir teste antes da execução dos comandos		
		try {			
			//Pegando valor passado da página de cadastro
			String valor = FacesUtil.getParam("codEstado");
						
			//Executando verificação se variável contém valores
			if (valor != null) {
				//Criando um variável temporária codigo e convertendo o a varável valor
				Long codigo = Long.parseLong(valor);
				
				EstadoDAO estadoDAO = new EstadoDAO();
				//Executando método Buscar por Código criado na classe DAO e inserindo em variável
				estadoCadastro = estadoDAO.buscarPorCodigo(codigo);
				
			} else {
				estadoCadastro = new Estado();
			}
			
			//Caso haja algum erro ao executar Try O catch é executado emitindo uma menssagem de erro	
			} catch (RuntimeException ex) {
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Cadastro." + ex.getMessage());
		}
	}
	
	// Criando Método para excutar o método Exclulir contido na DAO
	public void excluir(){
		//Método obrigatório para garantir teste antes da execução dos comandos		
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.excluir(estadoCadastro);
			
			FacesUtil.msgSucesso("Estado Excluído com Sucesso!");
			
			estadoCadastro = new Estado();
			
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de exclusão do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Estado não pode ser Excluído! Estado "+
				estadoCadastro.getUfEstado() +" está em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Excluír Estado." + ex.getMessage());
			}
		}
	}
	
	// Criando Método para excutar o método Editar contido na DAO
	public void editar(){
		//Método obrigatório para garantir teste antes da execução dos comandos		
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.editar(estadoCadastro);
			
			FacesUtil.msgSucesso("Estado Editado com Sucesso!");
			
			estadoCadastro = new Estado();
			
			//Caso haja algum erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de Editar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Estado não pode ser Editado, está em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Excluír Estado." + ex.getMessage());
			}
		}
	}
}
