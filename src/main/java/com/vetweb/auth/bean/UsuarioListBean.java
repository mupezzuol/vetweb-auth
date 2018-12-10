package com.vetweb.auth.bean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.vetweb.auth.dao.UsuarioDAO;
import com.vetweb.auth.model.Usuario;

@Model
public class UsuarioListBean {
	
	@Inject
	private UsuarioDAO dao;
	
	@SuppressWarnings("unused")
	private List<Usuario> usuarios = new ArrayList<>();

	public List<Usuario> getUsuarios() {
		return dao.all();
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	

}
