package com.vetweb.auth.bean;


import java.io.IOException;
import javax.annotation.Resource;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import com.vetweb.auth.dao.UsuarioDAO;
import com.vetweb.auth.endpoint.AuthEndpoint;
import com.vetweb.auth.model.Usuario;

@Model
public class UsuarioBean {
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	private String id;
	
	@Inject
	private AuthEndpoint endpoint;
	
	private Part fotoUsuario;
	
	@Inject
	private FacesContext context;
	
	@Inject
	private JMSContext jmsContext;
	
	@Resource(name = "java:/jms/topics/EmailSender")
	private Destination destination;
	
	private Usuario usuario = new Usuario();
	
	@Transactional
	public String save() {
		messageFlash();
		if (id != null) {
			Usuario oldRecord = usuarioDAO.findById(id);
			usuario.setCaminhoFoto(oldRecord.getCaminhoFoto());
			usuario.setPassword(oldRecord.getPassword());
			usuarioDAO.update(usuario);
			context
				.addMessage(null, new FacesMessage("USUÁRIO ATUALIZADO COM SUCESSO	"));
			return "/usuarios/usuarios?faces-redirect=true";
		}
		JMSProducer jmsProducer = jmsContext.createProducer();
		String caminhoFoto = System.getProperty("jboss.home.dir") + "/vetwebFiles/imagens/usuarios/" + fotoUsuario.getSubmittedFileName();
		try {
			fotoUsuario.write(caminhoFoto);
		} catch (IOException e) {
			context
				.addMessage(null, new FacesMessage("ERRO AO SALVAR O ARQUIVO COM A FOTO DO USUÁRIO"));
		}
		usuario.setCaminhoFoto(caminhoFoto);
		usuarioDAO.save(usuario);
		jmsProducer.send(destination, usuario.getEmail());
		endpoint.sendNewUserNotification(usuario);
		context
			.addMessage(null, new FacesMessage("USUÁRIO INCLUÍDO COM SUCESSO	"));
		return "/usuarios/usuarios?faces-redirect=true";
	}
	
	public void findById() {
		if (id != null) {
			this.usuario = usuarioDAO.findById(id);
		}
	}	

	private void messageFlash() {
		context
			.getExternalContext()
			.getFlash()
			.setKeepMessages(true);
	}	
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Part getFotoUsuario() {
		return fotoUsuario;
	}

	public void setFotoUsuario(Part fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}

}
