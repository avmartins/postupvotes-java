package br.com.segware.controller;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.segware.entity.Comentario;
import br.com.segware.service.ComentarioService;

/**
 * @author Anderson Virginio Martins
 */
@RestController
@RequestMapping("/comentario")
public class ComentarioController {

	private static final Logger logger = Logger.getLogger(ComentarioController.class);

	private final ComentarioService comentarioService;
	
	public ComentarioController(ComentarioService comentarioService) {
		this.comentarioService = comentarioService;
	}

	@GetMapping("list")
	public ModelAndView list(ModelMap modelMap) {
		modelMap.addAttribute("comentarios", comentarioService.findAll());
		modelMap.addAttribute("comentario", new Comentario());
		return new ModelAndView("/fragments/comentarios/list", modelMap);
	}

	@GetMapping("/new")
	public ModelAndView newComentarioUpVotes(ModelMap modelMap) {
		modelMap.addAttribute("comentario", new Comentario());
		return new ModelAndView("/fragments/comentarios/new", modelMap);
	}

	@PostMapping("/save")
	public ModelAndView saveComentarioUpVotes(@ModelAttribute("comentario") Comentario comentario) {
		try {
			
			if(comentarioService.findByDescricao(comentario.getDescricao())) {
				logger.info("Comentário já existe, por favor post um novo comentario");
			} else {
				comentarioService.save(comentario);
			}
			
		} catch (Exception e) {
			logger.info("Erro ao incluir a comentario");
		}

		return new ModelAndView("redirect:/comentario/list");
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {

		Comentario comentario = comentarioService.findById(id);
		model.addAttribute("comentario", comentario);
		return new ModelAndView("fragments/comentarios/edit", model);
	}

	@PostMapping("/update/{id}")
	public ModelAndView update(@PathVariable("id") Long id, Comentario comentario) {
		comentarioService.updateForm(comentario);
		return new ModelAndView("redirect:/comentario/list");
	}

	@GetMapping("/remove/{id}")
	public ModelAndView remove(@PathVariable("id") Long id) {
		Comentario comentario = comentarioService.findById(id);

		comentarioService.delete(comentario);
		logger.info("Comentario excluído");

		return new ModelAndView("redirect:/comentario/list");
	}
	
	@PostMapping("/buscar")
	public ModelAndView buscar(@ModelAttribute("comentario") Comentario comentario, ModelMap modelMap) {
		modelMap.addAttribute("comentario",  comentario);
		
		Iterable<Comentario> comentarios =  comentarioService.findByComentario(comentario.getDescricao());
		modelMap.addAttribute("comentarios", comentarios);
		
		return new ModelAndView("/fragments/comentarios/list", modelMap);
	}
	
	@GetMapping("/up/{id}")
	public ModelAndView up(@PathVariable("id") Long id, ModelMap model) {

		Comentario comentario = comentarioService.findById(id);
		comentario.setUp(comentario.getUp()+1);
		
		comentarioService.save(comentario);
		
		Iterable<Comentario> comentarios =  comentarioService.findAll();
		model.addAttribute("comentarios", comentarios);
		
		model.addAttribute("comentario", new Comentario());
		return new ModelAndView("/fragments/comentarios/list", model);
	}
	
	@GetMapping("/down/{id}")
	public ModelAndView down(@PathVariable("id") Long id, ModelMap modelMap) {

		Comentario comentario = comentarioService.findById(id);
		comentario.setDown(comentario.getDown()+1);
		
		comentarioService.save(comentario);
		
		modelMap.addAttribute("comentario", new Comentario());
		
		Iterable<Comentario> comentarios =  comentarioService.findAll();
		modelMap.addAttribute("comentarios", comentarios);
		
		return new ModelAndView("/fragments/comentarios/list", modelMap);
	}
	
	
	
	
}
