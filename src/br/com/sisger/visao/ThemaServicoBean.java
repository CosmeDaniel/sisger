package br.com.sisger.visao;

import java.util.ArrayList;  
import java.util.List;  
import java.util.Map;  
import java.util.TreeMap;  
  

import javax.annotation.PostConstruct;  
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ManagedProperty;  

import br.com.sisger.modelo.Thema;
  
@ManagedBean  
public class ThemaServicoBean {  
  
    private Map<String, String> themas;  
    private List<Thema> advacedThemas;  
    private String thema; 
    
@ManagedProperty(value="#{preferenciaThemas}")
    
    private preferenciaThemas gp;  
      
    public List<Thema> getAdvacedThemas() {  
        return advacedThemas;  
    }  
      
    public preferenciaThemas getGp() {  
        return gp;  
    }  
      
    public String getThema() {  
        return thema;  
    }  
    public Map<String, String> getThemas() {  
        return themas;  
    }  
    @PostConstruct  
    public void init(){  
        System.out.println("Init Thema");  
          
        thema = gp.getThema();  
          
        advacedThemas = new ArrayList<Thema>();  
        advacedThemas.add(new Thema("aristo"));  
        advacedThemas.add(new Thema("cupertino"));  
        advacedThemas.add(new Thema("trontastic"));  
          
        themas = new TreeMap<String, String>();  
          
        themas.put("Aristo",         "aristo");  
        themas.put("Black-Tie",      "black-tie");  
        themas.put("Blitzer",        "blitzer");  
        themas.put("Bluesky",        "bluesky");  
        themas.put("Casablanca",     "casablanca");  
        themas.put("Cupertino",      "cupertino");  
        themas.put("Dark-Hive",      "dark-hive");  
        themas.put("Dot-Luv",        "dot-luv");  
        themas.put("Eggplant",       "Eggplant");  
        themas.put("Excite-Bike",    "excite-bike");  
        themas.put("Flick",          "flick");  
        themas.put("Glass-X",        "glass-x");  
        themas.put("Hot-Speaks",     "ht-speaks");  
        themas.put("Humanity",       "humanity");  
        themas.put("Le-Frog",        "le-frog");  
        themas.put("Midnight",       "midnight");  
        themas.put("Mint-Choc",      "mint-choc");  
        themas.put("Overcast",       "overcast");  
        themas.put("Pepper-Grinder", "pepper-grinder");  
        themas.put("Redmond",        "redmond");  
        themas.put("Rocket",         "rocket");  
        themas.put("Smoothness",     "smoothness");  
        themas.put("Sout-Stret",     "sout-street");  
        themas.put("Start",          "start");  
        themas.put("Sunny",          "sunny");  
        themas.put("Swanky-Purse",   "swanky-purse");  
        themas.put("Trontastic",     "trontastic");  
        themas.put("UI-Darkness",    "ui-darkness");  
        themas.put("UI-Lightness",   "ui-lightness");  
        themas.put("Vader",          "vader");  
    }  
    public void saveThema(){  
        System.out.println("saveThema");  
        gp.setThema(thema);  
    }  
   
    public void setAdvacedThemas(List<Thema> advacedThemas) {  
        this.advacedThemas = advacedThemas;  
    }  
    public void setGp(preferenciaThemas gp) {  
        this.gp = gp;  
    }  
    public void setThema(String thema) {  
        this.thema = thema;  
    }  
    public void setThemas(Map<String, String> themas) {  
        this.themas = themas;  
    }  
      
}
