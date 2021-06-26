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
@ViewScoped // Definindo o tempo de vida útil dentro da sessão
public class UsuarioBean {
	// Criando um objeto e varáveis para  receber dados tipo Unidade
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
	
	//Criando métodos de acessos aos objetos criado, Gets e Sets
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
	
	// Criando Método Criar um novo objeto da classe
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
	
	// Criando Método Salvar da classe
	public void salvar(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
	
			//Pegando dados do usuário logado no sistema para verificar se ele é da Faculdade ou do Câmpus
			Unidade unidade = autenticacaoBean.getUsuarioAutenticado().getUnidade().getUnidade();
			//Verificando se Unidade logada é uma Faculdade ou Câmpus(Se valor for nulo Unidade Logada é uma Faculdade)
			if (unidade != null) {
				//Adicionando Faculdade do usuário automático no Objeto UsuárioCadastro no Campo Unidade do usuário
				usuarioCadastro.setUnidade(autenticacaoBean.getUsuarioAutenticado().getUnidade());
		
				//Criptografando senha para o formato MD5 para Edição da senha de forma segura
				usuarioCadastro.setSenhaUsuario((DigestUtils.md5Hex(usuarioCadastro.getSenhaUsuario())));
				//Executando executando método salvar criado na Classe UnidadeDAO
				usuarioDAO.salvar(usuarioCadastro);
				
				//Executando método de exibição de mensagem na Classe FacesUtil e criando mensagem
				FacesUtil.msgSucesso("Usuário Salvo com Sucesso!");
				usuarioCadastro = new Usuario();
			//Caso não seja nulo então Faculdade é um Câmpus(Valores dos atributos serão passados da página)
			}else{
				//Criptografando senha para o formato MD5 para Edição da senha de forma segura
				usuarioCadastro.setSenhaUsuario(DigestUtils.md5Hex(usuarioCadastro.getSenhaUsuario()));
				//Executando executando método salvar criado na Classe UnidadeDAO
				usuarioDAO.salvar(usuarioCadastro);
				
				//Executando método de exibição de mensagem na Classe FacesUtil e criando mensagem
				FacesUtil.msgSucesso("Usuário Salvo com Sucesso!");
				usuarioCadastro = new Usuario();			
			}
			
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de Salvar do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Usuário não pode ser Salvo, dados estão em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Salvar Usuário!" + ex.getMessage());
			}
		}
	}

	public void carregarPesquisa(){
		//Executando o Método para verificar a função do usuário
		verificaFuncaoUsuario();
		try {					
			//Pegando o Código da Unidade do usuário autenticado no sistema para buscar os usuarios da unidade
			Long codUnidade = autenticacaoBean.getUsuarioAutenticado().getUnidade().getCodUnidade();
			 
			//Estanciando o DAO de usuário para manipular os dados do usuario pelos metodos contido na DAO
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
			//Verificando qual o nível do usuário logado para carregar lista de usuário pesonalizada
			//Se usuário for gestor lista geral e carregada
			if (gestor != null) {
				listaUsuarios = usuarioDAO.listar();
				
				UnidadeDAO unidadeDAO = new UnidadeDAO();
				listaUnidade = unidadeDAO.listar();
				
				//Se usuário não for gestor é carregada lista de usuário da Unidade
			}else{
				//Executando método Buscar por Código criado na classe DAO e inserindo em variável
				listaUsuarios = usuarioDAO.usuariosUnidade(codUnidade);
					
			}
			usuarioCadastro = new Usuario();
			
		} catch (RuntimeException ex) {
			FacesUtil.msgErro("Erro ao Carregar Pesquisa de Usuário!" + ex.getMessage());
		}
	}
	
	// Criando Método Carregar os Cadastros da classe
	public void carregarCadastro(){
		try {
			//Pegando valor passado da página de cadastro
			String valor = FacesUtil.getParam("codUsuario");
			
			//Executando verificação se variável contém valores
			if (valor != null) {
				//Criando um variável temporária codigo e convertendo o a varável valor
				Long codigo = Long.parseLong(valor);
				
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				//Executando método Buscar por Código criado na classe UnidadeDAO e inserindo em variável
				usuarioCadastro = usuarioDAO.buscarPorCodigo(codigo);
				//Armazenando senha do usuário para edição de usuário sem alterar a senha.
				senha = usuarioCadastro.getSenhaUsuario();
				
			} else {
				usuarioCadastro = new Usuario();
			}
			StatusDAO statusDAO = new StatusDAO();
			//Executando método listar dentro da classe EnderecoDAO e carregando um lista de endereços
			listaStatus = statusDAO.buscarPorInterv(1L, 2L);
			
			//Pegando codigo da unidade e câmpus da unidade logada no sistema
			Long codUnidadeLogada = autenticacaoBean.getUsuarioAutenticado().getUnidade().getCodUnidade();
			Unidade campiUnidade = autenticacaoBean.getUsuarioAutenticado().getUnidade().getUnidade();
			
			//Criando uma nova UnidadeDAO para acesso os métodos contidos na DAO
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			//Verificando Unidade logada para carregar lista de unidades personalizadas					
			if (codUnidadeLogada != 1 && campiUnidade == null) {
				listaUnidade = unidadeDAO.buscarCampiUnidade(codUnidadeLogada);
			
			}else if(codUnidadeLogada == 1L){
				listaUnidade = unidadeDAO.listar();
				
			}
			
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Cadastro de Usuários!" + ex.getMessage());
		}	
	}

	public void Excluir(){
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuarioCadastro);
			
			FacesUtil.msgSucesso("Usuário Excluído com Sucesso!");
			usuarioCadastro = new Usuario();
			
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de exclusão do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Usuário não pode ser Excluído! Usuário "+
				usuarioCadastro.getNomeUsuario() +" está em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Excluir Usuário!" + ex.getMessage());
			}
		}
	}
	
	//Método de edição do usuário
	public void Editar(){
		try {
			//Criando um novo UsuarioDAO para acesso aos metodos da DAO
			UsuarioDAO usuarioDAO = new UsuarioDAO();	
			
			//Verificando se senha do usuário está vazia(Se sim usuário não quer editar senha, mesma senha é salva)
			if (usuarioCadastro.getSenhaUsuario() == "") {
				//Inserindo a mesma senha que usuario usava no objeto usuarioCadastro no atributo senha.
				usuarioCadastro.setSenhaUsuario(senha);
				//Executando método de edição do usuário
				usuarioDAO.editar(usuarioCadastro);
				
				FacesUtil.msgSucesso("Usuário Editado com Sucesso!");
				usuarioCadastro = new Usuario();
				
			} else {
				//Criptografando senha para o formato MD5 para Edição da senha de forma segura
				usuarioCadastro.setSenhaUsuario(DigestUtils.md5Hex(usuarioCadastro.getSenhaUsuario()));
				//Executando método de edição de usuário
				usuarioDAO.editar(usuarioCadastro);
				
				FacesUtil.msgSucesso("Usuário Editado com Sucesso!");
				usuarioCadastro = new Usuario();
			}			
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de edição do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Usuário não pode ser Editado, dados estão em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Editar Usuário!" + ex.getMessage());
			}
		}
	}
	
	//Criando método que verifica a funçao do usuário logado acessando AcessoPhaseListener para pegar valores
	public void verificaFuncaoUsuario(){
		try {
			//Pegando a funcao do usuario logado e o codigo da Unidade no classe autenticacaoBean
			Long funcaoUsuarioLogado = autenticacaoBean.getUsuarioAutenticado().getFuncaoUsuario().getcodFuncaoUsuario();
			Long unidadeLogada = autenticacaoBean.getUsuarioAutenticado().getUnidade().getCodUnidade();
			
			//Pegando verificando se unidade é gestora do sistema para liberação de permissões
			if (unidadeLogada == 1) {
				unidadeGestora = autenticacaoBean.getUsuarioAutenticado().getUnidade().getCodUnidade().toString();
			
			}
			//Condição que verifica a função do usuário logado
			//Se código da função do usuáro for igual a 1 Usuário é um gestor do sistema(Todos as Funções são exibidas para o usuário)
			if (funcaoUsuarioLogado == 1) {
				gestor = 1L;
				visivel = funcaoUsuarioLogado;
			
				//Se código da função do usuáro for igual a 2 Usuário é um Administrador do sistema(Função de Gestor não é exibida para cadastro)	
			}else if(funcaoUsuarioLogado == 2) {
				administrador = 2L;
				visivel = funcaoUsuarioLogado;
			
				//Se código da função do usuáro for igual a 3 Usuário é um supervisor do sistema(Funções Gestor e Administrados não são exibidas para o usuário)	
			}if (funcaoUsuarioLogado == 3) {
				supervisor = 3L;
				visivel = funcaoUsuarioLogado;
				
			}			
		} catch (RuntimeException ex) {
			
			FacesUtil.msgErro("Erro ao carregar Função do Usuário Logado!");
		}		
	}
	
	//Método responsável pelo carregamento personalizado da lista da função dos usuários de acordo com acesso da unidade
	public void carregarListaFuncaoUsuario(){
		try {
			Long codUnidadeLogada = autenticacaoBean.getUsuarioAutenticado().getUnidade().getCodUnidade();
			Unidade campiUnidade = autenticacaoBean.getUsuarioAutenticado().getUnidade().getUnidade(); 
			//Criando uma nova FuncaoUsuarioDAO para acesso aos metodos contidos na DAO
			FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO();
			//Verificando Nível de acesso da Unidade para carregar lista de função personalizada
			if (campiUnidade == null) {
				listaFuncaoUsuario = funcaoUsuarioDAO.buscarPorCodMai(2L);
				
				//Verificando se Unidade logada é um Campi ou uma Faculdade(Se for diferente de nulo é um Campi)
			}if(campiUnidade != null){
				listaFuncaoUsuario = funcaoUsuarioDAO.buscarPorCodMai(2L);
					
			}if(codUnidadeLogada == 1L){
					//Executando executando método listar criado na Classe DAO
					listaFuncaoUsuario = funcaoUsuarioDAO.listar();						
				}
			
		//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro				
		} catch (RuntimeException ex) {
			
			FacesUtil.msgErro("Erro ao carregar Lista de Função Usuário!");
		}
	}
	
	//Método para emitir mensagem de edição de usuário PROFESSOR e ALUNO
	public void emitirMsgEdicaoUsuario(){
		FacesUtil.msgAlerta("Edições serão aplicadas da proxima vez que Logar no Sistema!");
		
	}
}
