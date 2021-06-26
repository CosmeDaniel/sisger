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
//Definindo o tempo de vida útil dentro da sessão
@ViewScoped

// Criando um objeto e varáveis para receber dados
public class FuncaoUsuarioBean {

	private FuncaoUsuario funcaoUsuarioCadastro;
	private List<FuncaoUsuario> listaFuncaoUsuarios;
	private ArrayList<FuncaoUsuario> listaFuncaoUsuariosFiltrados;
	private String acao;
	private Long codigo;

	//Executando Injeção de dependência para acesso ao dados da Unidade Autenticada no sistema
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;
	
	//Criando métodos de acessos aos objetos criado, Gets e Sets
	public synchronized FuncaoUsuario getFuncaoUsuarioCadastro() {
		//Verificando se obejto é nulo ao ser acessado, se nulo um novo objeto é instânciado
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
	
	// Criando Método para Criar um novo objeto da classe
	public void novo(){
		funcaoUsuarioCadastro = new FuncaoUsuario();	
	}
	
	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}
	
	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	// Criando Método para excutar o método Salvar contido na DAO
	public void salvar(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			//Criando uma nova FuncaoUsuarioDAO para acesso aos metodos contidos na DAO
			FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO();
			//Executando executando método salvar criado na Classe DAO
			funcaoUsuarioDAO.salvar(funcaoUsuarioCadastro);
			
			//Executando método de exibição de mensagem na Classe FacesUtil e criando mensagem
			FacesUtil.msgSucesso("Função Salva com Sucesso!");
			funcaoUsuarioCadastro = new FuncaoUsuario();
			
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de Editar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Função não pode ser Salava, dados está em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Salvar Função!" + ex.getMessage());
			}
		}
	}
	
	// Criando Método para excutar o método Listar contido na DAO
	public void carregarPesquisa(){
		//Método obrigatório para garantir teste antes da execução dos comandos		
		try {
			Long codigoUnidadeLogada = autenticacaoBean.getUsuarioAutenticado().getUnidade().getCodUnidade();
			Unidade campiUnidade = autenticacaoBean.getUsuarioAutenticado().getUnidade().getUnidade(); 
			//Criando uma nova FuncaoUsuarioDAO para acesso aos metodos contidos na DAO
			FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO();
			//Verificando Nível de acesso da Unidade para carregar lista de função personalizada
			if (codigoUnidadeLogada != 1L) {
				listaFuncaoUsuarios = funcaoUsuarioDAO.buscarPorCodMai(2L);
			
			//Verificando se Unidade logada é um Campi ou uma Faculdade(Se for diferente de nulo é um Campi)
			}else if(campiUnidade != null){
				listaFuncaoUsuarios = funcaoUsuarioDAO.buscarPorCodMai(3L);
				
				}else{
				//Executando executando método listar criado na Classe DAO
				listaFuncaoUsuarios = funcaoUsuarioDAO.listar();	
				
			}
		
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
			} catch (RuntimeException ex) {
				//Capiturando o erro ocorrido para ser impresso com a mensagem
			FacesUtil.msgErro("Erro Carregar Lista de Função!" + ex.getMessage());
		}
	}
	
	// Criando Método para excutar o método Buscar por Código contido na DAO
	public void carregarCadastro(){
		//Método obrigatório para garantir teste antes da execução dos comandos		
		try {
			//Pegando valor passado da página de cadastro
			String valor = FacesUtil.getParam("codFuncaoUsuario"); 
			
			//Executando verificação se variável contém valores
			if (valor != null) {
				//Criando um variável temporária codigo e convertendo o a varável valor
				Long codigo = Long.parseLong(valor);
				
				//Criando uma nova FuncaoUsuarioDAO para acesso aos metodos contidos na DAO
				FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO();
				//Executando método Buscar por Código criado na classe DAO e inserindo em variável
				funcaoUsuarioCadastro = funcaoUsuarioDAO.buscarPorCodigo(codigo);
				
			} else {
				funcaoUsuarioCadastro = new FuncaoUsuario();
			}
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
			} catch (RuntimeException ex) {
				//Capiturando o erro ocorrido para ser impresso com a mensagem
			FacesUtil.msgErro("Erro ao Carregar Cadastro de Função!" + ex.getMessage());
		}
	}
	
	// Criando Método para excutar o método Exclulir contido na DAO
	public void excluir(){
		//Método obrigatório para garantir teste antes da execução dos comandos		
		try {
			//Criando uma nova FuncaoUsuarioDAO para acesso aos metodos contidos na DAO
			FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO();
			funcaoUsuarioDAO.excluir(funcaoUsuarioCadastro);
			
			FacesUtil.msgSucesso("Função Excluída com Sucesso!");
			funcaoUsuarioCadastro = new FuncaoUsuario();
			
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
			} catch (RuntimeException ex) {
				//Atribuiíndo erro capturado a uma variável
				String erroUso = ex.getMessage();
				//Capiturando mensagem de erro e gerando impressão do log de erro para análise
				ex.printStackTrace();
				//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
				if (erroUso.equals("could not execute statement")) {
					//Mensagem de tratamento de não permissão de exclusão do valor por estar sendo usado em algum cadastro
					FacesUtil.msgErro("Função não pode ser Excluída! Funão "+
					funcaoUsuarioCadastro.getDescricaoFuncaoUsuario() +" está em uso.");
				}else{
					//Capiturando o erro ocorrido para ser impresso com a mensagem
					FacesUtil.msgErro("Erro ao Excluir Função!" + ex.getMessage());
				}
			}
	}

	// Criando Método para excutar o método Editar contido na DAO
	public void editar(){
		//Método obrigatório para garantir teste antes da execução dos comandos		
		try {
			//Criando uma nova FuncaoUsuarioDAO para acesso aos metodos contidos na DAO
			FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO();
			funcaoUsuarioDAO.editar(funcaoUsuarioCadastro);
			
			FacesUtil.msgSucesso("Função Editada com Sucesso!");
			funcaoUsuarioCadastro = new FuncaoUsuario();
			
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de Editar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Função não pode ser Editada, dados está em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Editada Função!" + ex.getMessage());
			}
		}
	}
}
