
package controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import entity.Usuario;
import repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioRepository usuarioRepository;
	private final String CLIENTE_URI = "usuarios/";

	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@GetMapping("/")
	public ModelAndView list() {
		Iterable<Usuario> usuarios = this.usuarioRepository.findAll();
		return new ModelAndView(CLIENTE_URI + "list","usuarios",usuarios);
	}

	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Usuario usuario) {
		return new ModelAndView(CLIENTE_URI + "view","usuario",usuario);
	}

	@GetMapping("/novo")
	public String createForm(@ModelAttribute Usuario usuario) {
		return CLIENTE_URI + "form";
	}

	@PostMapping(params = "form")
	public ModelAndView create(@Valid Usuario usuario,BindingResult result,RedirectAttributes redirect) {
		if (result.hasErrors()) { return new ModelAndView(CLIENTE_URI + "form","formErrors",result.getAllErrors()); }
		usuario = this.usuarioRepository.save(usuario);
		redirect.addFlashAttribute("globalMessage","Usuario gravado com sucesso");
		return new ModelAndView("redirect:/" + CLIENTE_URI + "{usuario.id}","usuario.id",usuario.getId());
	}

	@GetMapping(value = "alterar/{id}")
	public ModelAndView alterarForm(@PathVariable("id") Usuario usuario) {
		return new ModelAndView(CLIENTE_URI + "form","usuario",usuario);
	}

}
