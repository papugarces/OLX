package co.com.eam.avanzada.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the productos database table.
 * 
 */
@Entity
@Data
@Table(name="productos")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idProducto;
	
	@NotBlank(message = "{description-mandatory}")
	private String descripcion;

	@NotBlank(message = "{address-mandatory}")
	private String direccion;
	
	//@NotBlank(message = "El campo estado es obligatorio")
	private boolean estado;

	@NotNull(message = "{date-mandatory}")
	private Date fechaPublicacion;

	@NotBlank(message = "{phone-mandatory}")
	@Size(min= 7, max=20, message="{phone-size}")
	//@Pattern(regexp="/^[0-9]$/", message="Por favor solo ingresar numeros")
	private String numContacto;


	//@NotBlank(message = "{price-mandatory}")
	//@Pattern(regexp="^[0-9]$", message="Por favor solo ingresar numeros")
	private double precio;

	@NotBlank(message = "{title-mandatory}")
	private String titulo;

	//bi-directional many-to-one association to Imgproducto
	@OneToMany(mappedBy="producto")
	private List<Imgproducto> imgproductos;

	//bi-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name="IdUbicacion")
	private Municipio municipio;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="correoContacto", referencedColumnName="correo"),
		@JoinColumn(name="idUsuario", referencedColumnName="dni")
		})
	private Usuario usuario;

	//bi-directional many-to-one association to Subcategoria
	@ManyToOne
	@JoinColumn(name="idSubcategoria")
	private Subcategoria subcategoria;

	
	public Imgproducto addImgproducto(Imgproducto imgproducto) {
		getImgproductos().add(imgproducto);
		imgproducto.setProducto(this);

		return imgproducto;
	}

	public Imgproducto removeImgproducto(Imgproducto imgproducto) {
		getImgproductos().remove(imgproducto);
		imgproducto.setProducto(null);

		return imgproducto;
	}

	
	
	

	public Producto() {
		super();
	}

	public Producto(Integer idProducto, @NotBlank(message = "El campo descripción es obligatorio") String descripcion,
			@NotBlank(message = "El campo dirección es obligatorio") String direccion,
			@NotNull(message = "El campo estado es obligatorio") boolean estado,
			Date fechaPublicacion,
			@NotBlank(message = "El campo teléfono de contácto es obligatorio") String numContacto,
			@NotNull(message = "El campo precio es obligatorio") double precio,
			@NotBlank(message = "El campo título es obligatorio") String titulo, 
			List<Imgproducto> imgproductos,
			Municipio municipio, 
			Usuario usuario, 
			Subcategoria subcategoria) {
		super();
		this.idProducto = idProducto;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.estado = estado;
		this.fechaPublicacion = fechaPublicacion;
		this.numContacto = numContacto;
		this.precio = precio;
		this.titulo = titulo;
		this.imgproductos = imgproductos;
		this.municipio = municipio;
		this.usuario = usuario;
		this.subcategoria = subcategoria;
	}

	
	
	


}