package br.com.segware.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.segware.web.jsonview.Views;

/**
 * @author Anderson Virginio Martins
 */
@Entity
@Table(name = "Comentario")
public class Comentario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1022092624232399374L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.Public.class)
	private Long id;

	@JsonView(Views.Public.class)
	@Column(nullable = false)
	private String descricao;

	@JsonView(Views.Public.class)
	@Column(nullable = false)
	private int up;
	
	@JsonView(Views.Public.class)
	@Column(nullable = false)
	private int down;

	public Comentario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}
	
	public int getDif() {
		return up-down;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + down;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + up;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comentario other = (Comentario) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (down != other.down)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (up != other.up)
			return false;
		return true;
	}

	public Comentario(Long id, String descricao, int up, int down) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.up = up;
		this.down = down;
	}
	
}
