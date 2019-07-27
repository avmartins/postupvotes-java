package br.com.segware.rest;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.segware.entity.Comentario;
import br.com.segware.service.ComentarioService;

/**
 * @author Anderson Virginio Martins
 */

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = { "http://localhost:8090" })
public class PostUpVotesRestController {

	private final ComentarioService comentarioService;

	public PostUpVotesRestController(ComentarioService comentarioService) {
		this.comentarioService = comentarioService;
	}
	
	// listar os posts atuais e seus upvotes recebidos
	@GetMapping("/posts")
	public Iterable<Comentario> consulta() {
		return comentarioService.findAll();
	}

	// Adicionar novo post;
	@PostMapping("/post")
	public ResponseEntity<Object> cadastro(@RequestBody Comentario comentario) {
		Comentario savedPost = comentarioService.save(comentario);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPost.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	// Adicionar upvote a um post.
	@PutMapping("/postUp/{id}")
	public ResponseEntity<Object> alteracao(@PathVariable long id) {

		Comentario postOptional = comentarioService.findById(id);

		if (postOptional == null)
			return ResponseEntity.notFound().build();

		postOptional.setId(id);
		
		save(postOptional,"UP");
		
		return ResponseEntity.noContent().build();
	}

	// Adicionar upvote a um post.
	@PutMapping("/postDown/{id}")
	public ResponseEntity<Object> alteracaoDown(@PathVariable long id) {

		Comentario postOptional = comentarioService.findById(id);

		if (postOptional == null)
			return ResponseEntity.notFound().build();

		postOptional.setId(id);
		
		save(postOptional,"DOWN");
			
		return ResponseEntity.noContent().build();
	
	}

	private void save(Comentario comentario, String tipo) {

		if (tipo.equals("UP")) {
			comentario.setUp(comentario.getUp()+1);
		} else {		
			comentario.setDown(comentario.getDown()+1);
		}

		comentarioService.save(comentario);
	}

	
}
