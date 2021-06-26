package br.com.sisger.visao;

import java.io.Serializable;  
import java.util.Map;  
  
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.SessionScoped;  
import javax.faces.context.FacesContext;  
  
@ManagedBean  
@SessionScoped  
public class preferenciaThemas implements Serializable{  
        
    private static final long serialVersionUID = 1L;  
    private String thema = "aristo";  
  
    public String getThema() {  
          
        System.out.println("getThema:" +thema);  

          
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();  
          
        if( params.containsKey(thema) ){  
            thema = params.get(thema);  
        }
          
        return thema;  
    }  
  
    public void setThema(String thema) {  
        System.out.println("setThema:" +thema);  
          
        this.thema = thema;  
    }  
 
} 
