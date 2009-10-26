package br.com.caelum.vraptor.minicurso.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
public class StringFactory implements ComponentFactory<String>{

	@Override
	public String getInstance() {
		return "Minha string";
	}

}
