package co.com.eam.avanzada.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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


	@Id
	@NotNull(message = "{email-mandatory}")
	@Pattern(regexp="^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$", message="{email-valid}")
	private String correo;
	
	@NotBlank(message = "{dni-mandatory}")
	@Size(min= 5, max=20, message="{dni-size}")
	private String dni;
	
	@NotBlank(message = "{lastname-mandatory}")
	@Size(min= 3, max=50, message="{lastname-size}")
	@Pattern(regexp="^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", message="{lastname-valid}")
	private String apellido;
	
	@NotBlank(message = "{password-mandatory}")
	@Size(min= 8, max=200, message="{password-size}")
	private String password;

	@NotBlank(message = "{address-mandatory}")
	@Size(min= 7, max=255, message="{address-size}")
	private String direccion;

	@Column(name="fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	//@Pattern(regexp="^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})$", message="{date-valid}")
	private Date fechaNacimiento;

	@NotBlank(message = "{name-mandatory}")
	@Size(min= 3, max=50, message="{name-size}")
	@Pattern(regexp="^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", message="{name-valid}")
	private String nombre;

	@NotBlank(message = "{phone-mandatory}")
	@Size(min= 7, max=20, message="{phone-size}")
	//@Pattern(regexp="^[0-9]$", message="Por favor solo ingresar numeros")
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