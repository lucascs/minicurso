package br.com.caelum.vraptor.minicurso;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.minicurso.model.Professor;
import br.com.caelum.vraptor.minicurso.model.Usuario;

@Resource
public class LoginController {

	private final Usuario usuario;

	public LoginController(Usuario usuario) {
		this.usuario = usuario;
	}

	public void login(String email, String senha) {
		Professor professor = null;

		this.usuario.login(professor);
	}

	public void logout() {
		this.usuario.logout();
	}
}
