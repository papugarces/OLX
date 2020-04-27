package co.com.eam.avanzada.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Data
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsuarioPK id;

	@NotBlank(message = "El campo apellido es obligatorio")
	private String apellido;
	
	@NotBlank(message = "El campo clave es obligatorio")
	private String password;

	@NotBlank(message = "El campo dirección es obligatorio")
	private String direccion;

	
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	@NotBlank(message = "El campo nombre es obligatorio")
	private String nombre;

	@NotBlank(message = "El campo teléfono es obligatorio")
	private String telefono;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="usuario",cascade={CascadeType.ALL})
	private List<Producto> productos;

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setUsuario(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setUsuario(null);

		return producto;
	}

	

}