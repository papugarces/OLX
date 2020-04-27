package co.com.eam.avanzada.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import java.util.List;


/**
 * The persistent class for the categorias database table.
 * 
 */
@Entity
@Data
@Table(name="categorias")
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategoria;

	@NotBlank(message = "El campo nombre es obligatorio")
	private String nombre;

	//bi-directional many-to-one association to Subcategoria
	@OneToMany(mappedBy="categoria",cascade={CascadeType.ALL})
	private List<Subcategoria> subcategorias;


	public Subcategoria addSubcategoria(Subcategoria subcategoria) {
		getSubcategorias().add(subcategoria);
		subcategoria.setCategoria(this);

		return subcategoria;
	}

	public Subcategoria removeSubcategoria(Subcategoria subcategoria) {
		getSubcategorias().remove(subcategoria);
		subcategoria.setCategoria(null);

		return subcategoria;
	}

}