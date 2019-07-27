package  br.com.segware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.segware.entity.Comentario;

/**
 * @author Anderson Virginio Martins
 */
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
	
	 @Query("select c from Comentario c where UPPER(c.descricao) like %?1%")
	    Iterable<Comentario> findByComentario(String descricao);
	 
	 @Query("select c from Comentario c where UPPER(c.descricao) = ?1")
	    Iterable<Comentario> findByDescricao(String descricao);
}
