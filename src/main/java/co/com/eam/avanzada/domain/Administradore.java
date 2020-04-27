package co.com.eam.avanzada.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;


/**
 * The persistent class for the administradores database table.
 * 
 */
@Entity
@Data
//@Table(name="administradores")
@NamedQuery(name="Administradore.findAll", query="SELECT a FROM Administradore a")
public class Administradore implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AdministradorePK id;

	@NotBlank(message = "El campo número de cuenta es obligatorio")
	@Column(name="numero_cuenta")
	private String numeroCuenta;

	@NotBlank(message = "El campo salario es obligatorio")
	private int salario;

	//bi-directional many-to-one association to Cargo
	@ManyToOne
	@JoinColumn(name="id_cargo")
	private Cargo cargo;

	

}