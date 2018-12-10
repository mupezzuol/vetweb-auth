package com.vetweb.auth.bean;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.vetweb.auth.dao.UsuarioDAO;
import com.vetweb.auth.model.Usuario;

@Model
public class CurrentUser {
	
	@Inject
	private HttpServletRequest request;
	
	private Usuario usuario;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@PostConstruct
	private void load() {
		Principal usuario = request.getUserPrincipal();
		if (usuario != null) {
			String nomeUsuario = usuario.getName();
			this.usuario = usuarioDAO.findById(nomeUsuario);
		}
	}
	
	public String logout() {
		request.getSession().invalidate();
		System.out.println("Saindo do sistema");
		return "/index.xhtml?faces-redirect=true";
	}
	
	public Usuario get() {
		return this.usuario;
	}

}
