package co.com.eam.avanzada.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import java.util.List;


/**
 * The persistent class for the cargos database table.
 * 
 */
@Entity
@Data
@Table(name="cargos")
@NamedQuery(name="Cargo.findAll", query="SELECT c FROM Cargo c")
public class Cargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "El campo nombre del cargo es obligatorio")
	private String nombre;

	//bi-directional many-to-one association to Administradore
	@OneToMany(mappedBy="cargo")
	private List<Administradore> administradores;


	public Administradore addAdministradore(Administradore administradore) {
		getAdministradores().add(administradore);
		administradore.setCargo(this);

		return administradore;
	}

	public Administradore removeAdministradore(Administradore administradore) {
		getAdministradores().remove(administradore);
		administradore.setCargo(null);

		return administradore;
	}

}