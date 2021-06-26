package br.com.sisger.controle;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;



//Criando Classe para disparo de mensagem de avisos
public class FacesUtil {
	//Método que dispara mensagem de Sucesso
	public static void msgSucesso(String mensagem){
		//Criando FacesMassagem para que o JSF possa exibir o Grau da mensagem
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);

		//Capturando e importando a Instãncia Corrente do JSF
		FacesContext facesContext = FacesContext.getCurrentInstance();
		//Capturando e importando o Contexto Corrente do JSF
		ExternalContext externalContext = facesContext.getExternalContext();
		//Capiturando mesagem armazenada na memória flash e alterando seu tempo de permanência
		Flash flash = externalContext.getFlash();
		flash.setKeepMessages(true);
		
		//Capiturando o parametro do navegador
		facesContext.addMessage(null, facesMessage);
	}
	
	//Método que dispara mensagem de Erro
	public static void msgErro(String mensagem){
		//Criando FacesMassagem para que o JSF possa exibir o Grau da mensagem
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
		
		//Capturando e importando o Contexto Corrente do JSF
		FacesContext facesContext = FacesContext.getCurrentInstance();	
		//Capturando e importando o Contexto Corrente do JSF
		ExternalContext externalContext = facesContext.getExternalContext();
		//Capiturando mesagem armazenada na memória flash e alterando seu tempo de permanência
		Flash flash = externalContext.getFlash();
		flash.setKeepMessages(true);
		
		facesContext.addMessage(null, facesMessage);	
	}
	
	//Método que dispara mensagem de Alerta
	public static void msgAlerta(String mensagem){
		//Criando FacesMassagem para que o JSF possa exibir o Grau da mensagem
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, mensagem);
		
		//Capturando e importando o Contexto Corrente do JSF
		FacesContext facesContext = FacesContext.getCurrentInstance();
		//Capturando e importando o Contexto Corrente do JSF
		ExternalContext externalContext = facesContext.getExternalContext();
		//Capiturando mesagem armazenada na memória flash e alterando seu tempo de permanência
		Flash flash = externalContext.getFlash();
		flash.setKeepMessages(true);
		
		facesContext.addMessage(null, facesMessage);
		
	}

	//Criando Método para capturar valores passado da URL para Editar ou Excluir cadastros
	public static String getParam(String nome){
		//Capturando e importando o Contexto Corrente do JSF
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		//Capturando o Contexto do Browser(Contexto externo)
		ExternalContext externalContext = facesContext.getExternalContext();
		
		//Pegando um mapa dos parametros externos
		Map<String, String> parametros = externalContext.getRequestParameterMap();
		
		//Pegando o Parâmetro desejado
		String valor = parametros.get(nome);
		
		//Retornando o valor do Parâmetro
		return valor;
	}
	
}
