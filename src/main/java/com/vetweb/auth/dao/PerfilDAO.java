package com.vetweb.auth.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.vetweb.auth.model.Perfil;
import com.vetweb.auth.model.Permissao;

@Stateful
public class PerfilDAO {
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	public void save(Perfil perfil) {
		entityManager.persist(perfil);
	}
	
	public void savePermissao(Permissao permissao) {
		if ((permissao.getId() == null) && (!allPermissoes().contains(permissao)))
				entityManager.persist(permissao);
	}

	public List<Permissao> allPermissoes() {
		return entityManager
				.createQuery("SELECT p FROM Permissao p", Permissao.class)
				.getResultList();
	}
	
	public Perfil update(Perfil perfil) {
		return entityManager.merge(perfil);
	}
	
	public List<Perfil> all() {
		return entityManager
				.createQuery("SELECT DISTINCT (p) FROM Perfil p LEFT JOIN FETCH p.permissoes perm", Perfil.class)
				.getResultList();
	}

	public Perfil findByName(String descricao) {
		return entityManager
				.find(Perfil.class, descricao);
	}

	public Permissao findPermissao(String pUrl) {
		return entityManager
				.createQuery("SELECT p FROM Permissao p WHERE p.url = :urlPermissao", Permissao.class)
				.setParameter("urlPermissao", pUrl)
				.getSingleResult();
	}
	
	public List<Permissao> permissions() {
		return entityManager
				.createQuery("SELECT p FROM Permissao p JOIN FETCH p.perfis", Permissao.class)
				.getResultList();
	}
	
}
