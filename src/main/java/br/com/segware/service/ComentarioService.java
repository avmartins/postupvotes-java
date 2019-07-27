package  br.com.segware.service;

import br.com.segware.entity.Comentario;

/**
 * @author Anderson Virginio Martins
 */
public interface ComentarioService {

	Iterable<Comentario> findAll();
	
	Comentario findById(Long id);
	
	Iterable<Comentario> findByComentario(String comentario);
	
	boolean findByDescricao(String descricao);

	Comentario save(Comentario comentario);

	void update(Comentario comentario);

	void updateForm(Comentario comentario);
	
	void delete(Comentario comentario);
}
