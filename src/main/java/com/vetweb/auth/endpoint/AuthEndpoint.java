package com.vetweb.auth.endpoint;

import javax.inject.Inject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetweb.auth.model.Usuario;

@ServerEndpoint(value = "/endpoint/auth")
public class AuthEndpoint {
	
	@Inject
	private EndpointSession endpointSession;
	
	@OnOpen
	public void onOpen(Session session) {
		endpointSession.add(session);
	}
	
	@OnMessage
	public void onMessage(String param) {
		System.out.println("MENSAGEM RECEBIDA COM PARÂMETRO " + param);
	}
	
//	sendUsersIntegration
	
	public void sendNewUserNotification(Usuario usuario) {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = null;
		try {
			json = objectMapper.writeValueAsString(usuario);
		} catch (JsonProcessingException jsonProcessingException) {
			System.out.println("ERRO AO CONVERTER O NOVO USUÁRIO INCLUÍDO EM FORMATO JSON: " + jsonProcessingException.getMessage());
		}
		for (Session session : endpointSession.getSessions()) {
			if (session.isOpen()) {
				try {
					session.getBasicRemote()
						.sendText(json);
				} catch (Exception exception) {
					System.out.println(exception.getMessage());
				}
			}
		}
	}
	
}
