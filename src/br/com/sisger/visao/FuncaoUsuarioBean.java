package br.com.sisger.visao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.sisger.controle.FacesUtil;
import br.com.sisger.dao.FuncaoUsuarioDAO;
import br.com.sisger.modelo.FuncaoUsuario;
import br.com.sisger.modelo.Unidade;

//Definindo a classe como um Mange Bean
@ManagedBean
//Definindo o tempo de vida �til dentro da sess�o
@ViewScoped

// Criando um objeto e var�veis para receber dados
public class FuncaoUsuarioBean {

	private FuncaoUsuario funcaoUsuarioCadastro;
	private List<FuncaoUsuario> listaFuncaoUsuarios;
	private ArrayList<FuncaoUsuario> listaFuncaoUsuariosFiltrados;
	private String acao;
	private Long codigo;

	//Executando Inje��o de depend�ncia para acesso ao dados da Unidade Autenticada no sistema
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;
	
	//Criando m�todos de acessos aos objetos criado, Gets e Sets
	public synchronized FuncaoUsuario getFuncaoUsuarioCadastro() {
		//Verificando se obejto � nulo ao ser acessado, se nulo um novo objeto � inst�nciado
		if (funcaoUsuarioCadastro == null) {
			funcaoUsuarioCadastro = new FuncaoUsuario();
		}
		return funcaoUsuarioCadastro;
	}

	public synchronized void setFuncaoUsuarioCadastro(
			FuncaoUsuario funcaoUsuarioCadastro) {
		this.funcaoUsuarioCadastro = funcaoUsuarioCadastro;
	}

	public synchronized List<FuncaoUsuario> getListaFuncaoUsuarios() {
		return listaFuncaoUsuarios;
	}

	public synchronized void setListaFuncaoUsuarios(
			List<FuncaoUsuario> listaFuncaoUsuarios) {
		this.listaFuncaoUsuarios = listaFuncaoUsuarios;
	}

	public synchronized ArrayList<FuncaoUsuario> getListaFuncaoUsuariosFiltrados() {
		return listaFuncaoUsuariosFiltrados;
	}

	public synchronized void setListaFuncaoUsuariosFiltrados(
			ArrayList<FuncaoUsuario> listaFuncaoUsuariosFiltrados) {
		this.listaFuncaoUsuariosFiltrados = listaFuncaoUsuariosFiltrados;
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
	
	// Criando M�todo para Criar um novo objeto da classe
	public void novo(){
		funcaoUsuarioCadastro = new FuncaoUsuario();	
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
			//Criando uma nova FuncaoUsuarioDAO para acesso aos metodos contidos na DAO
			FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO();
			//Executando executando m�todo salvar criado na Classe DAO
			funcaoUsuarioDAO.salvar(funcaoUsuarioCadastro);
			
			//Executando m�todo de exibi��o de mensagem na Classe FacesUtil e criando mensagem
			FacesUtil.msgSucesso("Fun��o Salva com Sucesso!");
			funcaoUsuarioCadastro = new FuncaoUsuario();
			
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribu�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de Editar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Fun��o n�o pode ser Salava, dados est� em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Salvar Fun��o!" + ex.getMessage());
			}
		}
	}
	
	// Criando M�todo para excutar o m�todo Listar contido na DAO
	public void carregarPesquisa(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos		
		try {
			Long codigoUnidadeLogada = autenticacaoBean.getUsuarioAutenticado().getUnidade().getCodUnidade();
			Unidade campiUnidade = autenticacaoBean.getUsuarioAutenticado().getUnidade().getUnidade(); 
			//Criando uma nova FuncaoUsuarioDAO para acesso aos metodos contidos na DAO
			FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO();
			//Verificando N�vel de acesso da Unidade para carregar lista de fun��o personalizada
			if (codigoUnidadeLogada != 1L) {
				listaFuncaoUsuarios = funcaoUsuarioDAO.buscarPorCodMai(2L);
			
			//Verificando se Unidade logada � um Campi ou uma Faculdade(Se for diferente de nulo � um Campi)
			}else if(campiUnidade != null){
				listaFuncaoUsuarios = funcaoUsuarioDAO.buscarPorCodMai(3L);
				
				}else{
				//Executando executando m�todo listar criado na Classe DAO
				listaFuncaoUsuarios = funcaoUsuarioDAO.listar();	
				
			}
		
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
			} catch (RuntimeException ex) {
				//Capiturando o erro ocorrido para ser impresso com a mensagem
			FacesUtil.msgErro("Erro Carregar Lista de Fun��o!" + ex.getMessage());
		}
	}
	
	// Criando M�todo para excutar o m�todo Buscar por C�digo contido na DAO
	public void carregarCadastro(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos		
		try {
			//Pegando valor passado da p�gina de cadastro
			String valor = FacesUtil.getParam("codFuncaoUsuario"); 
			
			//Executando verifica��o se vari�vel cont�m valores
			if (valor != null) {
				//Criando um vari�vel tempor�ria codigo e convertendo o a var�vel valor
				Long codigo = Long.parseLong(valor);
				
				//Criando uma nova FuncaoUsuarioDAO para acesso aos metodos contidos na DAO
				FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO();
				//Executando m�todo Buscar por C�digo criado na classe DAO e inserindo em vari�vel
				funcaoUsuarioCadastro = funcaoUsuarioDAO.buscarPorCodigo(codigo);
				
			} else {
				funcaoUsuarioCadastro = new FuncaoUsuario();
			}
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
			} catch (RuntimeException ex) {
				//Capiturando o erro ocorrido para ser impresso com a mensagem
			FacesUtil.msgErro("Erro ao Carregar Cadastro de Fun��o!" + ex.getMessage());
		}
	}
	
	// Criando M�todo para excutar o m�todo Exclulir contido na DAO
	public void excluir(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos		
		try {
			//Criando uma nova FuncaoUsuarioDAO para acesso aos metodos contidos na DAO
			FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO();
			funcaoUsuarioDAO.excluir(funcaoUsuarioCadastro);
			
			FacesUtil.msgSucesso("Fun��o Exclu�da com Sucesso!");
			funcaoUsuarioCadastro = new FuncaoUsuario();
			
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
			} catch (RuntimeException ex) {
				//Atribui�ndo erro capturado a uma vari�vel
				String erroUso = ex.getMessage();
				//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
				ex.printStackTrace();
				//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
				if (erroUso.equals("could not execute statement")) {
					//Mensagem de tratamento de n�o permiss�o de exclus�o do valor por estar sendo usado em algum cadastro
					FacesUtil.msgErro("Fun��o n�o pode ser Exclu�da! Fun�o "+
					funcaoUsuarioCadastro.getDescricaoFuncaoUsuario() +" est� em uso.");
				}else{
					//Capiturando o erro ocorrido para ser impresso com a mensagem
					FacesUtil.msgErro("Erro ao Excluir Fun��o!" + ex.getMessage());
				}
			}
	}

	// Criando M�todo para excutar o m�todo Editar contido na DAO
	public void editar(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos		
		try {
			//Criando uma nova FuncaoUsuarioDAO para acesso aos metodos contidos na DAO
			FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO();
			funcaoUsuarioDAO.editar(funcaoUsuarioCadastro);
			
			FacesUtil.msgSucesso("Fun��o Editada com Sucesso!");
			funcaoUsuarioCadastro = new FuncaoUsuario();
			
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de Editar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Fun��o n�o pode ser Editada, dados est� em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Editada Fun��o!" + ex.getMessage());
			}
		}
	}
}
