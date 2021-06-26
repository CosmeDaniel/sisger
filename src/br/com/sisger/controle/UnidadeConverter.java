package br.com.sisger.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sisger.dao.UnidadeDAO;
import br.com.sisger.modelo.Unidade;

@FacesConverter("unidadeConverter") //Implentando anotação de Converter e definindo o nome do converter
//Declarendo e Implementando Converter
public class UnidadeConverter implements Converter{

	//Escrevendo o método de busca e  compara uma variável
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String valor) {
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			//Retorna o valor longo representado por um parâmetro de Cadeia de Caracteres
			Long codigo = Long.parseLong(valor);			
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			//Executando método Buscar por Código na DAO e inserindo resultado na variável
			Unidade unidade = unidadeDAO.buscarPorCodigo(codigo);
			
			return unidade;
			
		} catch (RuntimeException ex) {
			return null;
		}
		
	}

	//Escrevendo o método de busca e  compara um objeto 
	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
		try {
			Unidade unidade = (Unidade) object;
			//Pegando o Código da Cidade e inserindo na variável código
			Long codigo = unidade.getCodUnidade();
			
			//Convertendo objeto em variável e retornando o valor
			return codigo.toString();
			
		} catch (RuntimeException ex) {
			return null;
		}
	}
	
	
	
}
