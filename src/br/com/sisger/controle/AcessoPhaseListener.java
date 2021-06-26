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

//Criando calsse que filtra acesso as p�ginas do sistema.
//Implementando PhaseListener para acesso aos atributos das p�ginas e do BEAN AUTENTICACAO USUARIO
@SuppressWarnings("serial")
public class AcessoPhaseListener implements PhaseListener {
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	// Determina que a��o ser� cometida Depois do RESTORE_VIEW
	@Override
	public void afterPhase(PhaseEvent evento) {
		// Usando FacesContex para saber qual p�gina foi acessada (Em que p�gina o usu�io est�)
		// Capitura o Contexto da aplica��o
		FacesContext facesContext = evento.getFacesContext();
		// Usando o UIViwRoot para navegar na �rvore de componente da p�gina (Na
		// p�gina o usu�rio solicitou)
		UIViewRoot uiViewRoot = facesContext.getViewRoot();
		// Pegando o nome da p�gina com o getViewId
		String paginaAcessada = uiViewRoot.getViewId();
		String voltarPagina = "/paginas/homerPrincipal.xhtml";
		
		// Verificando se contem uma determinados valor na p�ngina, determinando se ela � p�blica.
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
		
		//M�todo para impedir acesso via URL na tela de Login
		
		// Analisando as p�ginas que s�o de acesso privado.
		// Usando converso "!" para verificar se � a p�gina ou n�o
		if (!paginaLogin) {
			// Criando um ExternalContext para conseguir pegar o Usu�rio logado no sistema
			ExternalContext externalContext = facesContext.getExternalContext();
			// Pegando todas os valores carregados na sess�o com o getSessionMap
			Map<String, Object> map = externalContext.getSessionMap();
			// Pegando o os valores contidos no Bean de autentica��o.
			AutenticacaoBean autenticacaoBean = (AutenticacaoBean) map.get("autenticacaoBean");

			//Pegando qual usu�rio logado que est� no Bean autentica��o e adicionando ao novo usu�rio
			usuario = autenticacaoBean.getUsuarioAutenticado();
			
			//Verificando se dados do usu�rios s�o nulos (Se nulo tentativa de burlar o sistema realizada)
			if (usuario.getCodUsuario() == null) {
				FacesUtil.msgErro("Acesso Inv�lido!");
				
				//Capturando o Application para pegar informa��es e obrigar a navega��o
				Application application = facesContext.getApplication(); 
				//Criando o NavigationHandler para poder obrigar a navega��o do usu�rio
				NavigationHandler navigationHandler = application.getNavigationHandler();
						
				//Realizando navega��o do usu�rio for�ada por estar tentando bular Login do sistema
				navigationHandler.handleNavigation(facesContext, null, "/paginas/principal.xhtml?faces-redirect=true");
			}
			
		}else{		
			//M�todo para impedir acesso via URL nas telas privadas	
			if (paginaFaculdadeCadastro) {
				// Criando um ExternalContext para conseguir pegar o Usu�rio logado no sistema
				ExternalContext externalContext = facesContext.getExternalContext();
				// Pegando todas os valores carregados na sess�o com o getSessionMap
				Map<String, Object> map = externalContext.getSessionMap();
				// Pegando o os valores contidos no Bean de autentica��o.
				AutenticacaoBean autenticacaoBean = (AutenticacaoBean) map.get("autenticacaoBean");
			
				usuario = autenticacaoBean.getUsuarioAutenticado();
				//Fazendo verifica��o do Usu�rio nulo para evitar NullPointException(Erro JAVA)
				if (usuario.getCodUsuario() != null){
					//Verificando se existe uma unidade cadastra dentro do codigo do C�mpus(Se n�o, faculdade � um C�mpus)
					if (autenticacaoBean.getUsuarioAutenticado().getUnidade().getUnidade() != null) {
						FacesUtil.msgErro("Acesso Inv�lido");					
						
						Application application = facesContext.getApplication();
						NavigationHandler navigationHandler = application.getNavigationHandler();
						navigationHandler.handleNavigation(facesContext, null , voltarPagina);
						
					}	
				}
			}
		
			//Verificando se p�gina acessada � a p�gina de cadastro da fun�o do usu�rio
			if (paginaFuncaoUsuario) {
				// Criando um ExternalContext para conseguir pegar o Usu�rio logado no sistema
				ExternalContext externalContext = facesContext.getExternalContext();
				// Pegando todas os valores carregados na sess�o com o getSessionMap
				Map<String, Object> map = externalContext.getSessionMap();
				// Pegando o os valores contidos no Bean de autentica��o.
				AutenticacaoBean autenticacaoBean = (AutenticacaoBean) map.get("autenticacaoBean");
			
				usuario = autenticacaoBean.getUsuarioAutenticado();
				if (usuario.getCodUsuario() != null){
					//Verificando se existe uma unidade cadastra dentro do codigo do C�mpus(Se n�o, faculdade � um C�mpus)
					if (autenticacaoBean.getUsuarioAutenticado().getUnidade().getUnidade() != null) {
						FacesUtil.msgErro("Acesso Inv�lido");
						
						Application application = facesContext.getApplication();
						NavigationHandler navigationHandler = application.getNavigationHandler();
						navigationHandler.handleNavigation(facesContext, null , voltarPagina);
					}	
				}
			}
			
			//Verificando se p�gina acessada � a p�gina de cadastro de Status
			if (paginaStatusCadastro) {
				// Criando um ExternalContext para conseguir pegar o Usu�rio logado no sistema
				ExternalContext externalContext = facesContext.getExternalContext();
				// Pegando todas os valores carregados na sess�o com o getSessionMap
				Map<String, Object> map = externalContext.getSessionMap();
				// Pegando o os valores contidos no Bean de autentica��o.
				AutenticacaoBean autenticacaoBean = (AutenticacaoBean) map.get("autenticacaoBean");
			
				usuario = autenticacaoBean.getUsuarioAutenticado();
				if (usuario.getCodUsuario() != null){
					//Verificando se existe uma unidade cadastra dentro do codigo do C�mpus(Se n�o, faculdade � um C�mpus)
					if (autenticacaoBean.getUsuarioAutenticado().getUnidade().getUnidade() != null) {	
						FacesUtil.msgErro("Acesso Inv�lido");
					
						Application application = facesContext.getApplication();
						NavigationHandler navigationHandler = application.getNavigationHandler();
						navigationHandler.handleNavigation(facesContext, null , voltarPagina);	
					}	
				}
			}
		}
		
		if (paginaCidadeCadastro || paginaEstadoCadastro || paginaStatusCadastro) {
			// Criando um ExternalContext para conseguir pegar o Usu�rio logado no sistema
			ExternalContext externalContext = facesContext.getExternalContext();
			// Pegando todas os valores carregados na sess�o com o getSessionMap
			Map<String, Object> map = externalContext.getSessionMap();
			// Pegando o os valores contidos no Bean de autentica��o.
			AutenticacaoBean autenticacaoBean = (AutenticacaoBean) map.get("autenticacaoBean");
		
			usuario = autenticacaoBean.getUsuarioAutenticado();
			
			if (usuario.getCodUsuario() == null) {
				FacesUtil.msgErro("Acesso Inv�lido!");
				
				//Capturando o Application para pegar informa��es e obrigar a navega��o
				Application application = facesContext.getApplication(); 
				//Criando o NavigationHandler para poder obrigar a navega��o do usu�rio
				NavigationHandler navigationHandler = application.getNavigationHandler();
						
				//Realizando navega��o do usu�rio for�ada por estar tentando bular Login do sistema
				navigationHandler.handleNavigation(facesContext, null, "/paginas/principal.xhtml");
			}else{
				if (usuario.getFuncaoUsuario().getcodFuncaoUsuario() == 2L){			
					FacesUtil.msgErro("Acesso Inv�lido");
				
					Application application = facesContext.getApplication();
					NavigationHandler navigationHandler = application.getNavigationHandler();
					navigationHandler.handleNavigation(facesContext, null , voltarPagina);
				}
			}
		}
		
		//Verificando a p�gina acessada
		if (paginaFaculdadeCadastro || paginaFuncaoUsuario || paginaStatusCadastro || paginaEstadoCadastro || paginaCidadeCadastro
		 || paginafaculdadeEditar || paginaRecursoCadastro || paginaSubCategoriaCadastro || paginaUsuarioFaculdadeCadastro){
			// Criando um ExternalContext para conseguir pegar o Usu�rio logado no sistema
			ExternalContext externalContext = facesContext.getExternalContext();
			// Pegando todas os valores carregados na sess�o com o getSessionMap
			Map<String, Object> map = externalContext.getSessionMap();
			// Pegando o os valores contidos no Bean de autentica��o.
			AutenticacaoBean autenticacaoBean = (AutenticacaoBean) map.get("autenticacaoBean");
				
			usuario = autenticacaoBean.getUsuarioAutenticado();
			
			if(usuario.getCodUsuario() == null){				
				//Capturando o Application para pegar informa��es e obrigar a navega��o
				Application application = facesContext.getApplication(); 
				//Criando o NavigationHandler para poder obrigar a navega��o do usu�rio
				NavigationHandler navigationHandler = application.getNavigationHandler();
				FacesUtil.msgErro("Acesso Inv�lido!");
				
				//Realizando navega��o do usu�rio for�ada por estar tentando bular Login do sistema
				navigationHandler.handleNavigation(facesContext, null, "/paginas/principal.xhtml");
				
			//Verificando se usu�rio n�o � gestor nem administrador(C�digos maiores que 2)
			}else if (usuario.getFuncaoUsuario().getcodFuncaoUsuario() > 2L){
				FacesUtil.msgErro("Acesso Inv�lido");
				
				Application application = facesContext.getApplication();
				NavigationHandler navigationHandler = application.getNavigationHandler();
				navigationHandler.handleNavigation(facesContext, null , voltarPagina);
			}
		}
	}

	// Determina que a��o ser� cometida Antes do RESTORE_VIEW
	@Override
	public void beforePhase(PhaseEvent evento) {

	}

	// Definindo em que momento da requisi��o ser� executado o PhaseListener
	@Override
	public PhaseId getPhaseId() {

		// Determinando que PhaseListener ser� executado no momento da cria��o da �rvore da p�gina
		return PhaseId.RESTORE_VIEW;
	}

}
