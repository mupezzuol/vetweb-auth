package com.vetweb.auth.service;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(
				propertyName = "destinationLookup",
				propertyValue = "java:/jms/topics/EmailSender"
	),
		@ActivationConfigProperty(
				propertyName = "destinationType",
				propertyValue = "javax.jms.Topic"
	)
})
public class EmailSender implements MessageListener {
	
	@Resource(mappedName = "java:jboss/mail/gmail")
	private Session session;
	
	public void send(String remetente, String destinatario, String assunto, String mensagem) {
		MimeMessage message = new MimeMessage(session);
		try {
			message.setRecipients(RecipientType.TO, InternetAddress.parse(destinatario));;
			message.setFrom(remetente);
			message.setSubject(assunto);
			message.setContent(mensagem, "text/html");
			Transport.send(message);
		} catch (MessagingException messagingException) {
			throw new RuntimeException(messagingException);
		}
	}

	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage)message;
		try {
			this.send("springbootalura@gmail.com", textMessage.getText(), "INCLUSÃO DE USUÁRIO NA VETWEB", "SEU USUÁRIO PARA ACESSO A VETWEB FOI CRIADO");
		} catch (JMSException jmsException) {
			throw new RuntimeException("OCORREU UM ERRO AO ENVIAR O E-MAIL DE NOTIFICAÇÃO " + jmsException);
		}
	}
	
}
