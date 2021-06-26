package br.com.sisger.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sisger.dao.FuncaoUsuarioDAO;
import br.com.sisger.modelo.FuncaoUsuario;

@FacesConverter("funcaoUsuarioConverter") //Implentando anotação de Converter e definindo o nome do converter
//Declarendo e Implementando Converter
public class FuncaoUsuarioConverter implements Converter{

	//Escrevendo o método de busca e  compara uma variável
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String valor) {
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			//Retorna o valor longo representado por um parâmetro de Cadeia de Caracteres
			Long codigo = Long.parseLong(valor);
			FuncaoUsuarioDAO funcaoUsuarioDAO = new FuncaoUsuarioDAO();
			//Executando método Buscar por Código na DAO e inserindo resultado na variável
			FuncaoUsuario funcaoUsuario = funcaoUsuarioDAO.buscarPorCodigo(codigo);
			
			return funcaoUsuario;
			
		} catch (RuntimeException ex) {
			return null;
		}
	}

	//Escrevendo o método de busca e  compara um objeto 
	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
		try {
			FuncaoUsuario funcaoUsuario = (FuncaoUsuario) object;
			//Pegando o Código da Cidade e inserindo na variável código
			Long codigo = funcaoUsuario.getcodFuncaoUsuario();
			
			//Convertendo objeto em variável e retornando o valor
			return codigo.toString();
			
		} catch (RuntimeException ex) {
			return null;
		}
	}
}
