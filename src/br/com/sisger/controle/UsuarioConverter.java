package br.com.sisger.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sisger.dao.UsuarioDAO;
import br.com.sisger.modelo.Usuario;

@FacesConverter("usuarioConverter")//Implentando anotação de Converter e definindo o nome do converter
//Declarendo e Implementando Converter
public class UsuarioConverter implements Converter{

	//Escrevendo o método de busca e  compara uma variável
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String valor) {
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			//Retorna o valor longo representado por um parâmetro de Cadeia de Caracteres
			long codigo = Long.parseLong(valor);
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			//Executando método Buscar por Código na DAO e inserindo resultado na variável
			Usuario usuario = usuarioDAO.buscarPorCodigo(codigo);
			
			return usuario;
			
		} catch (RuntimeException ex) {
			return null;
		}
		
	}

	//Escrevendo o método de busca e  compara um objeto 
	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
		try {
			Usuario usuario = (Usuario) object;
			//Pegando o Código da Cidade e inserindo na variável código
			Long codigo = usuario.getCodUsuario();
			
			//Convertendo objeto em variável e retornando o valor
			return codigo.toString();
			
		} catch (RuntimeException ex) {
			return null;
		}
	}

}
