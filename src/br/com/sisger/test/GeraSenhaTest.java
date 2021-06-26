package br.com.sisger.test;

import java.util.UUID;

//Classe que gera senhas aleat�rias
public class GeraSenhaTest {
	
	//M�todo main(Principal) para gerar senhas 
	public static void main(String[] args){
		//Criando pacote UUID randomico com valores  
		UUID uuid = UUID.randomUUID();
		//Informando que ser� ultizado String do pacote UUI(Todos os dados)
		String myRandom = uuid.toString();
		//Imprimindo senhas de 8 Digitos que foram determinadas
		System.out.println(myRandom.substring(0,8));  
		
	}

}
