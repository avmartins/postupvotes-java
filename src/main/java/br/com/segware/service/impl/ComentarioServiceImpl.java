package br.com.segware.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.segware.entity.Comentario;
import br.com.segware.repository.ComentarioRepository;
import br.com.segware.service.ComentarioService;

/**
 * @author Anderson Virginio Martins
 */
@Service
public class ComentarioServiceImpl implements ComentarioService {

	private final ComentarioRepository comentarioRepository;

	@Autowired
	public ComentarioServiceImpl(ComentarioRepository comentarioRepository) {
		this.comentarioRepository = comentarioRepository;
	}

	@Override
	public Iterable<Comentario> findAll() {
		return comentarioRepository.findAll();
	}

	@Override
	public Comentario save(Comentario comentario) {		
		return comentarioRepository.save(comentario);
	}

	@Override
	public void update(Comentario comentario) {
		comentarioRepository.save(comentario);
	}

	@Override
	public void updateForm(Comentario comentario) {
		Comentario p = comentarioRepository.findById(comentario.getId()).orElse(null);
		
		p.setDescricao(comentario.getDescricao());
		
		comentarioRepository.save(p);
	}
	
	@Override
	public void delete(Comentario comentario) {
		Comentario e = comentarioRepository.findById(comentario.getId()).orElse(null);

		comentarioRepository.delete(e);
	}

	@Override
	public Comentario findById(Long id) {
		return comentarioRepository.findById(id).orElse(null);
	}
	
	@Override
	public Iterable<Comentario> findByComentario(String comentario) {
		return comentarioRepository.findByComentario(comentario);
	}
	
	@Override
	public boolean findByDescricao(String descricao) {
		
		boolean retorno = false;
		for (Comentario comentario : comentarioRepository.findByDescricao(descricao)) {
			retorno = true;
			break;
		}
		
		return retorno;
	}

}
