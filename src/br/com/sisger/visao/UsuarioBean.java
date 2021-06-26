package br.com.sisger.visao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.sisger.controle.FacesUtil;
import br.com.sisger.dao.FuncaoUsuarioDAO;
import br.com.sisger.dao.StatusDAO;
import br.com.sisger.dao.UnidadeDAO;
import br.com.sisger.dao.UsuarioDAO;
import br.com.sisger.modelo.FuncaoUsuario;
import br.com.sisger.modelo.Status;
import br.com.sisger.modelo.Unidade;
import br.com.sisger.modelo.Usuario;

@ManagedBean // Definindo a classe como um Mange Bean
@ViewScoped // Definindo o tempo de vida �til dentro da sess�o
public class UsuarioBean {
	// Criando um objeto e var�veis para  receber dados tipo Unidade
	private Usuario usuarioCadastro;
	private List<Usuario> listaUsuarios;
	private ArrayList<Usuario> listaUsuariosFiltrados;
	private String acao;
	private Long codigo;
	private Long gestor = null;
	private Long administrador = null;
	private Long supervisor = null;
	private String unidadeGestora = null;
	private Long visivel = null;
	private String senha = null;
	
	private List<Status> listaStatus;
	private List<Unidade> listaUnidade;
	private List<FuncaoUsuario> listaFuncaoUsuario;
	
	private Usuario selecaoUsuario;
		
	@ManagedProperty(value = "#{autenticacaoBean}")
	AutenticacaoBean autenticacaoBean;
	
	//Criando m�todos de acessos aos objetos criado, Gets e Sets
	public Usuario getSelecaoUsuario() {
		return selecaoUsuario;
	}
	
	public void setSelecaoUsuario(Usuario selecaoUsuario) {
		this.selecaoUsuario = selecaoUsuario;
		if (selecaoUsuario != null) {	
			selecaoUsuario.getCodUsuario();
		}
	}
	
	public synchronized Usuario getUsuarioCadastro() {
		if (usuarioCadastro == null) {
			usuarioCadastro = new Usuario();
		}
		return usuarioCadastro;
	}
	public synchronized void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	public synchronized List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	public synchronized void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	public synchronized ArrayList<Usuario> getListaUsuariosFiltrados() {
		return listaUsuariosFiltrados;
	}
	public synchronized void setListaUsuariosFiltrados(
			ArrayList<Usuario> listaUsuariosFiltrados) {
		this.listaUsuariosFiltrados = listaUsuariosFiltrados;
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
	public String getSenha() {
		return senha;
	}
	public synchronized List<Status> getListaStatus() {
		return listaStatus;
	}
	public synchronized void setListaStatus(List<Status> listaStatus) {
		this.listaStatus = listaStatus;
	}
	public synchronized List<Unidade> getListaUnidade() {
		return listaUnidade;
	}
	public synchronized void setListaUnidade(List<Unidade> listaUnidade) {
		this.listaUnidade = listaUnidade;
	}
	public synchronized List<FuncaoUsuario> getListaFuncaoUsuario() {
		return listaFuncaoUsuario;
	}
	public synchronized void setListaFuncaoUsuario(
			List<FuncaoUsuario> listaFuncaoUsuario) {
		this.listaFuncaoUsuario = listaFuncaoUsuario;
	}
	
	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}
	
	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}
	
	public synchronized Long getGestor() {
		return gestor;
	}
	public synchronized void setGestor(Long gestor) {
		this.gestor = gestor;
	}
	public synchronized Long getAdministrador() {
		return administrador;
	}
	public synchronized void setAdministrador(Long administrador) {
		this.administrador = administrador;
	}
	public synchronized Long getSupervisor() {
		return supervisor;
	}
	public synchronized void setSupervisor(Long supervisor) {
		this.supervisor = supervisor;
	}	
	
	// Criando M�todo Criar um novo objeto da classe
	public void novo(){
		usuarioCadastro = new Usuario();
		
	}
	
	public String getUnidadeGestora() {
		return unidadeGestora;
	}
	
	public void setUnidadeGestora(String unidadeGestora) {
		this.unidadeGestora = unidadeGestora;
	}
	
	public Long getVisivel() {
		return visivel;
	}
	
	public void setVisivel(Long visivel) {
		this.visivel = visivel;
	}
	
	// Criando M�todo Salvar da classe
	public void salvar(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
	
			//Pegando dados do usu�rio logado no sistema para verificar se ele � da Faculdade ou do C�mpus
			Unidade unidade = autenticacaoBean.getUsuarioAutenticado().getUnidade().getUnidade();
			//Verificando se Unidade logada � uma Faculdade ou C�mpus(Se valor for nulo Unidade Logada � uma Faculdade)
			if (unidade != null) {
				//Adicionando Faculdade do usu�rio autom�tico no Objeto Usu�rioCadastro no Campo Unidade do usu�rio
				usuarioCadastro.setUnidade(autenticacaoBean.getUsuarioAutenticado().getUnidade());
		
				//Criptografando senha para o formato MD5 para Edi��o da senha de forma segura
				usuarioCadastro.setSenhaUsuario((DigestUtils.md5Hex(usuarioCadastro.getSenhaUsuario())));
				//Executando executando m�todo salvar criado na Classe UnidadeDAO
				usuarioDAO.salvar(usuarioCadastro);
				
				//Executando m�todo de exibi��o de mensagem na Classe FacesUtil e criando mensagem
				FacesUtil.msgSucesso("Usu�rio Salvo com Sucesso!");
				usuarioCadastro = new Usuario();
			//Caso n�o seja nulo ent�o Faculdade � um C�mpus(Valores dos atributos ser�o passados da p�gina)
			}else{
				//Criptografando senha para o formato MD5 para Edi��o da senha de forma segura
				usuarioCadastro.setSenhaUsuario(DigestUtils.md5Hex(usuarioCadastro.getSenhaUsuario()));
				//Executando executando m�todo salvar criado na Classe UnidadeDAO
				usuarioDAO.salvar(usuarioCadastro);
				
				//Executando m�todo de exibi��o de mensagem na Classe FacesUtil e criando mensagem
				FacesUtil.msgSucesso("Usu�rio Salvo com Sucesso!");
				usuarioCadastro = new Usuario();			
			}
			
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de Salvar do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Usu�rio n�o pode ser Salvo, dados est�o em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Salvar Usu�rio!" + ex.getMessage());
			}
		}
	}

	public void carregarPesquisa(){
		//Executando o M�todo para verificar a fun��o do usu�rio
		verificaFuncaoUsuario();
		try {					
			//Pegando o C�digo da Unidade do usu�rio autenticado no sistema para buscar os usuarios da unidade
			Long codUnidade = autenticacaoBean.getUsuarioAutenticado().getUnidade().getCodUnidade();
			 
			//Estanciando o DAO de usu�rio para manipular os dados do usuario pelos metodos contido na DAO
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
			//Verificando qual o n�vel do usu�rio logado para carregar lista de usu�rio pesonalizada
			//Se usu�rio for gestor lista geral e carregada
			if (gestor != null) {
				listaUsuarios = usuarioDAO.listar();
				
				UnidadeDAO unidadeDAO = new UnidadeDAO();
				listaUnidade = unidadeDAO.listar();
				
				//Se usu�rio n�o for gestor � carregada lista de usu�rio da Unidade
			}else{
				//Executando m�todo Buscar por C�digo criado na classe DAO e inserindo em vari�vel
				listaUsuarios = usuarioDAO.usuariosUnidade(codUnidade);
					
			}
			usuarioCadastro = new Usuario();
			
		} catch (RuntimeException ex) {
			FacesUtil.msgErro("Erro ao Carregar Pesquisa de Usu�rio!" + ex.getMessage());
		}
	}
	
	// Criando M�todo Carregar os Cadastros da classe
	public void carregarCadastro(){
		try {
			//Pegando valor passado da p�gina de cadastro
			String valor = FacesUtil.getParam("codUsuario");
			
			//Executando verifica��o se vari�vel cont�m valores
			if (valor != null) {
				//Criando um vari�vel tempor�ria codigo e convertendo o a var�vel valor
				Long codigo = Long.parseLong(valor);
				
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				//Executando m�todo Buscar por C�digo criado na classe UnidadeDAO e inserindo em vari�vel
				usuarioCadastro = usuarioDAO.buscarPorCodigo(codigo);
				//Armazenando senha do usu�rio para edi��o de usu�rio sem alterar a senha.
				senha = usuarioCadastro.getSenhaUsuario();
				
			} else {
				usuarioCadastro = new Usuario();
			}
			StatusDAO statusDAO = new StatusDAO();
			//Executando m�todo listar dentro da classe EnderecoDAO e carregando um lista de endere�os
			listaStatus = statusDAO.buscarPorInterv(1L, 2L);
			
			//Pegando codigo da unidade e c�mpus da unidade logada no sistema
			Long codUnidadeLogada = autenticacaoBean.getUsuarioAutenticado().getUnidade().getCodUnidade();
			Unidade campiUnidade = autenticacaoBean.getUsuarioAutenticado().getUnidade().getUnidade();
			
			//Criando uma nova UnidadeDAO para acesso os m�todos contidos na DAO
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			//Verificando Unidade logada para carregar lista de unidades personalizadas					
			if (codUnidadeLogada != 1 && campiUnidade == null) {
				listaUnidade = unidadeDAO.buscarCampiUnidade(codUnidadeLogada);
			
			}else if(codUnidadeLogada == 1L){
				listaUnidade = unidadeDAO.listar();
				
			}
			
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Cadastro de Usu�rios!" + ex.getMessage());
		}	
	}

	public void Excluir(){
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuarioCadastro);
			
			FacesUtil.msgSucesso("Usu�rio Exclu�do com Sucesso!");
			usuarioCadastro = new Usuario();
			
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de exclus�o do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Usu�rio n�o pode ser Exclu�do! Usu�rio "+
				usuarioCadastro.getNomeUsuario() +" est� em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Excluir Usu�rio!" + ex.getMessage());
			}
		}
	}
	
	//M�todo de edi��o do usu�rio
	public void Editar(){
		try {
			//Criando um novo UsuarioDAO para acesso aos metodos da DAO
			UsuarioDAO usuarioDAO = new UsuarioDAO();	
			
			//Verificando se senha do usu�rio est� vazia(Se sim usu�rio n�o quer editar senha, mesma senha � salva)
			if (usuarioCadastro.getSenhaUsuario() == "") {
				//Inserindo a mesma senha que usuario usava no objeto usuarioCadastro no atributo senha.
				usuarioCadastro.setSenhaUsuario(senha);
				//Executando m�todo de edi��o do usu�rio
				usuarioDAO.editar(usuarioCadastro);
				
				FacesUtil.msgSucesso("Usu�rio Editado com Sucesso!");
				usuarioCadastro = new Usuario();
				
			} else {
				//Criptografando senha para o formato MD5 para Edi��o da senha de forma segura
				usuarioCadastro.setSenhaUsuario(DigestUtils.md5Hex(usuarioCadastro.getSenhaUsuario()));
				//Executando m�todo de edi��o de usu�rio
				usuarioDAO.editar(usuarioCadastro);
				
				FacesUtil.msgSucesso("Usu�rio Editado com Sucesso!");
				usuarioCadastro = new Usuario();
			}			
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de edi��o do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Usu�rio n�o pode ser Editado, dados est�o em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Editar Usu�rio!" + ex.getMessage());
			}
		}
	}
	
	//Criando m�todo que verifica a fun�ao do usu�rio logado acessando AcessoPhaseListener para pegar valores
	public void verificaFuncaoUsuario(){
		try {
			//Pegando a funcao do usuario logado e o codigo da Unidade no classe autenticacaoBean
			Long funcaoUsuarioLogado = autenticacaoBean.getUsuarioAutenticado().getFuncaoUsuario().getcodFuncaoUsuario();
			Long unidadeLogada = autenticacaoBean.getUsuarioAutenticado().getUnidade().getCodUnidade();
			
			//Pegando verificando se unidade � gestora do sistema para libera��o de permiss�es
			if (unidadeLogada == 1) {
				unidadeGestora = autenticacaoBean.getUsuarioAutenticado().getUnidade().getCodUnidade().toString();
			
			}
			//Condi��o que verifica a fun��o do usu�rio logado
			//Se c�digo da fun��o do usu�ro for igual a 1 Usu�rio � um gestor do sistema(Todos as Fun��es s�o exibidas para o usu�rio)
			if (funcaoUsuarioLogado == 1) {
				gestor = 1L;
				visivel = funcaoUsuarioLogado;
			
				//Se c�digo da fun��o do usu�ro for igual a 2 Usu�rio � um Administrador do sistema(Fun��o de Gestor n�o � exibida para cadastro)	
			}else if(funcaoUsuarioLogado == 2) {
				administrador = 2L;
				visivel = funcaoUsuarioLogado;
			
				//Se c�digo da fun��o do usu�ro for igual a 3 Usu�rio � um supervisor do sistema(Fun��es Gestor e Administrados n�o s�o exibidas para o usu�rio)	
			}if (funcaoUsuarioLogado == 3) {
				supervisor = 3L;
				visivel = funcaoUsuarioLogado;
				
			}			
		} catch (RuntimeException ex) {
			
			FacesUtil.msgErro("Erro ao carregar Fun��o do Usu�rio Logado!");
		}		
	}
	
	//M�todo respons�vel pelo carregamento personalizado da lista da fun��o dos usu�rios de acordo com acesso da unidade
	public void carregarListaFuncaoUsuario(){
		try {
			Long codUnidadeLogada = autenticacaoBean.getUsuarioAutenticado().getUnidade().getCodUnidade();
			Unidade campiUnidade = autenticacaoBean.getUsuarioAutenticado().getUnidade().getUnidade(); 
			//Criando uma nova FuncaoUsuarioDAO para acesso aos metodos contidos na DAO
			FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO();
			//Verificando N�vel de acesso da Unidade para carregar lista de fun��o personalizada
			if (campiUnidade == null) {
				listaFuncaoUsuario = funcaoUsuarioDAO.buscarPorCodMai(2L);
				
				//Verificando se Unidade logada � um Campi ou uma Faculdade(Se for diferente de nulo � um Campi)
			}if(campiUnidade != null){
				listaFuncaoUsuario = funcaoUsuarioDAO.buscarPorCodMai(2L);
					
			}if(codUnidadeLogada == 1L){
					//Executando executando m�todo listar criado na Classe DAO
					listaFuncaoUsuario = funcaoUsuarioDAO.listar();						
				}
			
		//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro				
		} catch (RuntimeException ex) {
			
			FacesUtil.msgErro("Erro ao carregar Lista de Fun��o Usu�rio!");
		}
	}
	
	//M�todo para emitir mensagem de edi��o de usu�rio PROFESSOR e ALUNO
	public void emitirMsgEdicaoUsuario(){
		FacesUtil.msgAlerta("Edi��es ser�o aplicadas da proxima vez que Logar no Sistema!");
		
	}
}
