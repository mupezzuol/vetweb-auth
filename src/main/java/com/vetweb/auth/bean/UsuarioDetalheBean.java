package com.vetweb.auth.bean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.vetweb.auth.dao.UsuarioDAO;
import com.vetweb.auth.model.Usuario;

@Model
public class UsuarioDetalheBean {
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	private String id;
	
	private Usuario usuario;
	
	public void findById() {
		this.usuario = usuarioDAO.findById(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
