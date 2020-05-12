package co.com.eam.avanzada.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the mensajes database table.
 * 
 */
@Entity
@Data
@Table(name="mensajes")
@NamedQuery(name="Mensaje.findAll", query="SELECT m FROM Mensaje m")
public class Mensaje implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MensajePK id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaEnvio;

	
	//@NotBlank(message = "Escribe un mensaje para poder enviarlo")
	private String texto;


	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="correoRemitente", referencedColumnName="correo"),
		@JoinColumn(name="dniRemitente", referencedColumnName="dni")
		})
	//Usuario Remitente
	private Usuario usuario;

}