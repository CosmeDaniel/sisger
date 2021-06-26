package br.com.sisger.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sisger.dao.UnidadeDAO;
import br.com.sisger.modelo.Unidade;

@FacesConverter("unidadeConverter") //Implentando anota��o de Converter e definindo o nome do converter
//Declarendo e Implementando Converter
public class UnidadeConverter implements Converter{

	//Escrevendo o m�todo de busca e  compara uma vari�vel
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String valor) {
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			//Retorna o valor longo representado por um par�metro de Cadeia de Caracteres
			Long codigo = Long.parseLong(valor);			
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			//Executando m�todo Buscar por C�digo na DAO e inserindo resultado na vari�vel
			Unidade unidade = unidadeDAO.buscarPorCodigo(codigo);
			
			return unidade;
			
		} catch (RuntimeException ex) {
			return null;
		}
		
	}

	//Escrevendo o m�todo de busca e  compara um objeto 
	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
		try {
			Unidade unidade = (Unidade) object;
			//Pegando o C�digo da Cidade e inserindo na vari�vel c�digo
			Long codigo = unidade.getCodUnidade();
			
			//Convertendo objeto em vari�vel e retornando o valor
			return codigo.toString();
			
		} catch (RuntimeException ex) {
			return null;
		}
	}
	
	
	
}
