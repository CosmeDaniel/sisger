package br.com.sisger.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sisger.dao.StatusDAO;
import br.com.sisger.modelo.Status;

@FacesConverter("statusConverter")//Implentando anotação de Converter e definindo o nome do converter
//Declarendo e Implementando Converter
public class StatusConverter implements Converter{

	//Escrevendo o método de busca e  compara uma variável 
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String valor) {
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			//Retorna o valor longo representado por um parâmetro de Cadeia de Caracteres
			Long codigo = Long.parseLong(valor);
			StatusDAO statusDAO = new StatusDAO();
			//Executando método Buscar por Código na DAO e inserindo resultado na variável
			Status status = statusDAO.buscarPorCodigo(codigo);
			
			return status;
			
		} catch (RuntimeException e) {
			return null;
		}
	}

	//Escrevendo o método de busca e  compara um objeto 
	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
		try {
			Status status = (Status) object;
			//Pegando o Código da Cidade e inserindo na variável código
			Long codigo = status.getCodStatus();
			
			//Convertendo objeto em variável e retornando o valor
			return codigo.toString();
			
		} catch (RuntimeException ex) {
			return null;
		}
	}

}
