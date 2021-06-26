package br.com.sisger.visao;

import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.context.RequestContext;

import br.com.sisger.controle.FacesUtil;
import br.com.sisger.controle.JavaMailApp;
import br.com.sisger.dao.UsuarioDAO;
import br.com.sisger.modelo.Usuario;

@ManagedBean
// Definindo a classe como um Mange Bean
@SessionScoped
// Definindo o tempo de vida �til dentro da sess�o
public class AutenticacaoBean { // Classe respons�vel pela autentica��o no sistema
	private Usuario usuarioAutenticado;
	private String funcaoUsuarioAutenticado;
	private String emailUsuario;
	private String senha;
	
	public synchronized Usuario getUsuarioAutenticado() {
		//Verificando se Usu�rio � nulo caso sim usu�rio nove e criado e preenchido.
		if (usuarioAutenticado == null) { 
			usuarioAutenticado = new Usuario();
		}
		return usuarioAutenticado;
	}

	public synchronized void setUsuarioAutenticado(Usuario usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}
	
	public String getFuncaoUsuarioAutenticado() {
		return funcaoUsuarioAutenticado;
	}
	
	public void setFuncaoUsuarioAutenticado(String funcaoUsuarioAutenticado) {
		this.funcaoUsuarioAutenticado = funcaoUsuarioAutenticado;
	}
	
	public String getEmailUsuario() {
		//Setando o e-mail informado para recuperar senha em vari�vel toda vez que ela � acessada
		emailUsuario = FacesUtil.getParam("emailDigitado");
		return emailUsuario;
	}
	
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	
	public synchronized String getSenha() {
		return senha;
	}

	public synchronized void setSenha(String senha) {
		this.senha = senha;
	}

	//Criando M�todo que verifica dados da tela e autentica
	public String autenticarSistema(){
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			//pegando valores da tela e inserindo em uma vari�vel usu�rio
			usuarioAutenticado = usuarioDAO.autenticar(usuarioAutenticado.getLoginUsuario(), DigestUtils.md5Hex(usuarioAutenticado.getSenhaUsuario()));
			//Adicionando a fun��o do usu�rio na v�ri�vel de verifica��o de fun��o
			String valorFuncao = usuarioAutenticado.getFuncaoUsuario().getDescricaoFuncaoUsuario();
			funcaoUsuarioAutenticado = null;
			if (usuarioAutenticado == null || usuarioAutenticado.getStatus().getCodStatus() == 2) {
				FacesUtil.msgErro("Login ou Senha Inv�lido!");
				
				return null;
			} else{
				//Criando um novo contexto para passar um parametro externo
				RequestContext fechaLogin = RequestContext.getCurrentInstance();
				//Adicionando valores de parametros passados
				fechaLogin.addCallbackParam("logado", true);
				
					//Verificando a fun��o do usu�rio para permitir acesso aos menus do sistema
					if (valorFuncao.equals("ADMINISTRADOR")){
						funcaoUsuarioAutenticado = valorFuncao;
					}

				FacesUtil.msgSucesso("Bem vindo ao Sisger: " + usuarioAutenticado.getNomeUsuario());
				return "/paginas/homerPrincipal.xhtml?faces-redirect=true";
				
			}
			
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.msgErro("Login ou Senha Inv�lido!");
			
			return null;
		}
	}
	
	//M�todo para sair do Sistema
	public String sairSistema(){
		if (usuarioAutenticado != null) {
			//Setando valor nulo ao usu�rio Autenticado para que sess�o seja encerrada.
			usuarioAutenticado = null;
		}
		//Direcionando para p�gina de saida
		return "/paginas/principal.xhtml?faces-redirect=true";
	}
	
	//M�todo que gera senha provis�ria para usu�rio
	public void geraSenhaProvisoria(){
		//Criando pacote UUID randomico com valores  
		UUID uuid = UUID.randomUUID();
		//Informando que ser� ultizado String do pacote UUI(Todos os dados)
		String myRandom = uuid.toString();
		//Sentando senha provisoira na vari�vel e no cadastro do usu�rio
		senha = myRandom.substring(0,8);		
	}
	
	//Metodo usado para recuperar o E-mail do Usuario
	public void recuperarEmailUsuario(){
		try {
			//Estanciando um nov usuarioDAO para ter acesso os metodo de pesquisa de e-mail
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			//Executando pesquisa com email passado e setando usuario do email se existir no DB
			Usuario usuario = usuarioDAO.buscarPorEmailString(emailUsuario);
			//Verificando se retorna da busca � nullo(Se sim retorno de e-mail n�o cadastrado � emitido)
			if (usuario == null) {
				FacesUtil.msgAlerta("Email " + emailUsuario + " n�o encontrado! Contate o Administrador.");
				
			}else{			
				//Setando e-mail do usuario solicitante no destinat�rio do e-mail(Classe JavaMailApp)
				JavaMailApp.setEmailParaEnvio(emailUsuario);
				//Setando Login de usuario solicitante no e-mail(Classe JavaMailApp)
				JavaMailApp.setLoginParaEnvio(usuario.getLoginUsuario());
				//Setando senha provis�ria de usuario solicitante no e-mail(Classe JavaMailApp)
				geraSenhaProvisoria();
				JavaMailApp.setSenhaProvisoria(senha);
				//Executando o M�todo de Envio do e-mail(Classe JavaMailApp)
				JavaMailApp.enviarEmail();
				
				//Verificando se e-mail foi enviado com sucesso(Se Sim altera��o na senha do usuario no DB � realizada)
				if (JavaMailApp.isVerificaEnvio() == true) {
					//Criando um novo contexto para passar um parametro externo para fechar tela
					RequestContext fechaRecuperaEmail = RequestContext.getCurrentInstance();
					//Adicionando valor verdadeiro no parametro passado
					fechaRecuperaEmail.addCallbackParam("fecharTelaRecuperaEmail", true);
					//Alterando Senha do usu�rio solicitante
					usuario.setSenhaUsuario(DigestUtils.md5Hex(senha));
					
					usuarioDAO.editar(usuario);
					
					FacesUtil.msgSucesso("Um E-mail foi enviado para, " + emailUsuario + " para altera��o da Senha!");	
				}
			}
			
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.msgErro("Erro ao recuperar Senha do Usu�rio!" +ex.getMessage());
			
		}
	}
}
