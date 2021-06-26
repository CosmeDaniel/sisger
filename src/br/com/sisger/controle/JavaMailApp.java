package br.com.sisger.controle;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.sisger.controle.FacesUtil;

public class JavaMailApp {
	private static String emailParaEnvio;
	private static String senhaProvisoria;
	private static String loginParaEnvio;
	private static boolean verificaEnvio;

	public static synchronized String getEmailParaEnvio() {
		return emailParaEnvio;
	}

	public static synchronized void setEmailParaEnvio(String emailParaEnvio) {
		JavaMailApp.emailParaEnvio = emailParaEnvio;
	}
	
	public static synchronized String getSenhaProvisoria() {
		return senhaProvisoria;
	}

	public static synchronized void setSenhaProvisoria(String senhaProvisoria) {
		JavaMailApp.senhaProvisoria = senhaProvisoria;
	}

	public static synchronized String getLoginParaEnvio() {
		return loginParaEnvio;
	}

	public static synchronized void setLoginParaEnvio(String loginParaEnvio) {
		JavaMailApp.loginParaEnvio = loginParaEnvio;
	}

	public static synchronized boolean isVerificaEnvio() {
		return verificaEnvio;
	}

	public static synchronized void setVerificaEnvio(boolean verificaEnvio) {
		JavaMailApp.verificaEnvio = verificaEnvio;
	}

	//M�todo para envio de e-mail com altera��o de senha
	public static void enviarEmail(){
		Properties props = new Properties();
	    //Configurando parametros do servidor do GMAIL
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "465");
	    
	    //Criando uma nova sess�o e setando uma inst�ncia
	    Session session = Session.getDefaultInstance(props,
	    		//Criando uma nova Inst�ncia de aut�ntica��o
	    		new javax.mail.Authenticator() {
	    			//Pegando senha protegida
	            	protected PasswordAuthentication getPasswordAuthentication(){
	            		//Retornando e-mail e senha de aut�ntica��o
	            		return new PasswordAuthentication("sisgersuporte@gmail.com", "sisger@27985T");}});

	            //Ativando forma de Debugar a Sess�o
	            session.setDebug(true);
	    try {
	    	//Criando um anova sess�o de menssagem
	    	Message message = new MimeMessage(session);
	    	//Passando o endere�o de e-mail enviado
	        message.setFrom(new InternetAddress("sisgersuporte@gmail.com")); //Remetente
	        //Passando o e-mail de envio (Destinat�rio(s)) 
            Address[] toUser = InternetAddress.parse(emailParaEnvio);  
	        message.setRecipients(Message.RecipientType.TO, toUser);
	        //Setando assundo do E-mail
	        message.setSubject("Solicita��o de Recupera��o de senha Sisger");
	        //Menssagem do e-mail
	        message.setText("Voc� solicitou a recupera��o de senha do Sisger. " +
	        		"Seu Login �  '"+ loginParaEnvio +"'. " +
	        		"Use esta Senha Provis�ria '" + senhaProvisoria + "' para Logar no sistema! " +
	        		"OBS.: Para sua seguran�a Altere a senha ap�s logar no sistema!");	        
	        //Enviando a menssagem criada
	        Transport.send(message);
	        System.out.println("Send Realizado!!!");
	        
	        //Informa que e-mail foi enviado com sucesso para Autentica��oBean
	        verificaEnvio = true;
	        

	    } catch (MessagingException ex) {
	    	//Emitindo mensagem de Erro
	    	FacesUtil.msgErro("Erro ao enviar E-mail!");
	    	throw new RuntimeException(ex);
	    }
	}
}
