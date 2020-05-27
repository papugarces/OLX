package co.com.eam.avanzada.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

import java.util.List;


/**
 * The persistent class for the subcategorias database table.
 * 
 */
@Entity
@Data
@Table(name="subcategorias")
@NamedQuery(name="Subcategoria.findAll", query="SELECT s FROM Subcategoria s")
public class Subcategoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSubCategoria;

	@NotBlank(message = "{name-mandatory}")
	@Size(min= 3, max=50, message="{name-size}")
	@Pattern(regexp="^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", message="{name-valid}")
	private String nombre;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="subcategoria",cascade={CascadeType.ALL})
	private List<Producto> productos;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="idCategoria")
	private Categoria categoria;

	

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setSubcategoria(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setSubcategoria(null);

		return producto;
	}


}