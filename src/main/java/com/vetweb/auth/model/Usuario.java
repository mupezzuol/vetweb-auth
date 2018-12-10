package com.vetweb.auth.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vetweb.auth.configuration.PasswordGenerator;

@Entity
@Table(name = "tbl_usuario")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	@Id
	private String username;
	
	private String caminhoFoto;
	
	private String password;
	
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@XmlElement(name = "perfil")
	@XmlElementWrapper(name = "perfis")
	@JsonManagedReference
	@JsonProperty("perfis")
	@JoinTable(name = "tbl_usuario_perfis")
	private Set<Perfil> perfis = new HashSet<>();

	public Usuario() {
	}
	
	public Usuario(String username, String password, Set<Perfil> perfis) {
		this.username = username;
		this.password = PasswordGenerator.genPassword(password);
		this.perfis = perfis;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = PasswordGenerator.genPassword(password);
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}

	public String getCaminhoFoto() {
		return caminhoFoto;
	}

	public void setCaminhoFoto(String caminhoFoto) {
		this.caminhoFoto = caminhoFoto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
