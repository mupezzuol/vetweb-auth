package com.vetweb.auth.client;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetweb.auth.dao.PerfilDAO;
import com.vetweb.auth.model.Permissao;

public class IntegrationClient {
	
	private Client wsClient;
	
	private WebTarget integrationResource;
	
	@Inject
	private PerfilDAO perfilDAO;
	
	public IntegrationClient() {
		wsClient = new ResteasyClientBuilder().build();
		integrationResource = wsClient.target("http://localhost:8080/vetweb").path("/integration/mappings");
	}
	
	public List<Permissao> permissoes() {
		List<Permissao> permissoes = null;
		Response response = integrationResource.request().get();
		try {
			
			permissoes = new ObjectMapper().readValue(response.readEntity(String.class), new TypeReference<List<Permissao>>() {	});
			permissoes.forEach(p -> {
				perfilDAO.savePermissao(p);
			});
			
		} catch(Exception exception) {
			
			throw new RuntimeException("NÃO FOI POSSÍVEL OBTER A LISTA DE PERMISSÕES, MOTIVO: " + exception.getMessage());
			
		}
		return permissoes;
	}

}
