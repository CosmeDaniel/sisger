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
// Definindo o tempo de vida útil dentro da sessão
public class AutenticacaoBean { // Classe responsável pela autenticação no sistema
	private Usuario usuarioAutenticado;
	private String funcaoUsuarioAutenticado;
	private String emailUsuario;
	private String senha;
	
	public synchronized Usuario getUsuarioAutenticado() {
		//Verificando se Usuário é nulo caso sim usuário nove e criado e preenchido.
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
		//Setando o e-mail informado para recuperar senha em variável toda vez que ela é acessada
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

	//Criando Método que verifica dados da tela e autentica
	public String autenticarSistema(){
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			//pegando valores da tela e inserindo em uma variável usuário
			usuarioAutenticado = usuarioDAO.autenticar(usuarioAutenticado.getLoginUsuario(), DigestUtils.md5Hex(usuarioAutenticado.getSenhaUsuario()));
			//Adicionando a função do usuário na váriável de verificação de função
			String valorFuncao = usuarioAutenticado.getFuncaoUsuario().getDescricaoFuncaoUsuario();
			funcaoUsuarioAutenticado = null;
			if (usuarioAutenticado == null || usuarioAutenticado.getStatus().getCodStatus() == 2) {
				FacesUtil.msgErro("Login ou Senha Inválido!");
				
				return null;
			} else{
				//Criando um novo contexto para passar um parametro externo
				RequestContext fechaLogin = RequestContext.getCurrentInstance();
				//Adicionando valores de parametros passados
				fechaLogin.addCallbackParam("logado", true);
				
					//Verificando a função do usuário para permitir acesso aos menus do sistema
					if (valorFuncao.equals("ADMINISTRADOR")){
						funcaoUsuarioAutenticado = valorFuncao;
					}

				FacesUtil.msgSucesso("Bem vindo ao Sisger: " + usuarioAutenticado.getNomeUsuario());
				return "/paginas/homerPrincipal.xhtml?faces-redirect=true";
				
			}
			
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.msgErro("Login ou Senha Inválido!");
			
			return null;
		}
	}
	
	//Método para sair do Sistema
	public String sairSistema(){
		if (usuarioAutenticado != null) {
			//Setando valor nulo ao usuário Autenticado para que sessão seja encerrada.
			usuarioAutenticado = null;
		}
		//Direcionando para página de saida
		return "/paginas/principal.xhtml?faces-redirect=true";
	}
	
	//Método que gera senha provisória para usuário
	public void geraSenhaProvisoria(){
		//Criando pacote UUID randomico com valores  
		UUID uuid = UUID.randomUUID();
		//Informando que será ultizado String do pacote UUI(Todos os dados)
		String myRandom = uuid.toString();
		//Sentando senha provisoira na variável e no cadastro do usuário
		senha = myRandom.substring(0,8);		
	}
	
	//Metodo usado para recuperar o E-mail do Usuario
	public void recuperarEmailUsuario(){
		try {
			//Estanciando um nov usuarioDAO para ter acesso os metodo de pesquisa de e-mail
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			//Executando pesquisa com email passado e setando usuario do email se existir no DB
			Usuario usuario = usuarioDAO.buscarPorEmailString(emailUsuario);
			//Verificando se retorna da busca é nullo(Se sim retorno de e-mail não cadastrado é emitido)
			if (usuario == null) {
				FacesUtil.msgAlerta("Email " + emailUsuario + " não encontrado! Contate o Administrador.");
				
			}else{			
				//Setando e-mail do usuario solicitante no destinatário do e-mail(Classe JavaMailApp)
				JavaMailApp.setEmailParaEnvio(emailUsuario);
				//Setando Login de usuario solicitante no e-mail(Classe JavaMailApp)
				JavaMailApp.setLoginParaEnvio(usuario.getLoginUsuario());
				//Setando senha provisória de usuario solicitante no e-mail(Classe JavaMailApp)
				geraSenhaProvisoria();
				JavaMailApp.setSenhaProvisoria(senha);
				//Executando o Método de Envio do e-mail(Classe JavaMailApp)
				JavaMailApp.enviarEmail();
				
				//Verificando se e-mail foi enviado com sucesso(Se Sim alteração na senha do usuario no DB é realizada)
				if (JavaMailApp.isVerificaEnvio() == true) {
					//Criando um novo contexto para passar um parametro externo para fechar tela
					RequestContext fechaRecuperaEmail = RequestContext.getCurrentInstance();
					//Adicionando valor verdadeiro no parametro passado
					fechaRecuperaEmail.addCallbackParam("fecharTelaRecuperaEmail", true);
					//Alterando Senha do usuário solicitante
					usuario.setSenhaUsuario(DigestUtils.md5Hex(senha));
					
					usuarioDAO.editar(usuario);
					
					FacesUtil.msgSucesso("Um E-mail foi enviado para, " + emailUsuario + " para alteração da Senha!");	
				}
			}
			
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.msgErro("Erro ao recuperar Senha do Usuário!" +ex.getMessage());
			
		}
	}
}
