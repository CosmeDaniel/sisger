package br.com.sisger.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sisger.dao.CategoriaDAO;
import br.com.sisger.modelo.Categoria;

@FacesConverter("categoriaConverter") //Implentando anota��o de Converter e definindo o nome do converter
//Declarendo e Implementando Converter
public class CategoriaConverter implements Converter{

	//Escrevendo o m�todo de busca e  compara uma vari�vel
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String valor) {
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			//Retorna o valor longo representado por um par�metro de Cadeia de Caracteres
			Long codigo = Long.parseLong(valor);
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			//Executando m�todo Buscar por C�digo na DAO e inserindo resultado na vari�vel
			Categoria categoria = categoriaDAO.buscarPorCodigo(codigo);
			
			return categoria;
			
		} catch (RuntimeException ex) {
			return null;
		}
	}

	//Escrevendo o m�todo de busca e  compara um objeto 
	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object objeto) {
		try {
			Categoria categoria = (Categoria) objeto;
			//Pegando o C�digo da Cidade e inserindo na vari�vel c�digo
			Long codigo = categoria.getCodCategoria();
			
			//Convertendo objeto em vari�vel e retornando o valor
			return codigo.toString();
		} catch (RuntimeException ex) {
			return null;
		}
	}
	
}
