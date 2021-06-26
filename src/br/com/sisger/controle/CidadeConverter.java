package br.com.sisger.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sisger.dao.CidadeDAO;
import br.com.sisger.modelo.Cidade;

@FacesConverter("cidadeConverter") //Implentando anota��o de Converter e definindo o nome do converter
//Declarendo e Implementando Converter
public class CidadeConverter implements Converter{
	
	//Escrevendo o m�todo de busca e  compara uma vari�vel 
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String valor) {
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			//Retorna o valor longo representado por um par�metro de Cadeia de Caracteres
			Long codigo = Long.parseLong(valor);
			CidadeDAO cidadeDAO = new CidadeDAO();
			//Executando m�todo Buscar por C�digo na DAO e inserindo resultado na vari�vel
			Cidade cidade = cidadeDAO.buscarPorCodigo(codigo);		
			
			return cidade;
			
		} catch (RuntimeException ex) {
			return null;
		}
	}

	//Escrevendo o m�todo de busca e  compara um objeto 
	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object objeto) {
		try {
			Cidade cidade = (Cidade) objeto;
			//Pegando o C�digo da Cidade e inserindo na vari�vel c�digo
			Long codigo = cidade.getCodCidade();
			
			//Convertendo objeto em vari�vel e retornando o valor
			return codigo.toString();
			
		} catch (RuntimeException ex) {
			return null;
		}
	}
	
	
}
