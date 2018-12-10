package com.vetweb.auth.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import com.vetweb.auth.dao.PerfilDAO;
import com.vetweb.auth.model.Perfil;
import com.vetweb.auth.model.Permissao;

@RequestScoped
@Path("perfil")
public class PerfilResource {
	
	@Inject
	private PerfilDAO perfilDAO;
	
	@GET
	@Path("all")
	@Produces({MediaType.APPLICATION_JSON})
	@Wrapped
	public List<Perfil> findAll() {
		return perfilDAO.all();
	}
	
	@GET
	@Path("permissions")
	@Produces({MediaType.APPLICATION_JSON})
	@Wrapped
	public Map<String, List<String>> permissions() {
		Map<String, List<String>> permissionsWithProfiles = new HashMap<>();
		List<Permissao> permissions = perfilDAO.permissions();
		permissions
			.stream()
			.forEach(p -> {
				permissionsWithProfiles.put(p.getUrl(), p.getPerfis().stream().map(perf -> perf.getDescricao()).collect(Collectors.toList()));
			});
		return permissionsWithProfiles;
	}

}
