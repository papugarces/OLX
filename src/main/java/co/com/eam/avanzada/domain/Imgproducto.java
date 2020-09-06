package co.com.eam.avanzada.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the imgproducto database table.
 * 
 */
@Entity
@Data
@NamedQuery(name="Imgproducto.findAll", query="SELECT i FROM Imgproducto i")
public class Imgproducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int idImgProducto;

	private String url;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="idProducto")
	private Producto producto;


}