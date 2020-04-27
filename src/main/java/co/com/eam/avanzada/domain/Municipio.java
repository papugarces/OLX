package co.com.eam.avanzada.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.List;


/**
 * The persistent class for the municipios database table.
 * 
 */
@Entity
@Data
@Table(name="municipios")
@NamedQuery(name="Municipio.findAll", query="SELECT m FROM Municipio m")
public class Municipio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private int codigo;

	private String nombre;

	//bi-directional many-to-one association to Departamento
	@ManyToOne
	private Departamento departamento;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="municipio")
	private List<Producto> productos;



	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setMunicipio(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setMunicipio(null);

		return producto;
	}

}