package br.com.sisger.controle;

import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.sisger.modelo.Usuario;
import br.com.sisger.visao.AutenticacaoBean;

//Criando calsse que filtra acesso as páginas do sistema.
//Implementando PhaseListener para acesso aos atributos das páginas e do BEAN AUTENTICACAO USUARIO
@SuppressWarnings("serial")
public class AcessoPhaseListener implements PhaseListener {
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	// Determina que ação será cometida Depois do RESTORE_VIEW
	@Override
	public void afterPhase(PhaseEvent evento) {
		// Usando FacesContex para saber qual página foi acessada (Em que página o usuáio está)
		// Capitura o Contexto da aplicação
		FacesContext facesContext = evento.getFacesContext();
		// Usando o UIViwRoot para navegar na árvore de componente da página (Na
		// página o usuário solicitou)
		UIViewRoot uiViewRoot = facesContext.getViewRoot();
		// Pegando o nome da página com o getViewId
		String paginaAcessada = uiViewRoot.getViewId();
		String voltarPagina = "/paginas/homerPrincipal.xhtml";
		
		// Verificando se contem uma determinados valor na pángina, determinando se ela é pública.
		boolean paginaLogin = paginaAcessada.contains("principal.xhtml");
		boolean paginaFaculdadeCadastro = paginaAcessada.contains("faculdadeCadastro.xhtml");
		boolean paginaFuncaoUsuario = paginaAcessada.contains("funcaoUsuarioCadastro.xhtml");
		boolean paginaStatusCadastro = paginaAcessada.contains("statusCadastro.xhtml");
		boolean paginaEstadoCadastro = paginaAcessada.contains("estadoCadastro.xhtml");
		boolean paginaCidadeCadastro = paginaAcessada.contains("cidadeCadastro.xhtml");
		boolean paginafaculdadeEditar = paginaAcessada.contains("faculdadeEditar.xhtml");
		boolean paginaRecursoCadastro = paginaAcessada.contains("recursoCadastro.xhtml");
		boolean paginaSubCategoriaCadastro = paginaAcessada.contains("subCategoriaCadastro.xhtml");
		boolean paginaUsuarioFaculdadeCadastro = paginaAcessada.contains("usuarioFaculdadeCadastro.xhtml");
		
		//Método para impedir acesso via URL na tela de Login
		
		// Analisando as páginas que são de acesso privado.
		// Usando converso "!" para verificar se é a página ou não
		if (!paginaLogin) {
			// Criando um ExternalContext para conseguir pegar o Usuário logado no sistema
			ExternalContext externalContext = facesContext.getExternalContext();
			// Pegando todas os valores carregados na sessão com o getSessionMap
			Map<String, Object> map = externalContext.getSessionMap();
			// Pegando o os valores contidos no Bean de autenticação.
			AutenticacaoBean autenticacaoBean = (AutenticacaoBean) map.get("autenticacaoBean");

			//Pegando qual usuário logado que está no Bean autenticação e adicionando ao novo usuário
			usuario = autenticacaoBean.getUsuarioAutenticado();
			
			//Verificando se dados do usuários são nulos (Se nulo tentativa de burlar o sistema realizada)
			if (usuario.getCodUsuario() == null) {
				FacesUtil.msgErro("Acesso Inválido!");
				
				//Capturando o Application para pegar informações e obrigar a navegação
				Application application = facesContext.getApplication(); 
				//Criando o NavigationHandler para poder obrigar a navegação do usuário
				NavigationHandler navigationHandler = application.getNavigationHandler();
						
				//Realizando navegação do usuário forçada por estar tentando bular Login do sistema
				navigationHandler.handleNavigation(facesContext, null, "/paginas/principal.xhtml?faces-redirect=true");
			}
			
		}else{		
			//Método para impedir acesso via URL nas telas privadas	
			if (paginaFaculdadeCadastro) {
				// Criando um ExternalContext para conseguir pegar o Usuário logado no sistema
				ExternalContext externalContext = facesContext.getExternalContext();
				// Pegando todas os valores carregados na sessão com o getSessionMap
				Map<String, Object> map = externalContext.getSessionMap();
				// Pegando o os valores contidos no Bean de autenticação.
				AutenticacaoBean autenticacaoBean = (AutenticacaoBean) map.get("autenticacaoBean");
			
				usuario = autenticacaoBean.getUsuarioAutenticado();
				//Fazendo verificação do Usuário nulo para evitar NullPointException(Erro JAVA)
				if (usuario.getCodUsuario() != null){
					//Verificando se existe uma unidade cadastra dentro do codigo do Câmpus(Se não, faculdade é um Câmpus)
					if (autenticacaoBean.getUsuarioAutenticado().getUnidade().getUnidade() != null) {
						FacesUtil.msgErro("Acesso Inválido");					
						
						Application application = facesContext.getApplication();
						NavigationHandler navigationHandler = application.getNavigationHandler();
						navigationHandler.handleNavigation(facesContext, null , voltarPagina);
						
					}	
				}
			}
		
			//Verificando se página acessada é a página de cadastro da funão do usuário
			if (paginaFuncaoUsuario) {
				// Criando um ExternalContext para conseguir pegar o Usuário logado no sistema
				ExternalContext externalContext = facesContext.getExternalContext();
				// Pegando todas os valores carregados na sessão com o getSessionMap
				Map<String, Object> map = externalContext.getSessionMap();
				// Pegando o os valores contidos no Bean de autenticação.
				AutenticacaoBean autenticacaoBean = (AutenticacaoBean) map.get("autenticacaoBean");
			
				usuario = autenticacaoBean.getUsuarioAutenticado();
				if (usuario.getCodUsuario() != null){
					//Verificando se existe uma unidade cadastra dentro do codigo do Câmpus(Se não, faculdade é um Câmpus)
					if (autenticacaoBean.getUsuarioAutenticado().getUnidade().getUnidade() != null) {
						FacesUtil.msgErro("Acesso Inválido");
						
						Application application = facesContext.getApplication();
						NavigationHandler navigationHandler = application.getNavigationHandler();
						navigationHandler.handleNavigation(facesContext, null , voltarPagina);
					}	
				}
			}
			
			//Verificando se página acessada é a página de cadastro de Status
			if (paginaStatusCadastro) {
				// Criando um ExternalContext para conseguir pegar o Usuário logado no sistema
				ExternalContext externalContext = facesContext.getExternalContext();
				// Pegando todas os valores carregados na sessão com o getSessionMap
				Map<String, Object> map = externalContext.getSessionMap();
				// Pegando o os valores contidos no Bean de autenticação.
				AutenticacaoBean autenticacaoBean = (AutenticacaoBean) map.get("autenticacaoBean");
			
				usuario = autenticacaoBean.getUsuarioAutenticado();
				if (usuario.getCodUsuario() != null){
					//Verificando se existe uma unidade cadastra dentro do codigo do Câmpus(Se não, faculdade é um Câmpus)
					if (autenticacaoBean.getUsuarioAutenticado().getUnidade().getUnidade() != null) {	
						FacesUtil.msgErro("Acesso Inválido");
					
						Application application = facesContext.getApplication();
						NavigationHandler navigationHandler = application.getNavigationHandler();
						navigationHandler.handleNavigation(facesContext, null , voltarPagina);	
					}	
				}
			}
		}
		
		if (paginaCidadeCadastro || paginaEstadoCadastro || paginaStatusCadastro) {
			// Criando um ExternalContext para conseguir pegar o Usuário logado no sistema
			ExternalContext externalContext = facesContext.getExternalContext();
			// Pegando todas os valores carregados na sessão com o getSessionMap
			Map<String, Object> map = externalContext.getSessionMap();
			// Pegando o os valores contidos no Bean de autenticação.
			AutenticacaoBean autenticacaoBean = (AutenticacaoBean) map.get("autenticacaoBean");
		
			usuario = autenticacaoBean.getUsuarioAutenticado();
			
			if (usuario.getCodUsuario() == null) {
				FacesUtil.msgErro("Acesso Inválido!");
				
				//Capturando o Application para pegar informações e obrigar a navegação
				Application application = facesContext.getApplication(); 
				//Criando o NavigationHandler para poder obrigar a navegação do usuário
				NavigationHandler navigationHandler = application.getNavigationHandler();
						
				//Realizando navegação do usuário forçada por estar tentando bular Login do sistema
				navigationHandler.handleNavigation(facesContext, null, "/paginas/principal.xhtml");
			}else{
				if (usuario.getFuncaoUsuario().getcodFuncaoUsuario() == 2L){			
					FacesUtil.msgErro("Acesso Inválido");
				
					Application application = facesContext.getApplication();
					NavigationHandler navigationHandler = application.getNavigationHandler();
					navigationHandler.handleNavigation(facesContext, null , voltarPagina);
				}
			}
		}
		
		//Verificando a página acessada
		if (paginaFaculdadeCadastro || paginaFuncaoUsuario || paginaStatusCadastro || paginaEstadoCadastro || paginaCidadeCadastro
		 || paginafaculdadeEditar || paginaRecursoCadastro || paginaSubCategoriaCadastro || paginaUsuarioFaculdadeCadastro){
			// Criando um ExternalContext para conseguir pegar o Usuário logado no sistema
			ExternalContext externalContext = facesContext.getExternalContext();
			// Pegando todas os valores carregados na sessão com o getSessionMap
			Map<String, Object> map = externalContext.getSessionMap();
			// Pegando o os valores contidos no Bean de autenticação.
			AutenticacaoBean autenticacaoBean = (AutenticacaoBean) map.get("autenticacaoBean");
				
			usuario = autenticacaoBean.getUsuarioAutenticado();
			
			if(usuario.getCodUsuario() == null){				
				//Capturando o Application para pegar informações e obrigar a navegação
				Application application = facesContext.getApplication(); 
				//Criando o NavigationHandler para poder obrigar a navegação do usuário
				NavigationHandler navigationHandler = application.getNavigationHandler();
				FacesUtil.msgErro("Acesso Inválido!");
				
				//Realizando navegação do usuário forçada por estar tentando bular Login do sistema
				navigationHandler.handleNavigation(facesContext, null, "/paginas/principal.xhtml");
				
			//Verificando se usuário não é gestor nem administrador(Códigos maiores que 2)
			}else if (usuario.getFuncaoUsuario().getcodFuncaoUsuario() > 2L){
				FacesUtil.msgErro("Acesso Inválido");
				
				Application application = facesContext.getApplication();
				NavigationHandler navigationHandler = application.getNavigationHandler();
				navigationHandler.handleNavigation(facesContext, null , voltarPagina);
			}
		}
	}

	// Determina que ação será cometida Antes do RESTORE_VIEW
	@Override
	public void beforePhase(PhaseEvent evento) {

	}

	// Definindo em que momento da requisição será executado o PhaseListener
	@Override
	public PhaseId getPhaseId() {

		// Determinando que PhaseListener será executado no momento da criação da árvore da página
		return PhaseId.RESTORE_VIEW;
	}

}
