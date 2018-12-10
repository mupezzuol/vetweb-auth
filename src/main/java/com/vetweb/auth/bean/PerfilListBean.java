package com.vetweb.auth.bean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import com.vetweb.auth.model.Perfil;

import com.vetweb.auth.dao.PerfilDAO;

@Model
public class PerfilListBean {
	
	@Inject
	private PerfilDAO perfilDAO;
	
	@SuppressWarnings("unused")
	private List<Perfil> perfis = new ArrayList<>();

	public List<Perfil> getPerfis() {
		return perfilDAO.all();
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	
	

}
