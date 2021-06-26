package br.com.sisger.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sisger.dao.EstadoDAO;
import br.com.sisger.modelo.Estado;

@FacesConverter("estadoConverter") //Implentando anota��o de Converter e definindo o nome do converter
//Declarendo e Implementando Converter
public class EstadoConverter implements Converter{

	//Escrevendo o m�todo de busca e  compara uma vari�vel
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String valor) {
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			//Retorna o valor longo representado por um par�metro de Cadeia de Caracteres
			Long codigo = Long.parseLong(valor);
			EstadoDAO estadoDAO = new EstadoDAO();
			//Executando m�todo Buscar por C�digo na DAO e inserindo resultado na vari�vel
			Estado estado = estadoDAO.buscarPorCodigo(codigo);
			
			return estado;
			
		} catch (RuntimeException ex) {
			return null;
		}
	}

	//Escrevendo o m�todo de busca e  compara um objeto 
	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object objeto) {
		try {
			Estado estado = (Estado) objeto;
			//Pegando o C�digo da Cidade e inserindo na vari�vel c�digo
			Long codigo = estado.getCodEstado();
			
			//Convertendo objeto em vari�vel e retornando o valor
			return codigo.toString();
			
		} catch (RuntimeException ex) {
			return null;
		}
	}
	
}
