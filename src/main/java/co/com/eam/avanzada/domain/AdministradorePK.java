package co.com.eam.avanzada.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * The primary key class for the administradores database table.
 * 
 */
@Embeddable
@Data
public class AdministradorePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	@Column
	@NotBlank(message = "El campo cédula es obligatorio")
	private String dni;
	@Column
	@NotBlank(message = "El campo correo es obligatorio")
	private String correo;

}