package br.com.sisger.test;

import java.util.UUID;

//Classe que gera senhas aleatórias
public class GeraSenhaTest {
	
	//Método main(Principal) para gerar senhas 
	public static void main(String[] args){
		//Criando pacote UUID randomico com valores  
		UUID uuid = UUID.randomUUID();
		//Informando que será ultizado String do pacote UUI(Todos os dados)
		String myRandom = uuid.toString();
		//Imprimindo senhas de 8 Digitos que foram determinadas
		System.out.println(myRandom.substring(0,8));  
		
	}

}
