package com.vetweb.auth.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.vetweb.auth.model.Usuario;

@Stateful
public class UsuarioDAO {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	public void save(Usuario usuario) {
		entityManager.persist(usuario);
	}
	
	public Usuario update(Usuario usuario) {
		return entityManager.merge(usuario);
	}
	
	public List<Usuario> all() {
		return entityManager
				.createQuery("SELECT DISTINCT(u) FROM Usuario u "
						+ " JOIN FETCH u.perfis p "
						+ " JOIN FETCH p.permissoes perm", Usuario.class)
				.getResultList();
	}
	
	public List<Usuario> allLeft() {
		return entityManager
				.createQuery("SELECT DISTINCT(u) FROM Usuario u "
						+ " LEFT JOIN FETCH u.perfis p "
						+ " LEFT JOIN FETCH p.permissoes perm", Usuario.class)
				.getResultList();
	}

	public Usuario findById(String id) {
		return entityManager.find(Usuario.class, id);
	}
	
	public Usuario findByUsername(String username) {
		return entityManager
				.createQuery("SELECT u FROM Usuario u "
						+ " JOIN FETCH u.perfis p "
						+ " JOIN FETCH p.permissoes perm "
						+ " WHERE u.username = :user", Usuario.class)
				.setParameter("user", username)
				.getSingleResult();
							
	}
	
}
