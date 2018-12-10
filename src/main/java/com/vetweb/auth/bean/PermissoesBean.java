package com.vetweb.auth.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import com.vetweb.auth.client.IntegrationClient;
import com.vetweb.auth.dao.PerfilDAO;
import com.vetweb.auth.model.Perfil;
import com.vetweb.auth.model.Permissao;

@Model
public class PermissoesBean {
	
	@Inject
	private IntegrationClient integrationClient;
	
	@Inject
	private PerfilDAO perfilDAO;
	
	private String idPerfil;
	
	private Perfil perfil;
	
	private List<Permissao> permissoes;
	
	private DualListModel<Permissao> dualListPermissao = new DualListModel<>(Arrays.asList(), Arrays.asList());
	
	List<Permissao> available = new ArrayList<>();
	
	List<Permissao> attributed = new ArrayList<>();
	
	public DualListModel<Permissao> getDualListPermissao() {
		return dualListPermissao;
	}
	
	@PostConstruct
	public void initialize() {
		permissoes = integrationClient.permissoes();
	}
	
	public void findPerfil() {
		this.perfil = perfilDAO.findByName(idPerfil);
		for (Permissao permissao : permissoes) {
			if (perfil.getPermissoes().contains(permissao)) {
				attributed.add(permissao);
			} else {
				available.add(permissao);
			}
		}
		this.dualListPermissao = new DualListModel<>(available, attributed);
	}	
	
	@Transactional
	public void onTransfer(TransferEvent event) {
		perfil = perfilDAO.findByName(idPerfil);
		List<Permissao> permissoes = (List<Permissao>)event.getItems();
		if (event.isAdd()) {
			permissoes.forEach(p -> {
				boolean perfilHasPermissoes = perfil.getPermissoes().contains(p);
				p = perfilDAO.findPermissao(p.getUrl());
				if (!perfilHasPermissoes) {
					perfil.getPermissoes().add(p);
				}
			});
				
		} else if (event.isRemove()) {
			permissoes.forEach(p -> {
				boolean perfilHasPermissoes = perfil.getPermissoes().contains(p);
				p = perfilDAO.findPermissao(p.getUrl());
				if(perfilHasPermissoes) {
					perfil.getPermissoes().remove(p);
				}
			});
		}
		perfilDAO.update(perfil);
	}
	
	public void setDualListPermissao(DualListModel<Permissao> dualListPermissao) {
		this.dualListPermissao = new DualListModel<>(Arrays.asList(), Arrays.asList());
	}

	public String getIdPerfi() {
		return idPerfil;
	}

	public void setIdPerfi(String idPerfi) {
		this.idPerfil = idPerfi;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	public List<Permissao> getAvailable() {
		return available;
	}

	public void setAvailable(List<Permissao> available) {
		this.available = available;
	}

	public List<Permissao> getAttributed() {
		return attributed;
	}

	public void setAttributed(List<Permissao> attributed) {
		this.attributed = attributed;
	}

}
