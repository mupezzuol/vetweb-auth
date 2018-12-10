package com.vetweb.auth.model.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.vetweb.auth.model.Permissao;

@FacesConverter("converterPermissao")
public class PermissaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Permissao permissao = new Permissao(value);
		return permissao;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Permissao) {
			Permissao permissao = (Permissao)value;
			return permissao.getUrl();
		}
		return null;
	}

}
