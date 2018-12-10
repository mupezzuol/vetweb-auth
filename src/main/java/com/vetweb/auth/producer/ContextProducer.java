package com.vetweb.auth.producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class ContextProducer {
	
	@RequestScoped
	@Produces
	public FacesContext facesContext() {
		return FacesContext
				.getCurrentInstance();
	}

}
