package com.vetweb.auth.bean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.vetweb.auth.dao.PerfilDAO;
import com.vetweb.auth.model.Perfil;

@Model
public class PerfilDetalheBean {
	
	@Inject
	private PerfilDAO perfilDAO;
	
	private String id;
	
	private Perfil perfil;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public void findById() {
		this.perfil = perfilDAO.findByName(id);
	}	

}
