package br.com.sisger.modelo;

	import java.io.Serializable;  
	  
	@SuppressWarnings("serial")  
	public class Thema implements Serializable{  
	  
	    private String nome;  
	      
	    public Thema() {  
	    }  
	  
	    public Thema(String nome) {  
	        this.nome = nome;  
	    }  
	  
	    public String getName() {  
	        return nome;  
	    }  
	  
	    public void setName(String nome) {  
	        this.nome = nome;  
	    }  
	  
	        @Override  
	    public String toString() {  
	        return nome;  
	    }  
	}  
