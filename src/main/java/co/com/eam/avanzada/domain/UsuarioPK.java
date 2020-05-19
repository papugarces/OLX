package co.com.eam.avanzada.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * The primary key class for the usuarios database table.
 * 
 */
@Embeddable
@Data
public class UsuarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "El campo cédula es obligatorio")
	@Size(min= 8, max=200, message="debe de tener minimo 8 caracteres")
	private String dni;

	@NotNull(message = "El campo correo es obligatorio")
	@Pattern(regexp="^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$", message="este no es un email valido")
	private String correo;

}