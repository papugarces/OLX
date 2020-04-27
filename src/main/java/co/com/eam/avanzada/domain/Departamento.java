package co.com.eam.avanzada.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.List;


/**
 * The persistent class for the departamentos database table.
 * 
 */
@Entity
@Data
@Table(name="departamentos")
@NamedQuery(name="Departamento.findAll", query="SELECT d FROM Departamento d")
public class Departamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private int codigo;

	private String nombre;

	//bi-directional many-to-one association to Municipio
	@OneToMany(mappedBy="departamento",cascade={CascadeType.ALL})
	private List<Municipio> municipios;


	public Municipio addMunicipio(Municipio municipio) {
		getMunicipios().add(municipio);
		municipio.setDepartamento(this);

		return municipio;
	}

	public Municipio removeMunicipio(Municipio municipio) {
		getMunicipios().remove(municipio);
		municipio.setDepartamento(null);

		return municipio;
	}

}