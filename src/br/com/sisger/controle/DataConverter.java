package br.com.sisger.controle;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "dataConverter")
public class DataConverter implements Converter{
	
	SimpleDateFormat datas = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String valor) {
		
		datas.setLenient(false);
		
		try {
			return datas.parse(valor);
					
		} catch (Exception ex) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Erro na conversão da Data!"));
		}
		
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
		try {
			
			datas.setLenient(false);
			
			String dat = datas.format((Date) object);
			
			return dat;
			
		} catch (Exception ex) {
			return "";
		}
		
	}

}
