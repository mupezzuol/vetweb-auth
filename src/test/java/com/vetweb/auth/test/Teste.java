package com.vetweb.auth.test;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.vetweb.auth.dao.PerfilDAO;

@RunWith(Arquillian.class)
public class Teste {

	@Deployment
	public static WebArchive deploy() {
		return ShrinkWrap
				.create(WebArchive.class, "vetweb-auth.war")
				.addPackage("com.vetweb.auth.dao")
				.addPackage("com.vetweb.auth.model")
				.addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Inject
	private PerfilDAO perfilDAO;
	
	@Test
	public void testaInjecaoDeDependenciasNoTeste() {
		assertTrue(!perfilDAO.all().isEmpty());
	}
	
}
