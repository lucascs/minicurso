package br.com.caelum.vraptor.minicurso;

import static br.com.caelum.vraptor.view.Results.logic;
import static br.com.caelum.vraptor.view.Results.page;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.minicurso.dao.ProfessorDao;
import br.com.caelum.vraptor.minicurso.model.Professor;
import br.com.caelum.vraptor.validator.Hibernate;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.validator.Validations;

@Resource
public class ProfessoresController {

	private final Result result;
	private final ProfessorDao dao;
	private final Validator validator;

	public ProfessoresController(Result result, Validator validator, ProfessorDao dao) {
		this.result = result;
		this.validator = validator;
		this.dao = dao;
	}

	public void form() {

	}

	@Path("/professores")
	@Get
	public List<Professor> lista() {
		return dao.list();
	}

	@Path("/professores/{id}")
	public Professor visualiza(Long id) {
		return dao.load(id);
	}

//	public Download download() {
//		return new InputStreamDownload(new FileInputStream("abc.txt"),
//				"text/plain", "abc.txt");
//	}

	@Post
	@Path("/professores")
	public void adiciona(Professor professor) {
		validaAdiciona(professor);

		dao.salva(professor);
		result.include("mensagem", "Professor " + professor.getNome()
				+" adicionado com sucesso!");

		result.use(logic()).redirectTo(ProfessoresController.class).lista();
	}

	private void validaAdiciona(final Professor professor) {
		validator.addAll(Hibernate.validate(professor));

		result.include("professor", professor);










		if(professor.getNome() == null || professor.getNome().isEmpty()) {
			validator.add(new ValidationMessage("Você precisa falar um nome", "professor.nome"));
		}
		if(professor.getEmail() == null || professor.getEmail().isEmpty()) {
			validator.add(new ValidationMessage("Você precisa falar um email", "professor.email"));
		}

		validator.checking(new Validations() {{
			that(professor.getSenha().length() > 6, "professor.senha", "senha_pequena");
		}});


		validator.onErrorUse(page()).of(ProfessoresController.class).form();
	}
}







